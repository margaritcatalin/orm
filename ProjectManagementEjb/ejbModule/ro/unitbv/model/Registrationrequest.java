package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the registrationrequest database table.
 * 
 */
@Entity
@Table(name = "registrationrequest")
@NamedQuery(name = "Registrationrequest.findAll", query = "SELECT rq FROM Registrationrequest rq")
public class Registrationrequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idregistrationrequest;

	private String username;
	private String firstname;
	private String lastname;
	private String resource;
	private String role;
	private String password;
	private String organization;

	public int getIdregistrationrequest() {
		return idregistrationrequest;
	}

	public void setIdregistrationrequest(int idregistrationrequest) {
		this.idregistrationrequest = idregistrationrequest;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}