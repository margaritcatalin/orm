package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.RightDTO;

@Remote
public interface RightDaoRemote extends GenericDAO<RightDTO> {
}
