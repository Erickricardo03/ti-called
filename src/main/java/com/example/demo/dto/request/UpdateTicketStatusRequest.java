package com.example.demo.dto.request;

import com.example.demo.enums.TicketStatus;

public record UpdateTicketStatusRequest(
        TicketStatus status
) {}
