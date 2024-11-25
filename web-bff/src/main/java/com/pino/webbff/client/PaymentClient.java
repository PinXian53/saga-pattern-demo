package com.pino.webbff.client;

import com.pino.openapi.payment.PaymentApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment", url = "${feign-url.payment}")
public interface PaymentClient extends PaymentApi {
}
