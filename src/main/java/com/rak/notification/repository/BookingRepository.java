package com.rak.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rak.notification.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserId(Long userId);
}
