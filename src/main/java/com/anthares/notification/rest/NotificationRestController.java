package com.anthares.notification.rest;

import com.anthares.commons.rest.input.FormatInput;
import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.notification.rest.input.NotificationInputDto;
import com.anthares.notification.rest.output.NotificationDto;
import com.anthares.notification.rest.output.NotificationSplitedDto;
import com.anthares.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Class.
 *
 * @author abelK
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationRestController {
  private final NotificationService notificationService;

  @PostMapping
  public FormatOutput<NotificationDto> createNotification(
      @RequestBody FormatInput<NotificationInputDto> createNotificationInputDto) {
    return notificationService.saveNotification(createNotificationInputDto.getData());
  }

  @GetMapping
  public FormatOutput<NotificationSplitedDto> listNotification(
      @RequestParam String userGuid) {
    return notificationService.listNotification(userGuid);
  }

}
