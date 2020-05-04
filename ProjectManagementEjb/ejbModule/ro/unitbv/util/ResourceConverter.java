package ro.unitbv.util;

import ro.unitbv.dto.ResourceDTO;
import ro.unitbv.model.Resource;

public class ResourceConverter implements GenericConverter<Resource, ResourceDTO> {

	private AuthTypeConverter authTypeConverter = new AuthTypeConverter();
	private IdentityConverter identityConverter = new IdentityConverter();
	private RoleConverter roleConverter = new RoleConverter();

	@Override
	public Resource inversConvert(ResourceDTO object) {
		Resource resource = new Resource();
		resource.setResourceName(object.getResourceName());
		resource.setAuthtypes(authTypeConverter.inversConvertAll(object.getAuthtypes()));
		resource.setIdentities(identityConverter.inversConvertAll(object.getIdentities()));
		resource.setRoles(roleConverter.inversConvertAll(object.getRoles()));
		return resource;
	}

	@Override
	public ResourceDTO directConvert(Resource object) {
		// TODO Auto-generated method stub
		return new ResourceDTO(object.getResourceId(), object.getResourceName(),
				authTypeConverter.directConvertAll(object.getAuthtypes()),
				identityConverter.directConvertAll(object.getIdentities()),
				roleConverter.directConvertAll(object.getRoles()));
	}

}
