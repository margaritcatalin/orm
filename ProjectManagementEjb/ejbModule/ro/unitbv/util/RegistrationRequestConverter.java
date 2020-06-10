package ro.unitbv.util;

import ro.unitbv.dto.RegistrationRequestDTO;
import ro.unitbv.model.Registrationrequest;

public class RegistrationRequestConverter implements GenericConverter<Registrationrequest, RegistrationRequestDTO> {

	@Override
	public Registrationrequest inversConvert(RegistrationRequestDTO object) {
		Registrationrequest rq = new Registrationrequest();
		rq.setFirstname(object.getFirstname());
		rq.setLastname(object.getLastname());
		rq.setUsername(object.getUsername());
		rq.setOrganization(object.getOrganization());
		rq.setRole(object.getOrganization());
		rq.setPassword(object.getPassword());
		rq.setResource(object.getResource());
		return rq;
	}

	@Override
	public RegistrationRequestDTO directConvert(Registrationrequest object) {
		RegistrationRequestDTO rq = new RegistrationRequestDTO();
		rq.setFirstname(object.getFirstname());
		rq.setLastname(object.getLastname());
		rq.setUsername(object.getUsername());
		rq.setOrganization(object.getOrganization());
		rq.setRole(object.getOrganization());
		rq.setPassword(object.getPassword());
		rq.setResource(object.getResource());
		rq.setIdregistrationrequest(object.getIdregistrationrequest());
		return rq;
	}

}
