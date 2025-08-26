package Model;

import java.time.Instant;

import Enums.Priority;
import Enums.TicketStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity @Table(name = "tickets")
public class Ticket {

	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	
	@Column(nullable = false, length = 4000)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TicketStatus status;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Priority priority;
	
	@ManyToOne(optional = false)
	private User requester;
	
	@ManyToOne
	private User assignee;
	
	@Column(nullable = false, updatable = false)
	private Instant createAt;
	
	
	private Instant updateAt;
	
	private String category;
	
	@PrePersist
	void onCreate(){
	this.createAt = Instant.now();
	this.status = this.status == null ? TicketStatus.OPEN : this.status;
	this.priority = this.priority == null ? Priority.MEDIUM : this.priority;
	}



	@PreUpdate
	void onUpdate(){ this.updateAt = Instant.now(); }
	
	
	
	
	
}
