package ro.unitbv.util;

import ro.unitbv.dto.RoleDTO;
import ro.unitbv.model.Role;

public class RoleConverter implements GenericConverter<Role, RoleDTO> {
	private ResourceConverter resourceConverter = new ResourceConverter();
	private IdentityConverter identityConverter = new IdentityConverter();
	private RightConverter rightConverter = new RightConverter();

	@Override
	public Role inversConvert(RoleDTO object) {
		Role role = new Role();
		role.setRoleName(object.getRoleName());
		role.setRoleDescription(object.getRoleDescription());
		role.setIdentities(identityConverter.inversConvertAll(object.getIdentities()));
		role.setResources(resourceConverter.inversConvertAll(object.getResources()));
		role.setRights(rightConverter.inversConvertAll(object.getRights()));
		return role;
	}

	@Override
	public RoleDTO directConvert(Role object) {
		return new RoleDTO(object.getRoleId(), object.getRoleDescription(), object.getRoleName(),
				rightConverter.directConvertAll(object.getRights()),
				identityConverter.directConvertAll(object.getIdentities()),
				resourceConverter.directConvertAll(object.getResources()));
	}

}
