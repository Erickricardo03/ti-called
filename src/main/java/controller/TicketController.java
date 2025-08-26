package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.CreateTicketRequest;
import com.example.demo.dto.request.UpdateTicketStatusRequest;
import com.example.demo.dto.response.TicketResponse;

import jakarta.validation.Valid;
import service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody @Valid CreateTicketRequest request) {
        return ResponseEntity.ok(ticketService.create(request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TicketResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateTicketStatusRequest request
    ) {
        return ResponseEntity.ok(ticketService.updateStatus(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }
}
