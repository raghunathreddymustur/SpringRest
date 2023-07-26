package com.raghu.reponse.status.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BANDWIDTH_LIMIT_EXCEEDED;

@ResponseStatus(code = BANDWIDTH_LIMIT_EXCEEDED, reason = "Custom Bandwidth Limit Exceeded Exception")
public class CustomBandwidthLimitExceededException extends RuntimeException {
}
