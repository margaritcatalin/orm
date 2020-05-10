package ro.unitbv.beans.organization;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.xml.internal.ws.util.StringUtils;

import ro.unitbv.dao.OrganizationDaoRemote;
import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.OrganizationDTO;

@ManagedBean
@SessionScoped
public class OrganizationBean {

	private OrganizationDTO organizationDTO = new OrganizationDTO();
	private OrganizationDTO selectedOrganization = new OrganizationDTO();
	private List<OrganizationDTO> organizations;
	private List<IdentityDTO> members;

	@EJB
	OrganizationDaoRemote organizationDAORemote;

	@PostConstruct
	public void init() {
		organizations = organizationDAORemote.findAll();
	}

	public String createOrganization() {
		organizations.add(organizationDAORemote.create(organizationDTO));
		initOrganization(organizationDTO);
		return "/organization/organizations.xhtml?faces-redirect=true";
	}

	public String deleteOrganization() {
		organizationDAORemote.delete(selectedOrganization.getOrganizationId());
		for (OrganizationDTO org : organizations) {
			if (selectedOrganization.getOrganizationId() == org.getOrganizationId()) {
				organizations.remove(org);
			}
		}
		initOrganization(selectedOrganization);
		return "/organization/organizations.xhtml?faces-redirect=true";
	}

	public String updateOrganization() {
		return "/organization/update-org.xhtml?faces-redirect=true";
	}

	public String doUpdateOrganization() {
		organizationDAORemote.update(selectedOrganization);
		for (OrganizationDTO org : organizations) {
			if (selectedOrganization.getOrganizationId() == org.getOrganizationId()) {
				org.setOrganizationName(selectedOrganization.getOrganizationName());
				org.setCui(selectedOrganization.getCui());
			}
		}
		initOrganization(selectedOrganization);
		return "/organization/organizations.xhtml?faces-redirect=true";
	}

	public String organizationMembers() {
		return "/organization/organization-members.xhtml?faces-redirect=true";
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
