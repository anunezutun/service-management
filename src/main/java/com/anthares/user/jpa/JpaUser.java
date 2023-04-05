package com.anthares.user.jpa;

import com.anthares.commons.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Interface.
 *
 * @author abelK
 */
public interface JpaUser extends JpaRepository<User, String> {

  Optional<User> findByGuid(String guid);

  Optional<User> findByUsernameIgnoreCaseAndPassword(String username, String password);

  Optional<User> findByUsernameIgnoreCase(String username);

}
