package ro.unitbv.beans.organization;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.unitbv.dao.IdentityDaoRemote;
import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.OrganizationDTO;

@ManagedBean
@SessionScoped
public class IdentityBean {

	private IdentityDTO userDTO = new IdentityDTO();
	private IdentityDTO selectedUser = new IdentityDTO();
	private List<IdentityDTO> organizationMembers;
	private OrganizationDTO currentOrganization = new OrganizationDTO();

	@EJB
	IdentityDaoRemote identityDaoRemote;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		currentOrganization = (OrganizationDTO) facesContext.getExternalContext().getSessionMap()
				.get("selectedOrganization");
		organizationMembers = identityDaoRemote.findAllMembers(currentOrganization);
	}

	public String createUser() {
		IdentityDTO user = identityDaoRemote.create(userDTO);
		user.setOrganization(currentOrganization);
		identityDaoRemote.update(user);
		organizationMembers.add(user);
		// TODO set ROLE / RESOURCES
		initUser(userDTO);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String deleteUser() {
		identityDaoRemote.delete(selectedUser.getIdentityId());
		for (IdentityDTO user : organizationMembers) {
			if (selectedUser.getIdentityId() == user.getIdentityId()) {
				organizationMembers.remove(user);
			}
		}
		initUser(selectedUser);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public String updateUser() {
		return "/identity/update-identity.xhtml?faces-redirect=true";
	}

	public String doUpdateUser() {
		identityDaoRemote.update(selectedUser);
		for (IdentityDTO user : organizationMembers) {
			if (selectedUser.getIdentityId() == user.getIdentityId()) {
				user.setEmail(selectedUser.getEmail());
				user.setUsername(selectedUser.getUsername());
				user.setFirstname(selectedUser.getFirstname());
				user.setLastname(selectedUser.getLastname());
				user.setPassword(selectedUser.getPassword());
				// TODO update role / resources
			}
		}
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

}
