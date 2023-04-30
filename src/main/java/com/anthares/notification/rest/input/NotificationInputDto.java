package com.anthares.notification.rest.input;

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
public class NotificationInputDto {

  private String userGuid;

  private String message;

  private Integer hour;

  private Integer minute;

  private String date;

  private String status;

  private String phoneNumber;

}
