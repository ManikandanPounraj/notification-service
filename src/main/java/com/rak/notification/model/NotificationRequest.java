package com.rak.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {
	private Long userId;
	private Long eventId;
	private int numberOfTickets;
	private double paymentAmount;
	private String type; // BOOKING or CANCELLATION
}
