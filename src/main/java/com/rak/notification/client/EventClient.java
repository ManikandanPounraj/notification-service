package com.rak.notification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rak.notification.model.EventDTO;

@FeignClient(name = "event-service", url = "http://localhost:8082")
public interface EventClient {
	@GetMapping("/api/events/{id}")
	EventDTO getEvent(@PathVariable Long id);
}
