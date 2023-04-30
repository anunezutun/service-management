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
@Table(schema = "public", name = "twilio")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Twilio {

  @Id
  @GeneratedValue(generator = "generate_guid")
  @GenericGenerator(name = "generate_guid", strategy = Constants.GENERATE_GUID_PACKAGE)
  private String guid;

  private String sid;

  private String token;

  @Column(name = "phone_server")
  private String phoneServer;

}
