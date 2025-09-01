package com.example.demo.dto.response;

import com.example.demo.enums.Priority;
import com.example.demo.enums.TicketStatus;
import com.example.demo.model.Ticket;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private TicketStatus status;
    private String category;
    private Long requesterId;
    private Long assigneeId;

    public static TicketResponse fromEntity(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .priority(ticket.getPriority())
                .status(ticket.getStatus())
                .category(ticket.getCategory())
                .requesterId(ticket.getRequester() != null ? ticket.getRequester().getId() : null)
                .assigneeId(ticket.getAssignee() != null ? ticket.getAssignee().getId() : null)
                .build();
    }
}
