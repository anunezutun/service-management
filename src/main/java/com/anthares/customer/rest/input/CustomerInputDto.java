package com.anthares.customer.rest.input;

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
public class CustomerInputDto {

  private String guid;

  private String userGuid;

  private String names;

  private String businessName;

  private String documentType;

  private String documentNumber;

  private String phoneNumber;

  private String email;

  private String solUser;

  private String solKey;

}
