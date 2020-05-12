package ro.unitbv.beans.organization;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.unitbv.dao.OrganizationDaoRemote;
import ro.unitbv.dto.OrganizationDTO;

@ManagedBean
@SessionScoped
public class OrganizationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private OrganizationDTO organizationDTO = new OrganizationDTO();
	private OrganizationDTO selectedOrganization = new OrganizationDTO();
	private List<OrganizationDTO> organizations;

	@EJB
	OrganizationDaoRemote organizationDAORemote;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (Objects.nonNull(facesContext.getExternalContext().getSessionMap().get("selectedOrganization"))) {
			facesContext.getExternalContext().getSessionMap().remove("selectedOrganization");
		}
		initAllList();
	}

	public String createOrganization() {
		organizationDAORemote.create(organizationDTO);
		initAllList();
		initOrganization(organizationDTO);
		return "/organization/organizations.xhtml?faces-redirect=true";
	}

	public String deleteOrganization() {
		organizationDAORemote.delete(selectedOrganization.getOrganizationId());
		initAllList();
		initOrganization(selectedOrganization);
		return "/organization/organizations.xhtml?faces-redirect=true";
	}

	public String updateOrganization() {
		return "/organization/update-org.xhtml?faces-redirect=true";
	}

	public String doUpdateOrganization() {
		organizationDAORemote.update(selectedOrganization);
		initAllList();
		initOrganization(selectedOrganization);
		return "/organization/organizations.xhtml?faces-redirect=true";
	}

	public String organizationMembers() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (Objects.nonNull(facesContext.getExternalContext().getSessionMap().get("selectedOrganization"))) {
			facesContext.getExternalContext().getSessionMap().remove("selectedOrganization");
		}
		facesContext.getExternalContext().getSessionMap().put("selectedOrganization", selectedOrganization);
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	public void initAllList() {
		organizations = organizationDAORemote.findAll();
	}

	public OrganizationDTO getOrganizationDTO() {
		return organizationDTO;
	}

	public void setOrganizationDTO(OrganizationDTO organizationDTO) {
		this.organizationDTO = organizationDTO;
	}

	public List<OrganizationDTO> getOrganizations() {
		return organizations;
	}

	public OrganizationDTO getSelectedOrganization() {
		return selectedOrganization;
	}

	public void setOrganizations(List<OrganizationDTO> organizations) {
		this.organizations = organizations;
	}

	public void setSelectedOrganization(OrganizationDTO selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
	}

	private void initOrganization(OrganizationDTO org) {
		org.setCui("");
		org.setOrganizationName("");
	}
}
