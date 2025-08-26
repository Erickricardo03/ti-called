package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTicketIdOrderByCreatedAtAsc(Long ticketId);

}
