package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.RegistrationRequestDTO;
@Remote
public interface RegistrationRequestDaoRemote extends GenericDAO<RegistrationRequestDTO> {
}

