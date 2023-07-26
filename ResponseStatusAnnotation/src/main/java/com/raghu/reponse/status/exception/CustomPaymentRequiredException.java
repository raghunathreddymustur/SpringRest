package com.raghu.reponse.status.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.PAYMENT_REQUIRED;

@ResponseStatus(code = PAYMENT_REQUIRED, reason = "Custom Payment Required Error")
public class CustomPaymentRequiredException extends RuntimeException {
}
