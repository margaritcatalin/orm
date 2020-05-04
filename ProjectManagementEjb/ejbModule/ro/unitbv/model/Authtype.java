package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authtypes database table.
 * 
 */
@Entity
@Table(name="authtypes")
@NamedQuery(name="Authtype.findAll", query="SELECT a FROM Authtype a")
public class Authtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int authTypesId;

	private String type;

	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="idResource")
	private Resource resource;

	public Authtype() {
	}

	public int getAuthTypesId() {
		return this.authTypesId;
	}

	public void setAuthTypesId(int authTypesId) {
		this.authTypesId = authTypesId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}