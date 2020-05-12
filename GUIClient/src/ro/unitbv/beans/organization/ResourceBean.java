package ro.unitbv.beans.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.unitbv.dao.ResourceDaoRemote;
import ro.unitbv.dto.ResourceDTO;

@ManagedBean
@SessionScoped
public class ResourceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResourceDTO resourceDTO = new ResourceDTO();
	private ResourceDTO selectedResource = new ResourceDTO();
	private List<ResourceDTO> allResources;
	@EJB
	ResourceDaoRemote resourceDaoRemote;

	@PostConstruct
	public void init() {
		initAllList();
	}

	public String createResource() {
		ResourceDTO user = resourceDaoRemote.create(resourceDTO);
		initAllList();
		initResource(resourceDTO);
		return "/resource/resources.xhtml?faces-redirect=true";
	}

	public String deleteResource() {
		resourceDaoRemote.delete(selectedResource.getResourceId());
		initAllList();
		initResource(selectedResource);
		return "/resource/resources.xhtml?faces-redirect=true";
	}

	public String updateResource() {
		return "/resource/update-resource.xhtml?faces-redirect=true";
	}

	public String doUpdateResource() {
		resourceDaoRemote.update(selectedResource);
		initAllList();
		initResource(selectedResource);
		return "/resource/resources.xhtml?faces-redirect=true";
	}

	public String allResources() {
		return "/resource/resources.xhtml?faces-redirect=true";
	}

	private void initResource(ResourceDTO resource) {
		resource.setResourceName("");
	}

	private void initAllList() {
		allResources = resourceDaoRemote.findAll();
	}

	public ResourceDTO getResourceDTO() {
		return resourceDTO;
	}

	public void setResourceDTO(ResourceDTO resourceDTO) {
		this.resourceDTO = resourceDTO;
	}

	public ResourceDTO getSelectedResource() {
		return selectedResource;
	}

	public void setSelectedResource(ResourceDTO selectedResource) {
		this.selectedResource = selectedResource;
	}

	public List<ResourceDTO> getAllResources() {
		return allResources;
	}

	public void setAllResources(List<ResourceDTO> allResources) {
		this.allResources = allResources;
	}

}
