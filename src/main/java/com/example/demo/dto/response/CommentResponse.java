package com.example.demo.dto.response;

import java.time.Instant;

public record CommentResponse ( 
		
		
		Long id,
        Long ticketId,
        Long authorId,
        String message,
        Instant createdAt)






{}
