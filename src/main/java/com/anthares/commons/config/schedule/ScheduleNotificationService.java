package com.anthares.commons.config.schedule;

import com.anthares.commons.jpa.JpaTwilio;
import com.anthares.commons.util.Commons;
import com.anthares.commons.util.Constants;
import com.anthares.notification.jpa.JpaNotification;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Class.
 *
 * @author abelK
 */
@Component
@EnableScheduling
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleNotificationService {

  private final JpaNotification jpaNotification;
  private final JpaTwilio jpaTwilio;
  private static final String TIME_ZONE = "America/Lima";

  /** Method.
   *
   */
  @Scheduled(cron = "0 */15 7-21 * * *", zone = TIME_ZONE)
  public void scheduleFixedDelayTask() {

    LocalDateTime currentDate
        = LocalDateTime.now();

    Integer year = currentDate.getYear();
    Integer month = currentDate.getMonthValue();
    Integer day = currentDate.getDayOfMonth();
    Integer hour = currentDate.getHour();
    Integer minute = currentDate.getMinute();
    log.info("Looking for notifications ... {}", LocalDateTime.now());

    jpaNotification.findByYearAndMonthAndDayAndHourAndMinuteAndStatus(
        year, month, day, hour, minute, "Pendiente")
        .forEach(notification -> {
          log.info("Processing notification with id: " + notification.getGuid());
          sendNotification(notification.getPhoneNumber(), notification.getMessage());
          notification.setStatus("Enviado");
          jpaNotification.save(notification);
        });
  }

  private void sendNotification(String phone, String messageBody) {
    String phoneReceiver = Commons.joinString(Constants.WHATS_APP_PREFIX,
        Constants.PERUVIAN_PHONE_CODE, phone);
    var twilio = jpaTwilio.findAll().stream().findFirst().get();
    Twilio.init(twilio.getSid(), twilio.getToken());
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber(phoneReceiver),
            new com.twilio.type
                .PhoneNumber(Commons.joinString(Constants.WHATS_APP_PREFIX,
                twilio.getPhoneServer())),
            messageBody)
        .create();
    //se puede hacer algo con la respuesta
  }

}
