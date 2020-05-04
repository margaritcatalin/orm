package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;

	private String roleDescription;

	private String roleName;

	//bi-directional many-to-many association to Right
	@ManyToMany
	@JoinTable(
		name="rightroles"
		, joinColumns={
			@JoinColumn(name="roleId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="rightId")
			}
		)
	private List<Right> rights;

	//bi-directional many-to-many association to Identity
	@ManyToMany(mappedBy="roles")
	private List<Identity> identities;

	//bi-directional many-to-many association to Resource
	@ManyToMany(mappedBy="roles")
	private List<Resource> resources;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Right> getRights() {
		return this.rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	public List<Identity> getIdentities() {
		return this.identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}