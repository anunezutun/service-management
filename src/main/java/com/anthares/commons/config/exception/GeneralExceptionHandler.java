package com.anthares.commons.config.exception;

import com.anthares.commons.rest.output.FormatOutput;
import com.anthares.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Class.
 *
 * @author abelK
 */
@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

  /** Method.
   *
   */
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<FormatOutput<Object>> handleMethodSaleReportNotFoundException(
      UserNotFoundException exception) {
    log.error(exception.getMessage(), exception);

    return buildResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
        exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  private ResponseEntity<FormatOutput<Object>> buildResponse(
      String code,
      String message,
      HttpStatus httpStatus) {
    FormatOutput<Object> output = new FormatOutput<>();
    output.setCode(code);
    output.setMessage(message);
    return ResponseEntity.status(httpStatus).body(output);
  }

}
