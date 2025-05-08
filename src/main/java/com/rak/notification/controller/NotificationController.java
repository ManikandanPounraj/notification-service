package com.rak.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rak.notification.model.NotificationRequest;
import com.rak.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

	private final NotificationService service;

	@PostMapping
	@Operation(summary = "Send user notification", description = "Notifies users of booking or cancellation using User & Event data")
	public ResponseEntity<String> send(@RequestBody NotificationRequest req) {
		return ResponseEntity.ok(service.sendNotification(req));
	}
}
