package com.anthares.notification.service;

import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.notification.rest.input.NotificationInputDto;
import com.anthares.notification.rest.output.NotificationDto;
import java.util.List;

/** Class.
 *
 * @author abelK
 */
public interface NotificationService {

  FormatOutput<NotificationDto> saveNotification(NotificationInputDto createNotificationInputDto);

  FormatOutput<List<NotificationDto>> listNotification(String userGuid);

}
