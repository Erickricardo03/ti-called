package com.example.demo.dto.response;

import java.time.LocalDateTime;

import com.example.demo.enums.Priority;
import com.example.demo.enums.TicketStatus;
import com.example.demo.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private UserSummary requester; 
	private UserSummary assignee; 
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static TicketResponse fromEntity(Ticket ticket) {
		return TicketResponse.builder().id(ticket.getId()).title(ticket.getTitle()).description(ticket.getDescription())
				.priority(ticket.getPriority()).status(ticket.getStatus()).category(ticket.getCategory())
				.requester(ticket.getRequester() != null
						? new UserSummary(ticket.getRequester().getId(), ticket.getRequester().getName())
						: null)
				.assignee(ticket.getAssignee() != null
						? new UserSummary(ticket.getAssignee().getId(), ticket.getAssignee().getName())
						: null)
				.createdAt(ticket.getCreatedAt()).updatedAt(ticket.getUpdatedAt()).build();
	}
}
