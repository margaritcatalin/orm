package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.AuthTypeDTO;

@Remote
public interface AuthTypeDaoRemote extends GenericDAO<AuthTypeDTO> {
}
