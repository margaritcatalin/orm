package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.LoginDTO;
import ro.unitbv.dto.OrganizationDTO;
import ro.unitbv.exception.LoginException;
import ro.unitbv.model.Identity;
import ro.unitbv.model.Identityroleresource;
import ro.unitbv.model.Organization;
import ro.unitbv.model.Resource;
import ro.unitbv.model.Role;
import ro.unitbv.util.IdentityConverter;

/**
 * Session Bean implementation class IdentityDao
 */
@Stateless
@LocalBean
public class IdentityDao implements IdentityDaoRemote {

	static final Logger LOGGER = Logger.getLogger(IdentityDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public IdentityDao() {
	}

	private IdentityConverter identityConverter = new IdentityConverter();

	@Override
	public IdentityDTO findById(int id) {
		Identity identity = entityManager.find(Identity.class, id);
		IdentityDTO identityDTO = identityConverter.directConvert(identity);
		return identityDTO;
	}

	@Override
	public List<IdentityDTO> findAll() {
		Query query = entityManager.createQuery("SELECT i FROM Identity i");
		List<Identity> users = query.getResultList();
		System.out.println(users.toString());
		List<IdentityDTO> identityDTOs = new ArrayList<>();
		for (Identity user : users) {
			identityDTOs.add(identityConverter.directConvert(user));
		}
		return identityDTOs;
	}

	@Override
	public IdentityDTO create(IdentityDTO identityDTO) {
		Identity user = identityConverter.inversConvert(identityDTO);
		if (Objects.nonNull(identityDTO.getOrganization())) {
			Organization organization = entityManager.find(Organization.class,
					identityDTO.getOrganization().getOrganizationId());
			if (Objects.nonNull(organization)) {
				user.setOrganization(organization);
			}
		}
		/*
		 * if (Objects.nonNull(identityDTO.getResources())) { List<Resource> resources =
		 * new ArrayList<Resource>(); for (ResourceDTO res : identityDTO.getResources())
		 * { Resource resource = entityManager.find(Resource.class,
		 * res.getResourceId()); if (Objects.nonNull(resource)) {
		 * resources.add(resource); } } user.setResources(resources); } if
		 * (Objects.nonNull(identityDTO.getRoles())) { List<Role> roles = new
		 * ArrayList<Role>(); for (RoleDTO rol : identityDTO.getRoles()) { Role role =
		 * entityManager.find(Role.class, rol.getRoleId()); if (Objects.nonNull(role)) {
		 * roles.add(role); } } user.setRoles(roles); }
		 */
		entityManager.persist(user);
		entityManager.flush();
		identityDTO.setIdentityId(user.getIdentityId());
		return identityDTO;
	}

	@Override
	public IdentityDTO update(IdentityDTO identityDTO) {
		Identity identity = identityConverter.inversConvert(identityDTO);
		identity.setIdentityId(identityDTO.getIdentityId());
		if (Objects.nonNull(identityDTO.getOrganization())) {
			Organization organization = entityManager.find(Organization.class,
					identityDTO.getOrganization().getOrganizationId());
			if (Objects.nonNull(organization)) {
				identity.setOrganization(organization);
			}
		}
		/*
		 * if (Objects.nonNull(identityDTO.getResources())) { List<Resource> resources =
		 * new ArrayList<Resource>(); for (ResourceDTO res : identityDTO.getResources())
		 * { Resource resource = entityManager.find(Resource.class,
		 * res.getResourceId()); if (Objects.nonNull(resource)) {
		 * resources.add(resource); } } identity.setResources(resources); } if
		 * (Objects.nonNull(identityDTO.getRoles())) { List<Role> roles = new
		 * ArrayList<Role>(); for (RoleDTO rol : identityDTO.getRoles()) { Role role =
		 * entityManager.find(Role.class, rol.getRoleId()); if (Objects.nonNull(role)) {
		 * roles.add(role); } } identity.setRoles(roles); }
		 */
		identity = entityManager.merge(identity);
		return identityDTO;
	}

	@Override
	public void delete(int id) {
		Identity identity = entityManager.find(Identity.class, id);
		entityManager.remove(identity);
	}

	@Override
	public IdentityDTO loginIdentity(LoginDTO loginDTO) throws LoginException {
		Identity identity = null;
		try {
			identity = (Identity) entityManager.createQuery("SELECT i FROM Identity i WHERE i.username=:username")
					.setParameter("username", loginDTO.getUsername()).getSingleResult();
		} catch (NoResultException e) {
			throw new LoginException("Wrong authentication!");
		}
		if (!loginDTO.getPassword().equals(identity.getPassword())) {
			throw new LoginException("Wrong authentication!");
		}

		IdentityDTO identityDTO = identityConverter.directConvert(identity);
		return identityDTO;

	}

	@Override
	public List<IdentityDTO> findAllMembers(OrganizationDTO organization) {
		return findAll().stream()
				.filter(identity -> Objects.nonNull(identity.getOrganization())
						&& organization.getOrganizationId() == identity.getOrganization().getOrganizationId())
				.collect(Collectors.toList());
	}

	@Override
	public IdentityDTO assignResourcesToUser(Integer userId, Integer resourceId, Integer roleId) {
		Identityroleresource resourceWithRole = new Identityroleresource();
		resourceWithRole.setIdentityId(userId);
		resourceWithRole.setResourceId(resourceId);
		resourceWithRole.setRoleId(roleId);
		entityManager.persist(resourceWithRole);
		entityManager.flush();
		return identityConverter.directConvert(entityManager.find(Identity.class, userId));
	}

	@Override
	public Map<String, String> getAllUserResources(Integer userId) {
		Map<String, String> uResources = new HashMap<String, String>();
		Query query = entityManager
				.createQuery("SELECT ires FROM Identityroleresource ires WHERE ires.identityId=:userid")
				.setParameter("userid", userId);
		List<Identityroleresource> userResources = query.getResultList();
		for (Identityroleresource uRes : userResources) {
			Resource res = entityManager.find(Resource.class, uRes.getResourceId());
			Role rol = entityManager.find(Role.class, uRes.getRoleId());
			if (Objects.isNull(uResources.get("[" + res.getResourceId() + "] " + res.getResourceName()))) {
				uResources.put("[" + res.getResourceId() + "] " + res.getResourceName(), rol.getRoleName());
			} else {
				uResources.put("[" + res.getResourceId() + "] " + res.getResourceName(),
						uResources.get("[" + res.getResourceId() + "] " + res.getResourceName()) + ", "
								+ rol.getRoleName());

			}

		}
		return uResources;

	}
}
