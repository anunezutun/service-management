package com.anthares.commons.model;

import com.anthares.commons.guid.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/** Class.
 *
 * @author abelK
 */
@Entity
@Table(schema = "public", name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(generator = "generate_guid")
  @GenericGenerator(name = "generate_guid", strategy = Constants.GENERATE_GUID_PACKAGE)
  private String guid;

  private String names;

  @Column(name = "user_name")
  private String username;

  private String password;

  @Column(name = "phone_number")
  private String phoneNumber;

  private String email;

}
