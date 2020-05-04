package ro.unitbv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rights database table.
 * 
 */
@Entity
@Table(name="rights")
@NamedQuery(name="Right.findAll", query="SELECT r FROM Right r")
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rightId;

	private String rightDescription;

	private String rightName;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="rights")
	private List<Role> roles;

	public Right() {
	}

	public int getRightId() {
		return this.rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public String getRightDescription() {
		return this.rightDescription;
	}

	public void setRightDescription(String rightDescription) {
		this.rightDescription = rightDescription;
	}

	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}