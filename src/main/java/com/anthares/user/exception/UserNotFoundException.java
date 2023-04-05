package com.anthares.user.exception;

/** Class.
 *
 * @author abelK
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }

}
