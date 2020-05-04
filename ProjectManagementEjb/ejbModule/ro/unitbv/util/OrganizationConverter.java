package ro.unitbv.util;

import ro.unitbv.dto.OrganizationDTO;
import ro.unitbv.model.Organization;

public class OrganizationConverter implements GenericConverter<Organization, OrganizationDTO> {

	private IdentityConverter identityConverter = new IdentityConverter();

	@Override
	public Organization inversConvert(OrganizationDTO object) {
		Organization organization = new Organization();
		organization.setCui(object.getCui());
		organization.setOrganizationName(object.getOrganizationName());
		organization.setIdentities(identityConverter.inversConvertAll(object.getIdentities()));
		return organization;
	}

	@Override
	public OrganizationDTO directConvert(Organization object) {
		return new OrganizationDTO(object.getOrganizationId(), object.getCui(), object.getOrganizationName(),
				identityConverter.directConvertAll(object.getIdentities()));
	}

}
