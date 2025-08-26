package service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.CreateTicketRequest;
import com.example.demo.dto.request.UpdateTicketStatusRequest;
import com.example.demo.dto.response.TicketResponse;

import enums.TicketStatus;
import jakarta.transaction.Transactional;
import model.Ticket;
import model.User;
import repository.TicketRepository;
import repository.UserRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TicketResponse create(CreateTicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.title());
        ticket.setDescription(request.description());
        ticket.setPriority(request.priority());
        ticket.setCategory(request.category());
        ticket.setStatus(TicketStatus.OPEN);

        User requester = userRepository.findById(request.requesterId())
                .orElseThrow(() -> new RuntimeException("Requester not found"));
        ticket.setRequester(requester);

        if (request.assigneeId() != null) {
            User assignee = userRepository.findById(request.assigneeId())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
            ticket.setAssignee(assignee);
        }

        Ticket saved = ticketRepository.save(ticket);
        return TicketResponse.fromEntity(saved);
    }

    @Transactional
    public TicketResponse updateStatus(Long ticketId, UpdateTicketStatusRequest request) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(request.status());

        Ticket updated = ticketRepository.save(ticket);
        return TicketResponse.fromEntity(updated);
    }

    @Transactional
    public TicketResponse getTicketById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return TicketResponse.fromEntity(ticket);
    }
}
