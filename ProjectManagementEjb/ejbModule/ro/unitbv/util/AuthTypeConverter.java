package ro.unitbv.util;

import ro.unitbv.dto.AuthTypeDTO;
import ro.unitbv.model.Authtype;

public class AuthTypeConverter implements GenericConverter<Authtype, AuthTypeDTO> {
	private ResourceConverter resourceConverter = new ResourceConverter();

	@Override
	public Authtype inversConvert(AuthTypeDTO object) {
		Authtype authType = new Authtype();
		authType.setType(object.getType());
		authType.setResource(resourceConverter.inversConvert(object.getResource()));
		return authType;
	}

	@Override
	public AuthTypeDTO directConvert(Authtype object) {
		return new AuthTypeDTO(object.getAuthTypesId(), object.getType(),
				resourceConverter.directConvert(object.getResource()));
	}

}
