package ro.unitbv.util;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.model.Identity;

public class IdentityConverter implements GenericConverter<Identity, IdentityDTO> {

	private ResourceConverter resourceConverter = new ResourceConverter();
	private RoleConverter roleConverter = new RoleConverter();
	private OrganizationConverter organizationConverter = new OrganizationConverter();

	@Override
	public Identity inversConvert(IdentityDTO identityDTO) {
		Identity identity = new Identity();
		identity.setEmail(identityDTO.getEmail());
		identity.setFirstname(identityDTO.getFirstname());
		identity.setLastname(identityDTO.getLastname());
		identity.setUsername(identityDTO.getUsername());
		identity.setPassword(identityDTO.getPassword());
		identity.setOrganization(organizationConverter.inversConvert(identityDTO.getOrganization()));
		identity.setRoles(roleConverter.inversConvertAll(identityDTO.getRoles()));
		identity.setResources(resourceConverter.inversConvertAll(identityDTO.getResources()));
		return identity;
	}

	@Override
	public IdentityDTO directConvert(Identity identity) {
		return new IdentityDTO(identity.getIdentityId(), identity.getEmail(), identity.getFirstname(),
				identity.getLastname(), identity.getPassword(), identity.getUsername(),
				organizationConverter.directConvert(identity.getOrganization()),
				resourceConverter.directConvertAll(identity.getResources()),
				roleConverter.directConvertAll(identity.getRoles()));
	}

	public RoleConverter getRoleConverter() {
		return roleConverter;
	}

	public OrganizationConverter getOrganizationConverter() {
		return organizationConverter;
	}

	public ResourceConverter getResourceConverter() {
		return resourceConverter;
	}

}
