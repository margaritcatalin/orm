package ro.unitbv.util;

import java.util.Objects;

import ro.unitbv.dto.ResourceDTO;
import ro.unitbv.model.Resource;

public class ResourceConverter implements GenericConverter<Resource, ResourceDTO> {

	private AuthTypeConverter authTypeConverter = new AuthTypeConverter();

	@Override
	public Resource inversConvert(ResourceDTO object) {
		Resource resource = new Resource();
		resource.setResourceName(object.getResourceName());
		if (Objects.nonNull(object.getAuthtypes())) {
			resource.setAuthtypes(authTypeConverter.inversConvertAll(object.getAuthtypes()));
		}
		return resource;
	}

	@Override
	public ResourceDTO directConvert(Resource object) {
		ResourceDTO resource = new ResourceDTO(object.getResourceId(), object.getResourceName());
		resource.setAuthtypes(authTypeConverter.directConvertAll(object.getAuthtypes()));
		return resource;
	}

}
