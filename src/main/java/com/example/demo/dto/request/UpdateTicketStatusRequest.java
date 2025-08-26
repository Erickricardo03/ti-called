package com.example.demo.dto.request;

import enums.TicketStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketStatusRequest (
        
		
		
		
		@NotNull
        Long ticketId,

        @NotNull
        TicketStatus status
) 


{}
