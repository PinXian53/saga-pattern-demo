package com.pino.webbff.config;

import io.micrometer.tracing.Tracer;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final Tracer tracer;

    public FeignConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Bean
    public RequestInterceptor tracingFeignInterceptor() {
        return requestTemplate -> {
            if (tracer.currentSpan() != null) {
                var traceId = tracer.currentSpan().context().traceId();
                var spanId = tracer.currentSpan().context().spanId();
                var traceFlags = tracer.currentSpan().context().sampled() ? "01" : "00";
                requestTemplate.header("traceparent", String.format("00-%s-%s-%s", traceId, spanId, traceFlags));
            }
        };
    }
}
