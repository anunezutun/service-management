package com.anthares.commons.rest.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Class.
 *
 * @author abelK
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormatOutput<T> {

  private T data;

  private String code;

  private String message;

}
