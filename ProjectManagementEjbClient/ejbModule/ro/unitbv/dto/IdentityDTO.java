package ro.unitbv.dto;

import java.io.Serializable;
import java.util.List;

public class IdentityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int identityId;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private String username;
	private OrganizationDTO organization;
	private List<ResourceDTO> resources;
	private List<RoleDTO> roles;
	private boolean isAdmin;
	private boolean isModerator;

	public IdentityDTO() {
		super();
	}

	public IdentityDTO(int identityId, String email, String firstname, String lastname, String password,
			String username) {
		super();
		this.identityId = identityId;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.username = username;
	}

	public IdentityDTO(int identityId, String email, String firstname, String lastname, String password,
			String username, OrganizationDTO organization, List<ResourceDTO> resources, List<RoleDTO> roles) {
		super();
		this.identityId = identityId;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.username = username;
		this.organization = organization;
		this.resources = resources;
		this.roles = roles;
	}

	public int getIdentityId() {
		return identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrganizationDTO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}

	public List<ResourceDTO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceDTO> resources) {
		this.resources = resources;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	public boolean isModerator() {
		return isModerator;
	}

	public void setModerator(boolean isModerator) {
		this.isModerator = isModerator;
	}

	@Override
	public String toString() {
		return "IdentityDTO [identityId=" + identityId + ", email=" + email + ", firstname=" + firstname + ", lastname="
				+ lastname + ", password=" + password + ", username=" + username + ", organization=" + organization
				+ ", resources=" + resources + ", roles=" + roles + "]";
	}

}
