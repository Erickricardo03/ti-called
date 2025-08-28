package com.example.demo.dto.response;

import java.util.Objects;

import com.example.demo.enums.Role;
import com.example.demo.model.User;

public class UserResponse {

	
	    private Long id;
	    private String name;
	    private String email;
	    private Role role;

	    public UserResponse(User user) {
	        this.id = user.getId();
	        this.name = user.getName();
	        this.email = user.getEmail();
	        this.role = user.getRole();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = getRole();
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
			UserResponse other = (UserResponse) obj;
			return Objects.equals(id, other.id);
		}
	
	
	}
