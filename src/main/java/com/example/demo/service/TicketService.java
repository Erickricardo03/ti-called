package com.example.demo.service;

import com.example.demo.dto.request.CreateTicketRequest;
import com.example.demo.dto.request.UpdateTicketStatusRequest;
import com.example.demo.dto.response.TicketResponse;
import com.example.demo.enums.TicketStatus;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Ticket; 
import com.example.demo.model.User;
import com.example.demo.repository.TicketRepository; 
import com.example.demo.repository.UserRepository;

import org.springframework.security.core.Authentication; 
import org.springframework.security.core.context.SecurityContextHolder; 
import org.springframework.security.authentication.AnonymousAuthenticationToken; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional; 

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public TicketResponse createTicket(CreateTicketRequest request) {
        User requester = userRepository.findById(request.getRequesterId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + request.getRequesterId()));
        
        User assignee = null;
        if (request.getAssigneeId() != null) {
            assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + request.getAssigneeId()));
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setCategory(request.getCategory());
        ticket.setRequester(requester);
        ticket.setAssignee(assignee); 
        ticket.setStatus(TicketStatus.OPEN);

        return TicketResponse.fromEntity(ticketRepository.save(ticket));
    }

   
    public List<TicketResponse> getAllTickets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Ticket> tickets;

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken || !authentication.isAuthenticated()) {
            System.out.println("Modo Portfólio Ativado: Retornando todos os tickets.");
            tickets = ticketRepository.findAll();
            
        } else {
            
            String username = authentication.getName();
            
            Optional<User> userOptional = userRepository.findByEmail(username);
            
            if (userOptional.isEmpty()) {
                tickets = ticketRepository.findAll(); 
            } else {
                User user = userOptional.get();

                boolean isAdmin = user.getRole().name().equals("ROLE_ADMIN"); 

                if (isAdmin) {
                    tickets = ticketRepository.findAll();
                } else {
                    tickets = ticketRepository.findByRequester(user); 
                }
            }
        }
        
        return tickets.stream()
                .map(TicketResponse::fromEntity)
                .toList();
    }

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket não encontrado com ID: " + id));
        return TicketResponse.fromEntity(ticket);
    }

    public TicketResponse updateTicketStatus(Long id, UpdateTicketStatusRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket não encontrado com ID: " + id));

        ticket.setStatus(request.status());
        return TicketResponse.fromEntity(ticketRepository.save(ticket));
    }

    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket não encontrado com ID: " + id));

        ticketRepository.delete(ticket);
    }
}