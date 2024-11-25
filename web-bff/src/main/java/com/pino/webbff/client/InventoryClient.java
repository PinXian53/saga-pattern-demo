package com.pino.webbff.client;

import com.pino.openapi.inventory.InventoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "inventory", url = "${feign-url.inventory}")
public interface InventoryClient extends InventoryApi {
}
