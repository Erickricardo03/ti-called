package com.example.demo.model;

import java.time.Instant;
import java.util.Objects;

import com.example.demo.enums.Priority;
import com.example.demo.enums.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	void onCreate() {
		this.createAt = Instant.now();
		this.status = this.status == null ? TicketStatus.OPEN : this.status;
		this.priority = this.priority == null ? Priority.MEDIUM : this.priority;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public Instant getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Instant createAt) {
		this.createAt = createAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getAssignee() {
		return assignee;
	}
	
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	@PreUpdate
	void onUpdate() {
		this.updateAt = Instant.now();
	}

	

	

}
