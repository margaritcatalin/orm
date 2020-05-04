package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.AuthTypeDTO;
import ro.unitbv.model.Authtype;
import ro.unitbv.util.AuthTypeConverter;

@Stateless
@LocalBean
public class AuthtypeDao implements AuthTypeDaoRemote {
	static final Logger LOGGER = Logger.getLogger(AuthtypeDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	private AuthTypeConverter authTypeConverter = new AuthTypeConverter();

	public AuthtypeDao() {

	}

	@Override
	public AuthTypeDTO findById(int id) {
		Authtype authType = entityManager.find(Authtype.class, id);
		AuthTypeDTO authTypeDTO = authTypeConverter.directConvert(authType);
		return authTypeDTO;
	}

	@Override
	public List<AuthTypeDTO> findAll() {
		Query query = entityManager.createQuery("SELECT a FROM Authtype a");
		List<Authtype> authTypes = query.getResultList();
		List<AuthTypeDTO> authTypesDTO = new ArrayList<>();
		for (Authtype authType : authTypes) {
			authTypesDTO.add(authTypeConverter.directConvert(authType));
		}
		return authTypesDTO;
	}

	@Override
	public AuthTypeDTO create(AuthTypeDTO entity) {
		Authtype authType = authTypeConverter.inversConvert(entity);
		entityManager.persist(authType);
		entityManager.flush();
		entity.setAuthTypesId(authType.getAuthTypesId());
		return entity;
	}

	@Override
	public AuthTypeDTO update(AuthTypeDTO entity) {
		Authtype authType = authTypeConverter.inversConvert(entity);
		authType.setAuthTypesId(entity.getAuthTypesId());
		authType = entityManager.merge(authType);
		return entity;
	}

	@Override
	public void delete(int id) {
		Authtype authType = entityManager.find(Authtype.class, id);
		entityManager.remove(authType);
	}

}
