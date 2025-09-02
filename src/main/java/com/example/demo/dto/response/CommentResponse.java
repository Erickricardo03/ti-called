package com.example.demo.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;

import com.example.demo.model.Comment;

public record CommentResponse(
        Long id,
        Long ticketId,
        Long authorId,
        String message,
        LocalDateTime createdAt 
) {
    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getTicket() != null ? comment.getTicket().getId() : null,
                comment.getUser() != null ? comment.getUser().getId() : null,
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
