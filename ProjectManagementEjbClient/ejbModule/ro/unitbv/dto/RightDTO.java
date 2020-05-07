package ro.unitbv.dto;

import java.io.Serializable;
import java.util.List;

public class RightDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rightId;
	private String rightDescription;
	private String rightName;
	private List<RoleDTO> roles;

	public RightDTO() {
		super();
	}

	public RightDTO(int rightId, String rightDescription, String rightName, List<RoleDTO> roles) {
		super();
		this.rightId = rightId;
		this.rightDescription = rightDescription;
		this.rightName = rightName;
		this.roles = roles;
	}

	public RightDTO(int rightId, String rightDescription, String rightName) {
		super();
		this.rightId = rightId;
		this.rightDescription = rightDescription;
		this.rightName = rightName;
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public String getRightDescription() {
		return rightDescription;
	}

	public void setRightDescription(String rightDescription) {
		this.rightDescription = rightDescription;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "RightDTO [rightId=" + rightId + ", rightDescription=" + rightDescription + ", rightName=" + rightName
				+ ", roles=" + roles + "]";
	}

}
