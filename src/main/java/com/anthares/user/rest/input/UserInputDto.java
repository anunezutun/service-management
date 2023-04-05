package com.anthares.user.rest.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Class.
 *
 * @author abelK
 */
@Getter
@Setter
@ToString
public class UserInputDto {

  private String guid;

  private String names;

  private String username;

  private String password;

  private String phoneNumber;

  private String email;
}
