package com.anthares.customer.rest.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Class.
 *
 * @author abelK
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

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

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime creationDate;


}
