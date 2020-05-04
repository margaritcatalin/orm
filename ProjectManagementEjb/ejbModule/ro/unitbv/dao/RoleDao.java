package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.RoleDTO;
import ro.unitbv.model.Role;
import ro.unitbv.util.RoleConverter;

@Stateless
@LocalBean
public class RoleDao implements RoleDaoRemote {
	static final Logger LOGGER = Logger.getLogger(RoleDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	private RoleConverter roleConverter = new RoleConverter();

	public RoleDao() {

	}

	@Override
	public RoleDTO findById(int id) {
		Role role = entityManager.find(Role.class, id);
		RoleDTO roleDTO = roleConverter.directConvert(role);
		return roleDTO;
	}

	@Override
	public List<RoleDTO> findAll() {
		Query query = entityManager.createQuery("SELECT r FROM Role r");
		List<Role> roles = query.getResultList();
		List<RoleDTO> rolesDTO = new ArrayList<>();
		for (Role role : roles) {
			rolesDTO.add(roleConverter.directConvert(role));
		}
		return rolesDTO;
	}

	@Override
	public RoleDTO create(RoleDTO entity) {
		Role role = roleConverter.inversConvert(entity);
		entityManager.persist(role);
		entityManager.flush();
		entity.setRoleId(role.getRoleId());
		return entity;
	}

	@Override
	public RoleDTO update(RoleDTO entity) {
		Role role = roleConverter.inversConvert(entity);
		role.setRoleId(entity.getRoleId());
		role = entityManager.merge(role);
		return entity;
	}

	@Override
	public void delete(int id) {
		Role role = entityManager.find(Role.class, id);
		entityManager.remove(role);
	}

}
