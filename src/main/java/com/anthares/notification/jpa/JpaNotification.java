package com.anthares.notification.jpa;

import com.anthares.commons.model.Notification;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/** Interface.
 *
 * @author abelK
 */
public interface JpaNotification extends JpaRepository<Notification, String> {

  Collection<Notification> findByUserGuidOrderByRemindDate(String guid);


  Collection<Notification> findByYearAndMonthAndDayAndHourAndMinuteAndStatus(
      @Param("year") Integer year,
      @Param("month") Integer month,
      @Param("day") Integer day,
      @Param("hour") Integer hour,
      @Param("minute") Integer minute,
      @Param("status") String status);

}
