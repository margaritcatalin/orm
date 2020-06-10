package ro.unitbv.util;

import java.util.Objects;

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
		if (Objects.nonNull(identityDTO.getPassword()) && !identityDTO.getPassword().equalsIgnoreCase("")) {
			identity.setPassword(identityDTO.getPassword());
		}
		if (Objects.nonNull(identityDTO.getResources())) {
			identity.setResources(resourceConverter.inversConvertAll(identityDTO.getResources()));
		}
		if (Objects.nonNull(identityDTO.getOrganization())) {
			identity.setOrganization(organizationConverter.inversConvert(identityDTO.getOrganization()));
		}
		if (Objects.nonNull(identityDTO.getRoles())) {
			identity.setRoles(roleConverter.inversConvertAll(identityDTO.getRoles()));
		}
		return identity;
	}

	@Override
	public IdentityDTO directConvert(Identity identity) {
		IdentityDTO dto = new IdentityDTO(identity.getIdentityId(), identity.getEmail(), identity.getFirstname(),
				identity.getLastname(), identity.getPassword(), identity.getUsername());
		if (Objects.nonNull(identity.getOrganization())) {

			dto.setOrganization(organizationConverter.directConvert(identity.getOrganization()));
		}
		if (Objects.nonNull(identity.getRoles())) {

			dto.setRoles(roleConverter.directConvertAll(identity.getRoles()));
		}
		if (Objects.nonNull(identity.getResources())) {

			dto.setResources(resourceConverter.directConvertAll(identity.getResources()));
		}
		if (Objects.nonNull(identity.getRoles()) && identity.getRoles().stream()
				.filter(role -> role.getRoleName().equalsIgnoreCase("CEO")).findAny().isPresent()) {
			dto.setAdmin(true);
		} else {
			dto.setAdmin(false);

		}if (Objects.nonNull(identity.getRoles()) && identity.getRoles().stream()
				.filter(role -> role.getRoleName().equalsIgnoreCase("Moderator")).findAny().isPresent()) {
			dto.setModerator(true);
		} else {
			dto.setModerator(false);

		}
		return dto;
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
