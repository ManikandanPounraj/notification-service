package com.rak.notification;

import com.rak.notification.client.EventClient;
import com.rak.notification.client.UserClient;
import com.rak.notification.model.EventDTO;
import com.rak.notification.model.NotificationRequest;
import com.rak.notification.model.UserDTO;
import com.rak.notification.service.NotificationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private UserClient userClient;
    private EventClient eventClient;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        userClient = mock(UserClient.class);
        eventClient = mock(EventClient.class);
        notificationService = new NotificationService(userClient, eventClient);
    }

    @Test
    void testSendBookingNotification() {
        NotificationRequest req = NotificationRequest.builder()
                .userId(1L)
                .eventId(2L)
                .type("BOOKING")
                .paymentAmount(500.0)
                .build();

        UserDTO user = UserDTO.builder().id(1L).name("Alice").build();
        EventDTO event = EventDTO.builder().id(2L).name("Spring Fest").date(LocalDate.of(2025, 6, 1)).build();

        when(userClient.getUser(1L)).thenReturn(user);
        when(eventClient.getEvent(2L)).thenReturn(event);

        String result = notificationService.sendNotification(req);

        assertTrue(result.contains("'Spring Fest'"));
        verify(userClient).getUser(1L);
        verify(eventClient).getEvent(2L);
    }

    @Test
    void testSendCancellationNotification() {
        NotificationRequest req = NotificationRequest.builder()
                .userId(1L)
                .eventId(2L)
                .type("CANCELLATION")
                .build();

        UserDTO user = UserDTO.builder().id(1L).name("Bob").build();
        EventDTO event = EventDTO.builder().id(2L).name("Java Conference").date(LocalDate.of(2025, 6, 1)).build();

        when(userClient.getUser(1L)).thenReturn(user);
        when(eventClient.getEvent(2L)).thenReturn(event);

        String result = notificationService.sendNotification(req);

        assertTrue(result.contains("'Java Conference'"));
    }

    @Test
    void testSendNotification_UnknownType() {
        NotificationRequest req = NotificationRequest.builder()
                .userId(1L)
                .eventId(2L)
                .type("REMINDER") // invalid type
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                notificationService.sendNotification(req)
        );

        assertEquals("Unknown notification type", exception.getMessage());
    }
}
