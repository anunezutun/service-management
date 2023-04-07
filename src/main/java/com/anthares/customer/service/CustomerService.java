package com.anthares.customer.service;

import com.anthares.commons.model.Customer;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.customer.rest.input.CustomerInputDto;
import com.anthares.customer.rest.output.CustomerDto;
import com.anthares.customer.rest.output.CustomerListDto;
import java.util.Collection;

/** Interface.
 *
 * @author abelK
 */
public interface CustomerService {

  FormatOutput<CustomerDto> saveCustomer(CustomerInputDto createCustomerInputDto);

  FormatOutput<Customer> updateCustomer(CustomerInputDto updateCustomerInputDto);

  FormatOutput<Collection<CustomerListDto>> listCustomer(String userGuid);

  FormatOutput<CustomerDto> getCustomer(String customerGuid);

}
