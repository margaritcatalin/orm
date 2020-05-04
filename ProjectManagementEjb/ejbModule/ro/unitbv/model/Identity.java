package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the identity database table.
 * 
 */
@Entity
@NamedQuery(name="Identity.findAll", query="SELECT i FROM Identity i")
public class Identity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identityId;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private String username;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="organisationId")
	private Organization organization;

	//bi-directional many-to-many association to Resource
	@ManyToMany
	@JoinTable(name="identityroleresources",
    joinColumns = @JoinColumn(name = "identityId", 
                              referencedColumnName = "identityId"), 
    inverseJoinColumns = @JoinColumn(name = "resourceId", 
                              referencedColumnName = "resourceId"))
	private List<Resource> resources;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(name="identityroleresources",
    joinColumns = @JoinColumn(name = "identityId", 
                              referencedColumnName = "identityId"), 
    inverseJoinColumns = @JoinColumn(name = "roleId", 
                              referencedColumnName = "roleId"))
	private List<Role> roles;

	public Identity() {
	}

	public int getIdentityId() {
		return this.identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}