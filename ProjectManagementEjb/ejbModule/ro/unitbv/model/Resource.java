package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the resources database table.
 * 
 */
@Entity
@Table(name="resources")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resourceId;

	private String resourceName;

	//bi-directional many-to-one association to Authtype
	@OneToMany(mappedBy="resource")
	private List<Authtype> authtypes;

	//bi-directional many-to-many association to Identity
	@ManyToMany(mappedBy="resources")
	private List<Identity> identities;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(name="identityroleresources",
    joinColumns = @JoinColumn(name = "resourceId", 
                              referencedColumnName = "resourceId"), 
    inverseJoinColumns = @JoinColumn(name = "roleId", 
                              referencedColumnName = "roleId"))
	private List<Role> roles;

	public Resource() {
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<Authtype> getAuthtypes() {
		return this.authtypes;
	}

	public void setAuthtypes(List<Authtype> authtypes) {
		this.authtypes = authtypes;
	}

	public Authtype addAuthtype(Authtype authtype) {
		getAuthtypes().add(authtype);
		authtype.setResource(this);

		return authtype;
	}

	public Authtype removeAuthtype(Authtype authtype) {
		getAuthtypes().remove(authtype);
		authtype.setResource(null);

		return authtype;
	}

	public List<Identity> getIdentities() {
		return this.identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}