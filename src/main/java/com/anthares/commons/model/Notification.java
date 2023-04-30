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
@Table(schema = "public", name = "notification")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

  @Id
  @GeneratedValue(generator = "generate_guid")
  @GenericGenerator(name = "generate_guid", strategy = Constants.GENERATE_GUID_PACKAGE)
  private String guid;

  @Column(name = "user_guid")
  private String userGuid;

  @Column(name = "remind_date")
  private LocalDateTime remindDate;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  private Integer year;

  private Integer month;

  private Integer day;

  private Integer hour;

  private Integer minute;

  @Column(name = "phone_number")
  private String phoneNumber;

  private String message;

  private String status;

}
