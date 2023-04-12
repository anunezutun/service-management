package com.anthares.customer.service.impl;

import com.anthares.commons.model.Customer;
import com.anthares.commons.model.User;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.commons.service.CommonService;
import com.anthares.commons.util.Constants;
import com.anthares.customer.exception.CustomerNotFoundException;
import com.anthares.customer.jpa.JpaCustomer;
import com.anthares.customer.rest.input.CustomerInputDto;
import com.anthares.customer.rest.output.CustomerDto;
import com.anthares.customer.rest.output.CustomerListDto;
import com.anthares.customer.service.CustomerService;
import com.anthares.customer.util.CustomerConstants;
import com.anthares.user.jpa.JpaUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Class.
 *
 * @author abelK
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl extends CommonService implements CustomerService {

  private final JpaCustomer jpaCustomer;
  private final JpaUser jpaUser;

  @Override
  public FormatOutput<CustomerDto> saveCustomer(CustomerInputDto createCustomerInputDto) {

    var customer = setDataCustomer(createCustomerInputDto);
    customer.setCreationDate(LocalDateTime.now());
    jpaCustomer.save(customer);
    return buildResponse(buildCustomerDto(customer),
        CustomerConstants.CUSTOMER_CREATED_CODE,
        CustomerConstants.CUSTOMER_CREATED_MSG);
  }

  @Override
  public FormatOutput<CustomerDto> updateCustomer(CustomerInputDto updateCustomerInputDto) {
    var customer = setDataCustomer(updateCustomerInputDto);
    jpaCustomer.save(customer);
    return buildResponse(buildCustomerDto(customer),
        CustomerConstants.CUSTOMER_UPDATED_CODE,
        CustomerConstants.CUSTOMER_UPDATED_MSG);
  }

  private CustomerListDto getCustomerList(User user) {

    var customerListDto = new CustomerListDto();
    customerListDto.setUserGuid(user.getGuid());
    customerListDto.setUserNames(user.getNames());
    customerListDto.setUsername(user.getUsername());
    var customerOutputList = new ArrayList<CustomerDto>();
    jpaCustomer.findByUserGuid(user.getGuid())
        .forEach(customer -> customerOutputList.add(buildCustomerDto(customer)));
    customerListDto.setCustomerList(customerOutputList);
    return customerListDto;

  }

  @Override
  public FormatOutput<Collection<CustomerListDto>> listCustomer(String userGuid) {

    var userDefault = jpaUser.findByUsernameIgnoreCase(Constants.ANTHARES_USERNAME);
    var response = new ArrayList<CustomerListDto>();
    response.add(getCustomerList(userDefault.get()));
    if (!userDefault.get().getGuid().equals(userGuid)) {
      var user = jpaUser.findByGuid(userGuid);
      response.add(getCustomerList(user.get()));
    }
    return buildResponse(response,
        CustomerConstants.CUSTOMER_SUCCESS_PROCESS_CODE,
        CustomerConstants.CUSTOMER_SUCCESS_PROCESS_MSG);
  }

  @Override
  public FormatOutput<CustomerDto> getCustomer(String customerGuid) {
    Optional<Customer> customerOptional = jpaCustomer.findByGuid(customerGuid);
    if (customerOptional.isPresent()) {
      CustomerDto customerDto = buildCustomerDto(customerOptional.get());
      return buildResponse(customerDto,
          CustomerConstants.CUSTOMER_SUCCESS_PROCESS_CODE,
          CustomerConstants.CUSTOMER_SUCCESS_PROCESS_MSG);
    } else {
      throw new CustomerNotFoundException(CustomerConstants.CUSTOMER_NOT_FOUND_MSG);
    }
  }

  private Customer setDataCustomer(CustomerInputDto customerInputDto) {
    return Customer.builder()
        .guid(customerInputDto.getGuid() != null ? customerInputDto.getGuid() : null)
        .userGuid(customerInputDto.getUserGuid())
        .names(customerInputDto.getNames())
        .businessName(customerInputDto.getBusinessName())
        .documentType(customerInputDto.getDocumentType())
        .documentNumber(customerInputDto.getDocumentNumber())
        .phoneNumber(customerInputDto.getPhoneNumber())
        .email(customerInputDto.getEmail())
        .solUser(customerInputDto.getSolUser())
        .solKey(customerInputDto.getSolKey())
        .build();
  }

  private CustomerDto buildCustomerDto(Customer customer) {
    return CustomerDto.builder()
        .guid(customer.getGuid())
        .userGuid(customer.getUserGuid())
        .names(customer.getNames())
        .businessName(customer.getBusinessName())
        .documentType(customer.getDocumentType())
        .documentNumber(customer.getDocumentNumber())
        .phoneNumber(customer.getPhoneNumber())
        .email(customer.getEmail())
        .creationDate(customer.getCreationDate())
        .solUser(customer.getSolUser())
        .solKey(customer.getSolKey())
        .build();
  }
}
