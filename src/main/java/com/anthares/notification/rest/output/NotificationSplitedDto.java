package com.anthares.notification.rest.output;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Class.
 *
 * @author abelK
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSplitedDto {

  private List<NotificationDto> sentList;
  private List<NotificationDto> pendingList;

}
