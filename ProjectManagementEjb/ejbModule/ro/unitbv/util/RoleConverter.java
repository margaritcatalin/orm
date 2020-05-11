package ro.unitbv.util;

import java.util.Objects;

import ro.unitbv.dto.RoleDTO;
import ro.unitbv.model.Role;

public class RoleConverter implements GenericConverter<Role, RoleDTO> {
	private RightConverter rightConverter = new RightConverter();

	@Override
	public Role inversConvert(RoleDTO object) {
		Role role = new Role();
		role.setRoleName(object.getRoleName());
		role.setRoleDescription(object.getRoleDescription());
		if (Objects.nonNull(object.getRights())) {
			role.setRights(rightConverter.inversConvertAll(object.getRights()));
		}
		return role;
	}

	@Override
	public RoleDTO directConvert(Role object) {
		RoleDTO dto = new RoleDTO(object.getRoleId(), object.getRoleDescription(), object.getRoleName());
		if (Objects.nonNull(object.getRights())) {
			dto.setRights(rightConverter.directConvertAll(object.getRights()));
		}
		return dto;
	}

}
