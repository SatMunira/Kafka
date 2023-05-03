package com.bezkoder.spring.security.postgresql.models;

import com.bezkoder.spring.security.postgresql.models.enums.AuthProvider;
import com.bezkoder.spring.security.postgresql.models.enums.ERole;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@Enumerated
	private AuthProvider provider;

	private String providerId;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;


	@Column(name = "reset_password_token")
	private String resetPasswordToken;
	@Size(max = 120)
	private String password;


	@ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<ERole> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
