package com.example.demo.dto.response;

import java.time.Instant;

import enums.Priority;
import enums.TicketStatus;
import jakarta.transaction.Transactional;
import model.Ticket;
import repository.TicketRepository;

public record TicketResponse (
		
		
		
		Long id,
        String title,
        String description,
        Priority priority,
        TicketStatus status,
        Long requesterId,
        Long assigneeId,
        String category,
        Instant createdAt)







{

	public static TicketResponse fromEntity(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}}

