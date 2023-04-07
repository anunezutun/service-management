package com.anthares.commons.model;

import com.anthares.commons.guid.Constants;
import java.time.LocalDateTime;
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
@Table(schema = "public", name = "customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  Customer {

  @Id
  @GeneratedValue(generator = "generate_guid")
  @GenericGenerator(name = "generate_guid", strategy = Constants.GENERATE_GUID_PACKAGE)
  private String guid;

  @Column(name = "user_guid")
  private String userGuid;

  private String names;

  @Column(name = "business_name")
  private String businessName;

  @Column(name = "document_type")
  private String documentType;

  @Column(name = "document_number")
  private String documentNumber;

  @Column(name = "phone_number")
  private String phoneNumber;

  private String email;

  @Column(name = "sol_user")
  private String solUser;

  @Column(name = "sol_key")
  private String solKey;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

}
