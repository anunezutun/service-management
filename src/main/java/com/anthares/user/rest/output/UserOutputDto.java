package com.anthares.user.rest.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** Class.
 *
 * @author abelK
 */
@Getter
@Setter
@Builder
public class UserOutputDto {

  private String guid;

  private String names;

  private String username;

  private String phoneNumber;

  private String email;

  private String password;

}
