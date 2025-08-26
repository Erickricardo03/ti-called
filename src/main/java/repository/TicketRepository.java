package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import enums.TicketStatus;
import model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	Page<Ticket> findByStatus(TicketStatus status, Pageable pageable);
}
