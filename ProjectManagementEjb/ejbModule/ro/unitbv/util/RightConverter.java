package ro.unitbv.util;

import ro.unitbv.dto.RightDTO;
import ro.unitbv.model.Right;

public class RightConverter implements GenericConverter<Right, RightDTO> {
	private RoleConverter roleConverter = new RoleConverter();

	@Override
	public Right inversConvert(RightDTO object) {
		Right right = new Right();
		right.setRightDescription(object.getRightDescription());
		right.setRightName(object.getRightName());
		right.setRoles(roleConverter.inversConvertAll(object.getRoles()));
		return right;
	}

	@Override
	public RightDTO directConvert(Right object) {
		return new RightDTO(object.getRightId(), object.getRightDescription(), object.getRightName(),
				roleConverter.directConvertAll(object.getRoles()));
	}

}
