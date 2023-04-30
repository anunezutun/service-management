package com.anthares.notification.service.impl;

import com.anthares.commons.model.Notification;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.commons.service.CommonService;
import com.anthares.notification.jpa.JpaNotification;
import com.anthares.notification.rest.input.NotificationInputDto;
import com.anthares.notification.rest.output.NotificationDto;
import com.anthares.notification.service.NotificationService;
import com.anthares.notification.util.NotificationConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
public class NotificationServiceImpl extends CommonService implements NotificationService {

  private final JpaNotification jpaNotification;

  @Override
  public FormatOutput<NotificationDto> saveNotification(
      NotificationInputDto createNotificationInputDto) {
    createNotificationInputDto.setStatus("active");
    Notification notification = setDataNotification(createNotificationInputDto);
    notification.setCreationDate(LocalDateTime.now());

    LocalDate inputDate
        = LocalDate.parse(createNotificationInputDto.getDate());

    Integer year = inputDate.getYear();
    Integer month = inputDate.getMonthValue();
    Integer day = inputDate.getDayOfMonth();
    Integer hour = createNotificationInputDto.getHour();
    Integer minute = createNotificationInputDto.getMinute();

    LocalDateTime remindDate =
        LocalDateTime.of(year,
            month,
            day,
            hour,
            minute);

    notification.setRemindDate(remindDate);
    notification.setYear(year);
    notification.setMonth(month);
    notification.setDay(day);
    notification.setHour(hour);
    notification.setMinute(minute);

    jpaNotification.save(notification);

    return buildResponse(buildNotificationDto(notification),
        NotificationConstants.NOTIFICATION_CREATED_CODE,
        NotificationConstants.NOTIFICATION_CREATED_MSG);
  }

  @Override
  public FormatOutput<List<NotificationDto>> listNotification(String userGuid) {
    Collection<Notification> notificationList = jpaNotification.findByUserGuid(userGuid);
    List<NotificationDto> notificationOutputDtoList = new ArrayList<>();
    for (Notification notification : notificationList) {
      NotificationDto outputDto = buildNotificationDto(notification);
      notificationOutputDtoList.add(outputDto);
    }
    return buildResponse(notificationOutputDtoList,
        NotificationConstants.NOTIFICATION_SUCCESS_PROCESS_CODE,
        NotificationConstants.NOTIFICATION_SUCCESS_PROCESS_MSG);
  }

  private Notification setDataNotification(NotificationInputDto notificationInputDto) {
    return Notification.builder()
        .userGuid(notificationInputDto.getUserGuid())
        .message(notificationInputDto.getMessage())
        .status(notificationInputDto.getStatus())
        .phoneNumber(notificationInputDto.getPhoneNumber())
        .build();
  }

  private NotificationDto buildNotificationDto(Notification notification) {
    return NotificationDto.builder()
        .guid(notification.getGuid())
        .remindDate(notification.getRemindDate())
        .creationDate(notification.getCreationDate())
        .message(notification.getMessage())
        .status(notification.getStatus())
        .phone(notification.getPhoneNumber())
        .build();
  }

}
