package com.anthares.customer.jpa;

import com.anthares.commons.model.Customer;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Interface.
 *
 * @author abelK
 */
public interface JpaCustomer extends JpaRepository<Customer, String> {

  Optional<Customer> findByGuid(String guid);

  Collection<Customer> findByUserGuid(String userGuid);

}
