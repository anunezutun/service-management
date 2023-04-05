package com.anthares.commons.service;

import com.anthares.commons.rest.output.FormatOutput;

/** Class.
 *
 * @author abelK
 */
public abstract class CommonService {

  /** Method.
   *
   */
  public <T> FormatOutput<T> buildResponse(T t, String code, String message) {

    FormatOutput<T> customerFormatOutput = new FormatOutput<>();
    customerFormatOutput.setData(t);
    customerFormatOutput.setCode(code);
    customerFormatOutput.setMessage(message);
    return customerFormatOutput;
  }

}
