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
        User user = new User();
        user.setName("Carlinhos");
        user.setEmail("carlinhos.works@gmail.com");
        userRepository.save(user);

        CreateTicketRequest request = new CreateTicketRequest(
                "Erro no sistema",
                "O sistema caiu",
                Priority.HIGH,
                user.getId(),
                null,
                "Software"
        );

        TicketResponse response = ticketService.create(request);

        assertThat(response).isNotNull();
        assertThat(response.title()).isEqualTo("Erro no sistema");
        assertThat(response.status()).isEqualTo(TicketStatus.OPEN);
    }

    @Test
    void shouldUpdateTicketStatus() {
        User requester = new User();
        requester.setName("Carlinhos");
        requester.setEmail("carlinhos.works@gmail.com");
        userRepository.save(requester);

        CreateTicketRequest request = new CreateTicketRequest(
                "Banco fora",
                "Erro no banco",
                Priority.MEDIUM,
                requester.getId(),
                null,
                "Backend"
        );

        TicketResponse created = ticketService.create(request);

        UpdateTicketStatusRequest updateRequest = new UpdateTicketStatusRequest(TicketStatus.IN_PROGRESS);

        TicketResponse updated = ticketService.updateStatus(created.id(), updateRequest);

        assertThat(updated.status()).isEqualTo(TicketStatus.IN_PROGRESS);
    }
}
