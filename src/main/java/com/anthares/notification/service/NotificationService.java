package com.anthares.notification.service;

import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.notification.rest.input.NotificationInputDto;
import com.anthares.notification.rest.output.NotificationDto;
import com.anthares.notification.rest.output.NotificationSplitedDto;

/** Class.
 *
 * @author abelK
 */
public interface NotificationService {

  FormatOutput<NotificationDto> saveNotification(NotificationInputDto createNotificationInputDto);

  FormatOutput<NotificationSplitedDto> listNotification(String userGuid);

}
