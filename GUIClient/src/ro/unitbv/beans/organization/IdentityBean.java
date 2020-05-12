package ro.unitbv.beans.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.unitbv.dao.IdentityDaoRemote;
import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.OrganizationDTO;
import ro.unitbv.dto.ResourceDTO;
import ro.unitbv.dto.ResourceWithRoleDTO;
import ro.unitbv.dto.RoleDTO;

@ManagedBean
@SessionScoped
public class IdentityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IdentityDTO userDTO = new IdentityDTO();
	private IdentityDTO selectedUser = new IdentityDTO();
	private List<IdentityDTO> organizationMembers;
	private OrganizationDTO currentOrganization = new OrganizationDTO();
	private ResourceWithRoleDTO assingResource = new ResourceWithRoleDTO();
	private Map<String, String> resourcesRoles;
	@EJB
	IdentityDaoRemote identityDaoRemote;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		currentOrganization = (OrganizationDTO) facesContext.getExternalContext().getSessionMap()
				.get("selectedOrganization");
		initAllList();
	}

	public String createUser() {
		IdentityDTO user = identityDaoRemote.create(userDTO);
		user.setOrganization(currentOrganization);
		identityDaoRemote.update(user);
		initAllList();
		initUser(userDTO);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String deleteUser() {
		identityDaoRemote.delete(selectedUser.getIdentityId());
		initAllList();
		initUser(selectedUser);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String updateUser() {
		return "/identity/update-user.xhtml?faces-redirect=true";
	}

	public String doUpdateUser() {
		identityDaoRemote.update(selectedUser);
		initAllList();
		initUser(selectedUser);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String seeUserResources() {
		resourcesRoles = identityDaoRemote.getAllUserResources(selectedUser.getIdentityId());
		return "/identity/resources.xhtml?faces-redirect=true";
	}

	public String assignResourceToUser() {
		return "/identity/assign-resource.xhtml?faces-redirect=true";
	}

	public String doAssignResource() {
		assingResource.setIdentityId(selectedUser.getIdentityId());
		if (Objects.nonNull(assingResource.getIdentityId()) && Objects.nonNull(assingResource.getResourceId())
				&& Objects.nonNull(assingResource.getRoleId())) {
			identityDaoRemote.assignResourcesToUser(assingResource.getIdentityId(), assingResource.getResourceId(),
					assingResource.getRoleId());
		}
		initAllList();
		resourcesRoles = identityDaoRemote.getAllUserResources(selectedUser.getIdentityId());
		initUser(selectedUser);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String organizationMembers() {
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	private void initUser(IdentityDTO identity) {
		identity.setEmail("");
		identity.setFirstname("");
		identity.setLastname("");
		identity.setPassword("");
		identity.setUsername("");
		identity.setOrganization(null);
		identity.setResources(new ArrayList<ResourceDTO>());
		identity.setRoles(new ArrayList<RoleDTO>());
	}

	private void initAllList() {
		organizationMembers = identityDaoRemote.findAllMembers(currentOrganization);
	}

	public IdentityDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(IdentityDTO userDTO) {
		this.userDTO = userDTO;
	}

	public IdentityDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(IdentityDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<IdentityDTO> getOrganizationMembers() {
		return organizationMembers;
	}

	public void setOrganizationMembers(List<IdentityDTO> organizationMembers) {
		this.organizationMembers = organizationMembers;
	}

	public OrganizationDTO getCurrentOrganization() {
		return currentOrganization;
	}

	public void setCurrentOrganization(OrganizationDTO currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public ResourceWithRoleDTO getAssingResource() {
		return assingResource;
	}

	public void setAssingResource(ResourceWithRoleDTO assingResource) {
		this.assingResource = assingResource;
	}

	public Map<String, String> getResourcesRoles() {
		return resourcesRoles;
	}

	public void setResourcesRoles(Map<String, String> resourcesRoles) {
		this.resourcesRoles = resourcesRoles;
	}
}
