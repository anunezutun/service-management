package com.anthares.customer.rest;

import com.anthares.commons.rest.input.FormatInput;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.customer.rest.input.CustomerInputDto;
import com.anthares.customer.rest.output.CustomerDto;
import com.anthares.customer.rest.output.CustomerListDto;
import com.anthares.customer.service.CustomerService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Class.
 *
 * @author abelK
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerRestController {

  private final CustomerService customerService;

  @PostMapping
  public FormatOutput<CustomerDto> createCustomer(
      @RequestBody FormatInput<CustomerInputDto> createCustomerInputDto) {
    return customerService.saveCustomer(createCustomerInputDto.getData());
  }

  @GetMapping("/list/{userGuid}")
  public FormatOutput<Collection<CustomerListDto>> listCustomer(
      @PathVariable("userGuid") String userGuid) {
    return customerService.listCustomer(userGuid);
  }

  @GetMapping("/{customerGuid}")
  public FormatOutput<CustomerDto> getCustomer(@PathVariable("customerGuid")
                                                     String customerGuid) {
    return customerService.getCustomer(customerGuid);
  }

  @PutMapping
  public FormatOutput<CustomerDto> updateCustomer(
      @RequestBody FormatInput<CustomerInputDto> updateCustomerInputDto) {
    return customerService.updateCustomer(updateCustomerInputDto.getData());
  }

}
