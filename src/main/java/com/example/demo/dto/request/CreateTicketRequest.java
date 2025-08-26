package com.example.demo.dto.request;

import enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTicketRequest (  
		
		
		
		@NotBlank @Size(max = 120)
        String title,

        @NotBlank @Size(max = 4000)
        String description,

        @NotNull
        Priority priority,

        @NotNull
        Long requesterId,

       
        Long assigneeId,

        @Size(max = 60)
        String category) 





{}
