package com.example.demo.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.CreateTicketRequest;
import com.example.demo.dto.request.UpdateTicketStatusRequest;
import com.example.demo.dto.response.TicketResponse;
import com.example.demo.enums.Priority;
import com.example.demo.enums.Role;
import com.example.demo.enums.TicketStatus;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketService;

@SpringBootTest
@Transactional
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateTicket() {
        User user = User.builder()
                .name("Carlinhos")
                .email("carlinhos.works@gmail.com")
                .password("123456")   
                .role(Role.END_USER)  
                .build();

        userRepository.save(user);

        CreateTicketRequest request = new CreateTicketRequest(
                "Erro no sistema",
                "O sistema caiu",
                Priority.HIGH,
                user.getId(),
                null,
                "Software"
        );

        TicketResponse response = ticketService.createTicket(request);

        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo("Erro no sistema");
        assertThat(response.getStatus()).isEqualTo(TicketStatus.OPEN);
    }

    @Test
    void shouldUpdateTicketStatus() {
        User requester = User.builder()
                .name("Carlinhos")
                .email("carlinhos.works@gmail.com")
                .password("123456")  
                .role(Role.END_USER)  
                .build();

        userRepository.save(requester);

        CreateTicketRequest request = new CreateTicketRequest(
                "Banco fora",
                "Erro no banco",
                Priority.MEDIUM,
                requester.getId(),
                null,
                "Backend"
        );

        TicketResponse created = ticketService.createTicket(request);

        UpdateTicketStatusRequest updateRequest =
                new UpdateTicketStatusRequest(TicketStatus.IN_PROGRESS);

        TicketResponse updated = ticketService.updateTicketStatus(created.getId(), updateRequest);

        assertThat(updated.getStatus()).isEqualTo(TicketStatus.IN_PROGRESS);
    }
}
