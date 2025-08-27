package com.example.demo.dto.request;

import com.example.demo.enums.TicketStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateTicketStatusRequest(
        @NotNull TicketStatus status
) {}
