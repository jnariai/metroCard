package com.jnariai.user;

import com.jnariai.metrocard.Metrocard;
import com.jnariai.shared.PassengerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private String id;
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@Enumerated (EnumType.STRING)
	private PassengerType passengerType;
	@OneToMany (mappedBy = "user")
	private List<Metrocard> metrocards;


	public User(String name, String email, String password, PassengerType passengerType) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.passengerType = passengerType;
	}
}
