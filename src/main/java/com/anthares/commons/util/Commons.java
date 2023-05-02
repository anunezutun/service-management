package com.anthares.commons.util;

/** Class.
 *
 * @author abelK
 */
public class Commons {

  /** Method.
   *
   */
  public static String joinString(String... texts) {
    StringBuilder joinMessage = new StringBuilder();
    for (String element : texts) {
      joinMessage.append(element);
    }
    return joinMessage.toString();
  }
}
