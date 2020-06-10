package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.RegistrationRequestDTO;
import ro.unitbv.model.Registrationrequest;
import ro.unitbv.util.RegistrationRequestConverter;

@Stateless
@LocalBean
public class RegistrationRequestDao implements RegistrationRequestDaoRemote {
	static final Logger LOGGER = Logger.getLogger(RegistrationRequestDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	private RegistrationRequestConverter registrationRequestConverter = new RegistrationRequestConverter();

	public RegistrationRequestDao() {

	}

	@Override
	public RegistrationRequestDTO findById(int id) {
		Registrationrequest rq = entityManager.find(Registrationrequest.class, id);
		RegistrationRequestDTO rqDTO = registrationRequestConverter.directConvert(rq);
		return rqDTO;
	}

	@Override
	public List<RegistrationRequestDTO> findAll() {
		Query query = entityManager.createQuery("SELECT rq FROM Registrationrequest rq");
		List<Registrationrequest> rqs = query.getResultList();
		List<RegistrationRequestDTO> rqsDTO = new ArrayList<>();
		for (Registrationrequest rq : rqs) {
			rqsDTO.add(registrationRequestConverter.directConvert(rq));
		}
		return rqsDTO;
	}

	@Override
	public RegistrationRequestDTO create(RegistrationRequestDTO entity) {
		Registrationrequest rq = registrationRequestConverter.inversConvert(entity);
		entityManager.persist(rq);
		entityManager.flush();
		entity.setIdregistrationrequest(rq.getIdregistrationrequest());
		return entity;
	}

	@Override
	public RegistrationRequestDTO update(RegistrationRequestDTO entity) {
		Registrationrequest rq = registrationRequestConverter.inversConvert(entity);
		rq.setIdregistrationrequest(entity.getIdregistrationrequest());
		rq = entityManager.merge(rq);
		return entity;
	}

	@Override
	public void delete(int id) {
		Registrationrequest rq = entityManager.find(Registrationrequest.class, id);
		entityManager.remove(rq);
	}

}
