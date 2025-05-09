package com.rak.notification.service;

import org.springframework.stereotype.Service;

import com.rak.notification.client.EventClient;
import com.rak.notification.client.UserClient;
import com.rak.notification.model.EventDTO;
import com.rak.notification.model.NotificationRequest;
import com.rak.notification.model.UserDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

	private final UserClient userClient;
	private final EventClient eventClient;

	public String sendNotification(NotificationRequest req) {
		log.info("Preparing notification for user {} for event {}", req.getUserId(), req.getEventId());

		UserDTO user = userClient.getUser(req.getUserId());
		EventDTO event = eventClient.getEvent(req.getEventId());

		String message;
		if ("BOOKING".equalsIgnoreCase(req.getType())) {
			message = String.format("Hello %s, your booking for '%s' on %s is confirmed. Total: ₹%.2f", user.getName(),
					event.getName(), event.getDate(), req.getPaymentAmount());
		} else if ("CANCELLATION".equalsIgnoreCase(req.getType())) {
			message = String.format("Hello %s, your booking for '%s' on %s has been cancelled.", user.getName(),
					event.getName(),event.getDate());
		} else {
			throw new IllegalArgumentException("Unknown notification type");
		}

		log.info("[NOTIFICATION SENT]: {}", message);
		return message;
	}
}
