package company.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Entity
public class Partner implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5518272182836833262L;
	@Id
	String name;
	String firstName;
	String email;
	String phone;
	@ManyToOne
	Campaign campaign;
	
	public Partner(String name, String firstName, String email, String phone) {
		this.name = name;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
	}
}
