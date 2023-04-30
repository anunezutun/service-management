package com.anthares.commons.util;

public class Commons {
  public static String joinString(String... texts) {
    StringBuilder joinMessage = new StringBuilder();
    for (String element : texts) {
      joinMessage.append(element);
    }
    return joinMessage.toString();
  }
}
