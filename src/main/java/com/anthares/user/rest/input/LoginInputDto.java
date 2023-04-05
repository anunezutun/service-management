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
public class LoginInputDto {

  private String username;

  private String password;

}
