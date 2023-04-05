package com.anthares.user.service;

import com.anthares.commons.model.User;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.user.rest.input.LoginInputDto;
import com.anthares.user.rest.input.UserInputDto;
import com.anthares.user.rest.output.UserOutputDto;
import java.util.Collection;

/** Interface.
 *
 * @author abelK
 */
public interface UserService {

  FormatOutput<UserOutputDto> saveUser(UserInputDto createUserInputDto);

  FormatOutput<UserOutputDto> updateUser(UserInputDto updateUserInputDto);

  //UserOutputDto getUserDtoByGuid(String userGuid);

  FormatOutput<UserOutputDto> login(LoginInputDto loginInputDto);

  FormatOutput<Collection<User>> listUser();

  FormatOutput<UserOutputDto> getUser(String userGuid);

}
