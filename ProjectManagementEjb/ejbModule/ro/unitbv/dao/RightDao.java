package ro.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.unitbv.dto.RightDTO;
import ro.unitbv.model.Right;
import ro.unitbv.util.RightConverter;

@Stateless
@LocalBean
public class RightDao implements RightDaoRemote {
	static final Logger LOGGER = Logger.getLogger(RightDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	private RightConverter rightConverter = new RightConverter();

	public RightDao() {

	}

	@Override
	public RightDTO findById(int id) {
		Right right = entityManager.find(Right.class, id);
		RightDTO rightDTO = rightConverter.directConvert(right);
		return rightDTO;
	}

	@Override
	public List<RightDTO> findAll() {
		Query query = entityManager.createQuery("SELECT ri FROM Right ri");
		List<Right> rights = query.getResultList();
		List<RightDTO> rightsDTO = new ArrayList<>();
		for (Right right : rights) {
			rightsDTO.add(rightConverter.directConvert(right));
		}
		return rightsDTO;
	}

	@Override
	public RightDTO create(RightDTO entity) {
		Right right = rightConverter.inversConvert(entity);
		entityManager.persist(right);
		entityManager.flush();
		entity.setRightId(right.getRightId());
		return entity;
	}

	@Override
	public RightDTO update(RightDTO entity) {
		Right right = rightConverter.inversConvert(entity);
		right.setRightId(entity.getRightId());
		right = entityManager.merge(right);
		return entity;
	}

	@Override
	public void delete(int id) {
		Right right = entityManager.find(Right.class, id);
		entityManager.remove(right);

	}

}
