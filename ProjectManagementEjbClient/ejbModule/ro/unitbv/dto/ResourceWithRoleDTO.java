package ro.unitbv.dto;

public class ResourceWithRoleDTO {
	private int identityId;

	private int resourceId;

	private int roleId;

	public ResourceWithRoleDTO(int identityId, int resourceId, int roleId) {
		super();
		this.identityId = identityId;
		this.resourceId = resourceId;
		this.roleId = roleId;
	}

	public ResourceWithRoleDTO() {
		super();
	}

	public int getIdentityId() {
		return identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "ResourceWithRoleDTO [identityId=" + identityId + ", resourceId=" + resourceId + ", roleId=" + roleId
				+ "]";
	}

}
