package ro.unitbv.util;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.model.Identity;

public class IdentityConverter implements GenericConverter<Identity, IdentityDTO> {

	@Override
	public Identity inversConvert(IdentityDTO identityDTO) {
		Identity identity = new Identity();
		identity.setEmail(identityDTO.getEmail());
		identity.setFirstname(identityDTO.getFirstname());
		identity.setLastname(identityDTO.getLastname());
		identity.setUsername(identityDTO.getUsername());
		identity.setPassword(identityDTO.getPassword());
		return identity;
	}

	@Override
	public IdentityDTO directConvert(Identity identity) {
		return new IdentityDTO(identity.getIdentityId(), identity.getEmail(), identity.getFirstname(),
				identity.getLastname(), identity.getPassword(), identity.getUsername());
	}

}
