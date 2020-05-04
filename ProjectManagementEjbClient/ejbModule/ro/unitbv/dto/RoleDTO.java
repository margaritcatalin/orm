package ro.unitbv.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int roleId;
	private String roleDescription;
	private String roleName;
	private List<RightDTO> rights;
	private List<IdentityDTO> identities;
	private List<ResourceDTO> resources;

	public RoleDTO() {
		super();
	}

	public RoleDTO(int roleId, String roleDescription, String roleName, List<RightDTO> rights,
			List<IdentityDTO> identities, List<ResourceDTO> resources) {
		super();
		this.roleId = roleId;
		this.roleDescription = roleDescription;
		this.roleName = roleName;
		this.rights = rights;
		this.identities = identities;
		this.resources = resources;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RightDTO> getRights() {
		return rights;
	}

	public void setRights(List<RightDTO> rights) {
		this.rights = rights;
	}

	public List<IdentityDTO> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdentityDTO> identities) {
		this.identities = identities;
	}

	public List<ResourceDTO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceDTO> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", roleDescription=" + roleDescription + ", roleName=" + roleName
				+ ", rights=" + rights + ", identities=" + identities + ", resources=" + resources + "]";
	}

}
