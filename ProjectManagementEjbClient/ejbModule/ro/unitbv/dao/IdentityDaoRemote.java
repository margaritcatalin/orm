package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.LoginDTO;
import ro.unitbv.exception.LoginException;

@Remote
public interface IdentityDaoRemote extends GenericDAO<IdentityDTO> {
	IdentityDTO loginIdentity(LoginDTO loginDTO) throws LoginException;
}
