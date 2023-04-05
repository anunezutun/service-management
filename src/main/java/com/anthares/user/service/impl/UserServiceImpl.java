package com.anthares.user.service.impl;

import com.anthares.commons.model.User;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.commons.service.CommonService;
import com.anthares.user.exception.UserNotFoundException;
import com.anthares.user.jpa.JpaUser;
import com.anthares.user.rest.input.LoginInputDto;
import com.anthares.user.rest.input.UserInputDto;
import com.anthares.user.rest.output.UserOutputDto;
import com.anthares.user.service.UserService;
import com.anthares.user.util.UserConstants;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Class.
 *
 * @author abelK
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl extends CommonService implements UserService {

  private final JpaUser jpaUser;

  @Override
  public FormatOutput<UserOutputDto> saveUser(UserInputDto createUserInputDto) {

    User user = setDataUser(createUserInputDto);
    jpaUser.save(user);

    return buildResponse(buildUserDto(user),
        UserConstants.USER_CREATED_CODE,
        UserConstants.USER_CREATED_MSG);
  }

  @Override
  public FormatOutput<UserOutputDto> updateUser(UserInputDto updateUserInputDto) {

    User user = setDataUser(updateUserInputDto);
    user = jpaUser.save(user);

    return buildResponse(buildUserDto(user),
        UserConstants.USER_UPDATED_CODE,
        UserConstants.USER_UPDATED_MSG);
  }

  /*@Override
  public UserOutputDto getUserDtoByGuid(String userGuid) {

    Optional<User> optionalUser = jpaUser.findByGuid(userGuid);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return buildUserDto(user);
    } else {
      throw new UserNotFoundException(UserConstants.USER_NOT_FOUND_MSG);
    }
  }*/

  @Override
  public FormatOutput<UserOutputDto> login(LoginInputDto loginInputDto) {
    var user = jpaUser.findByUsernameIgnoreCaseAndPassword(
        loginInputDto.getUsername(), loginInputDto.getPassword());

    if (user.isPresent()) {
      return buildResponse(buildUserDto(user.get()),
          UserConstants.USER_SUCCESS_LOGIN_CODE,
          UserConstants.USER_SUCCESS_LOGIN_MSG);
    } else {
      throw new UserNotFoundException(UserConstants.USER_NOT_FOUND_MSG);
    }
  }

  @Override
  public FormatOutput<Collection<User>> listUser() {
    List<User> userList = jpaUser.findAll();
    return buildResponse(userList,
        UserConstants.USER_SUCCESS_PROCESS_CODE,
        UserConstants.USER_SUCCESS_PROCESS_MSG);
  }

  @Override
  public FormatOutput<UserOutputDto> getUser(String userGuid) {
    Optional<User> userOptional = jpaUser.findByGuid(userGuid);
    if (userOptional.isPresent()) {
      UserOutputDto userDto = buildUserDto(userOptional.get());
      userDto.setPassword(userOptional.get().getPassword());
      return buildResponse(userDto,
          UserConstants.USER_SUCCESS_PROCESS_CODE,
          UserConstants.USER_SUCCESS_PROCESS_MSG);
    } else {
      throw new UserNotFoundException(UserConstants.USER_NOT_FOUND_MSG);
    }
  }

  private User setDataUser(UserInputDto userInputDto) {
    return User.builder()
            .guid(userInputDto.getGuid() != null ? userInputDto.getGuid() : null)
            .names(userInputDto.getNames())
            .username(userInputDto.getUsername())
            .password(userInputDto.getPassword())
            .phoneNumber(userInputDto.getPhoneNumber())
            .email(userInputDto.getEmail())
            .build();
  }

  private UserOutputDto buildUserDto(User user) {
    return UserOutputDto.builder()
            .guid(user.getGuid())
            .names(user.getNames())
            .username(user.getUsername())
            .phoneNumber(user.getPhoneNumber())
            .email(user.getEmail())
            .build();
  }

}
