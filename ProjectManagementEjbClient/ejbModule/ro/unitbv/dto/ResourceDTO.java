package ro.unitbv.dto;

import java.io.Serializable;
import java.util.List;

public class ResourceDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int resourceId;
	private String resourceName;
	private List<AuthTypeDTO> authtypes;
	private List<IdentityDTO> identities;
	private List<RoleDTO> roles;

	public ResourceDTO() {
		super();
	}

	public ResourceDTO(int resourceId, String resourceName, List<AuthTypeDTO> authtypes) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.authtypes = authtypes;
	}

	public ResourceDTO(int resourceId, String resourceName) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
	}

	public ResourceDTO(int resourceId, String resourceName, List<AuthTypeDTO> authtypes, List<IdentityDTO> identities,
			List<RoleDTO> roles) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.authtypes = authtypes;
		this.identities = identities;
		this.roles = roles;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<AuthTypeDTO> getAuthtypes() {
		return authtypes;
	}

	public void setAuthtypes(List<AuthTypeDTO> authtypes) {
		this.authtypes = authtypes;
	}

	public List<IdentityDTO> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdentityDTO> identities) {
		this.identities = identities;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ResourceDTO [resourceId=" + resourceId + ", resourceName=" + resourceName + ", authtypes=" + authtypes
				+ ", identities=" + identities + ", roles=" + roles + "]";
	}

}
