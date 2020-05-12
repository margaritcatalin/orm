package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the identityroleresources database table.
 * 
 */
@Entity
@Table(name="identityroleresources")
@NamedQuery(name="Identityroleresource.findAll", query="SELECT i FROM Identityroleresource i")
public class Identityroleresource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identityroleresourcesId;

	private int identityId;

	private int resourceId;

	private int roleId;

	public Identityroleresource() {
	}

	public int getIdentityroleresourcesId() {
		return this.identityroleresourcesId;
	}

	public void setIdentityroleresourcesId(int identityroleresourcesId) {
		this.identityroleresourcesId = identityroleresourcesId;
	}

	public int getIdentityId() {
		return this.identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}