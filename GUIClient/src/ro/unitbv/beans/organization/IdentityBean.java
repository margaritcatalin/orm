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
import ro.unitbv.dao.OrganizationDaoRemote;
import ro.unitbv.dao.RegistrationRequestDaoRemote;
import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.OrganizationDTO;
import ro.unitbv.dto.RegistrationRequestDTO;
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
	private List<RegistrationRequestDTO> rqList;
	private RegistrationRequestDTO selectedRQ = new RegistrationRequestDTO();

	@EJB
	IdentityDaoRemote identityDaoRemote;
	@EJB
	RegistrationRequestDaoRemote registrationRequestDaoRemote;
	@EJB
	OrganizationDaoRemote organizationDAORemote;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		currentOrganization = (OrganizationDTO) facesContext.getExternalContext().getSessionMap()
				.get("selectedOrganization");
		initAllList();
		rqList = registrationRequestDaoRemote.findAll();
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
		rqList = registrationRequestDaoRemote.findAll();
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String registrationRequests() {
		return "/identity/register-requests.xhtml?faces-redirect=true";
	}

	public String acceptUserRegistration() {
		OrganizationDTO orgDTO = organizationDAORemote.findById(Integer.valueOf(selectedRQ.getOrganization()));

		IdentityDTO idDTO = new IdentityDTO();
		idDTO.setEmail(
				selectedRQ.getFirstname() + selectedRQ.getLastname() + "@" + orgDTO.getOrganizationName() + ".com");
		idDTO.setUsername(selectedRQ.getUsername());
		idDTO.setFirstname(selectedRQ.getFirstname());
		idDTO.setLastname(selectedRQ.getLastname());
		idDTO.setPassword(selectedRQ.getPassword());
		IdentityDTO user = identityDaoRemote.create(idDTO);
		user.setOrganization(orgDTO);
		identityDaoRemote.update(user);
		identityDaoRemote.assignResourcesToUser(user.getIdentityId(), Integer.valueOf(selectedRQ.getResource()),
				Integer.valueOf(selectedRQ.getRole()));
		registrationRequestDaoRemote.delete(selectedRQ.getIdregistrationrequest());
		initAllList();
		rqList = registrationRequestDaoRemote.findAll();
		return "/identity/registerRequest.xhtml?faces-redirect=true";
	}

	public String declineUserRegistration() {
		registrationRequestDaoRemote.delete(selectedRQ.getIdregistrationrequest());
		rqList = registrationRequestDaoRemote.findAll();
		return "/identity/registerRequest.xhtml?faces-redirect=true";
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

	public List<RegistrationRequestDTO> getRqList() {
		return rqList;
	}

	public void setRqList(List<RegistrationRequestDTO> rqList) {
		this.rqList = rqList;
	}

	public RegistrationRequestDTO getSelectedRQ() {
		return selectedRQ;
	}

	public void setSelectedRQ(RegistrationRequestDTO selectedRQ) {
		this.selectedRQ = selectedRQ;
	}

}
