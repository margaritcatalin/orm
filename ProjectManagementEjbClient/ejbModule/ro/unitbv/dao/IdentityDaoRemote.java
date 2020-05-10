package ro.unitbv.dao;

import java.util.List;

import javax.ejb.Remote;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.LoginDTO;
import ro.unitbv.dto.OrganizationDTO;
import ro.unitbv.exception.LoginException;

@Remote
public interface IdentityDaoRemote extends GenericDAO<IdentityDTO> {
	IdentityDTO loginIdentity(LoginDTO loginDTO) throws LoginException;
	List<IdentityDTO> findAllMembers(OrganizationDTO organization);
}
