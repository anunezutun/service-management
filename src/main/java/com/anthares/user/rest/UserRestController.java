package com.anthares.user.rest;

import com.anthares.commons.model.User;
import com.anthares.commons.rest.input.FormatInput;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.user.rest.input.LoginInputDto;
import com.anthares.user.rest.input.UserInputDto;
import com.anthares.user.rest.output.UserOutputDto;
import com.anthares.user.service.UserService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Class.
 *
 * @author abelK
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

  private final UserService userService;

  @PostMapping
  public FormatOutput<UserOutputDto> createUser(@RequestBody FormatInput<UserInputDto>
                                                 createUserInputDto) {
    return userService.saveUser(createUserInputDto.getData());
  }

  @PutMapping
  public FormatOutput<UserOutputDto> updateUser(@RequestBody FormatInput<UserInputDto>
                                               updateUserInputDto) {
    return userService.updateUser(updateUserInputDto.getData());
  }

  @PostMapping("/login")
  public FormatOutput<UserOutputDto> login(@RequestBody FormatInput<LoginInputDto>
                          loginInputDto) {
    return userService.login(loginInputDto.getData());
  }

  @GetMapping
  public FormatOutput<Collection<User>> listUser() {
    return userService.listUser();
  }

  @GetMapping("/{userGuid}")
  public FormatOutput<UserOutputDto> getUser(@PathVariable("userGuid")
                                                     String userGuid) {
    return userService.getUser(userGuid);
  }


}
