package br.gov.sp.fatec.bank.clients.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CLI_CLIENT")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLI_ID")
	private Long id;

	@Column(name = "CLI_NAME")
	private String name;

	@Column(name = "CLI_SSN")
	private String ssn;

	@Column(name = "CLI_BIRTHDATE")
	private LocalDate birthdate;

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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
}
