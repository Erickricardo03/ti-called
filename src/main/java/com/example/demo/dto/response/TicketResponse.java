package com.example.demo.dto.response;

import com.example.demo.enums.Priority;
import com.example.demo.enums.TicketStatus;
import com.example.demo.model.Ticket;

public record TicketResponse(
        Long id,
        String title,
        String description,
        Priority priority,
        TicketStatus status,
        String category,
        Long requesterId,
        Long assigneeId
) {
    public static TicketResponse fromEntity(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCategory(),
                ticket.getRequester() != null ? ticket.getRequester().getId() : null,
                ticket.getAssignee() != null ? ticket.getAssignee().getId() : null
        );
    }
}
