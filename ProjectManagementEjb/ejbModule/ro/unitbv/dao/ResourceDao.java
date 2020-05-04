package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.ResourceDTO;
import ro.unitbv.model.Resource;
import ro.unitbv.util.ResourceConverter;

@Stateless
@LocalBean
public class ResourceDao implements ResourceDaoRemote {
	static final Logger LOGGER = Logger.getLogger(ResourceDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	private ResourceConverter resourceConverter = new ResourceConverter();

	public ResourceDao() {

	}

	@Override
	public ResourceDTO findById(int id) {
		Resource resource = entityManager.find(Resource.class, id);
		ResourceDTO resourceDTO = resourceConverter.directConvert(resource);
		return resourceDTO;
	}

	@Override
	public List<ResourceDTO> findAll() {
		Query query = entityManager.createQuery("SELECT res FROM Resource res");
		List<Resource> resources = query.getResultList();
		List<ResourceDTO> resourcesDTO = new ArrayList<>();
		for (Resource resource : resources) {
			resourcesDTO.add(resourceConverter.directConvert(resource));
		}
		return resourcesDTO;
	}

	@Override
	public ResourceDTO create(ResourceDTO entity) {
		Resource resource = resourceConverter.inversConvert(entity);
		entityManager.persist(resource);
		entityManager.flush();
		entity.setResourceId(resource.getResourceId());
		return entity;
	}

	@Override
	public ResourceDTO update(ResourceDTO entity) {
		Resource resource = resourceConverter.inversConvert(entity);
		resource.setResourceId(entity.getResourceId());
		resource = entityManager.merge(resource);
		return entity;
	}

	@Override
	public void delete(int id) {
		Resource resource = entityManager.find(Resource.class, id);
		entityManager.remove(resource);
	}

}
