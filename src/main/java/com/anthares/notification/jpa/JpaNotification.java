package com.anthares.notification.jpa;

import com.anthares.commons.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/** Interface.
 *
 * @author abelK
 */
public interface JpaNotification extends JpaRepository<Notification, String> {

  Collection<Notification> findByUserGuid(String guid);


  Collection<Notification> findByYearAndMonthAndDayAndHourAndMinute(@Param("year") Integer year,
                                                @Param("month") Integer month,
                                                @Param("day") Integer day,
                                                @Param("hour") Integer hour,
                                                @Param("minute") Integer minute);

}
