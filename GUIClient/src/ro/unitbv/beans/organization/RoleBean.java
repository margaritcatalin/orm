package ro.unitbv.beans.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.unitbv.dao.RoleDaoRemote;
import ro.unitbv.dto.RoleDTO;

@ManagedBean
@SessionScoped
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private RoleDTO roleDTO = new RoleDTO();
	private RoleDTO selectedRole = new RoleDTO();
	private List<RoleDTO> allRoles;

	@EJB
	RoleDaoRemote roleDaoRemote;

	@PostConstruct
	public void init() {
		allRoles = roleDaoRemote.findAll();
	}

	public String createRole() {
		RoleDTO role = roleDaoRemote.create(roleDTO);
		allRoles.add(role);
		initRole(role);
		return "/role/roles.xhtml?faces-redirect=true";
	}

	public String deleteRole() {
		allRoles.remove(selectedRole);
		roleDaoRemote.delete(selectedRole.getRoleId());
		initRole(selectedRole);
		return "/role/roles.xhtml?faces-redirect=true";
	}

	public String updateRole() {
		return "/role/update-role.xhtml?faces-redirect=true";
	}

	public String doUpdateRole() {
		roleDaoRemote.update(selectedRole);
		for (RoleDTO role : allRoles) {
			if (selectedRole.getRoleId() == role.getRoleId()) {
				role.setRoleName(selectedRole.getRoleName());
				role.setRoleDescription(selectedRole.getRoleDescription());
				// TODO update Rights
			}
		}
		initRole(selectedRole);
		return "/role/roles.xhtml?faces-redirect=true";
	}

	public String allRoles() {
		return "/role/roles.xhtml?faces-redirect=true";
	}

	private void initRole(RoleDTO role) {
		role.setRoleName("");
		role.setRoleDescription("");
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public RoleDTO getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(RoleDTO selectedRole) {
		this.selectedRole = selectedRole;
	}

	public List<RoleDTO> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<RoleDTO> allRoles) {
		this.allRoles = allRoles;
	}

}
