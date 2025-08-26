package Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import Enums.TicketStatus;
import Model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	Page<Ticket> findByStatus(TicketStatus status, Pageable pageable);
}
