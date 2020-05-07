package ro.unitbv.util;

import ro.unitbv.dto.RoleDTO;
import ro.unitbv.model.Role;

public class RoleConverter implements GenericConverter<Role, RoleDTO> {
	private RightConverter rightConverter = new RightConverter();

	@Override
	public Role inversConvert(RoleDTO object) {
		Role role = new Role();
		role.setRoleName(object.getRoleName());
		role.setRoleDescription(object.getRoleDescription());
		role.setRights(rightConverter.inversConvertAll(object.getRights()));
		return role;
	}

	@Override
	public RoleDTO directConvert(Role object) {
		return new RoleDTO(object.getRoleId(), object.getRoleDescription(), object.getRoleName(),
				rightConverter.directConvertAll(object.getRights()));
	}

}
