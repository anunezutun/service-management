package com.anthares.customer.rest.output;

import java.util.List;
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
public class CustomerListDto {

  private String userGuid;

  private String userNames;

  private String username;

  private List<CustomerDto> customerList;

}
