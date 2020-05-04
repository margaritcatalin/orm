package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.LoginDTO;
import ro.unitbv.exception.LoginException;
import ro.unitbv.model.Identity;
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
		entityManager.persist(user);
		entityManager.flush();
		identityDTO.setIdentityId(user.getIdentityId());
		return identityDTO;
	}

	@Override
	public IdentityDTO update(IdentityDTO identityDTO) {
		Identity identity = identityConverter.inversConvert(identityDTO);
		identity.setIdentityId(identityDTO.getIdentityId());
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

}
