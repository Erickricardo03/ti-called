package com.example.demo.dto.request;

import com.example.demo.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketStatusRequest {

    @NotNull
    private TicketStatus status;
}
