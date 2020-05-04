package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.OrganizationDTO;
import ro.unitbv.model.Organization;
import ro.unitbv.util.OrganizationConverter;

@Stateless
@LocalBean
public class OrganizationDao implements OrganizationDaoRemote {
	static final Logger LOGGER = Logger.getLogger(OrganizationDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	private OrganizationConverter organizationConverter = new OrganizationConverter();

	public OrganizationDao() {

	}

	@Override
	public OrganizationDTO findById(int id) {
		Organization organization = entityManager.find(Organization.class, id);
		OrganizationDTO organizationDTO = organizationConverter.directConvert(organization);
		return organizationDTO;
	}

	@Override
	public List<OrganizationDTO> findAll() {
		Query query = entityManager.createQuery("SELECT o FROM Organization o");
		List<Organization> organizations = query.getResultList();
		List<OrganizationDTO> organizationsDTO = new ArrayList<>();
		for (Organization organization : organizations) {
			organizationsDTO.add(organizationConverter.directConvert(organization));
		}
		return organizationsDTO;
	}

	@Override
	public OrganizationDTO create(OrganizationDTO entity) {
		Organization organization = organizationConverter.inversConvert(entity);
		entityManager.persist(organization);
		entityManager.flush();
		entity.setOrganizationId(organization.getOrganizationId());
		return entity;
	}

	@Override
	public OrganizationDTO update(OrganizationDTO entity) {
		Organization organization = organizationConverter.inversConvert(entity);
		organization.setOrganizationId(entity.getOrganizationId());
		organization = entityManager.merge(organization);
		return entity;
	}

	@Override
	public void delete(int id) {
		Organization organization = entityManager.find(Organization.class, id);
		entityManager.remove(organization);
	}

}
