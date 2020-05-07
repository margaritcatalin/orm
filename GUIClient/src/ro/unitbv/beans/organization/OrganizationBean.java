package ro.unitbv.beans.organization;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.unitbv.dao.OrganizationDaoRemote;
import ro.unitbv.dto.OrganizationDTO;

@ManagedBean
@SessionScoped
public class OrganizationBean {

	private OrganizationDTO organizationDTO = new OrganizationDTO();
	private OrganizationDTO selectedOrganization = new OrganizationDTO();
	private List<OrganizationDTO> organizations;
	
	@EJB
	OrganizationDaoRemote organizationDAORemote;

	@PostConstruct
	public void init() {
		organizations = organizationDAORemote.findAll();
	}

	public String createOrganization() {
		organizations.add(organizationDAORemote.create(organizationDTO));
		System.out.println("admin logged");
		return "/organization/organizations.xhtml?faces-redirect=true";
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

}
