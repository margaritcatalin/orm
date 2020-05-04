package ro.unitbv.dto;

import java.io.Serializable;
import java.util.List;

public class OrganizationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int organizationId;
	private String cui;
	private String organizationName;
	private List<IdentityDTO> identities;

	public OrganizationDTO() {
		super();
	}

	public OrganizationDTO(int organizationId, String cui, String organizationName, List<IdentityDTO> identities) {
		super();
		this.organizationId = organizationId;
		this.cui = cui;
		this.organizationName = organizationName;
		this.identities = identities;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getCui() {
		return cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<IdentityDTO> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdentityDTO> identities) {
		this.identities = identities;
	}

	@Override
	public String toString() {
		return "OrganizationDTO [organizationId=" + organizationId + ", cui=" + cui + ", organizationName="
				+ organizationName + ", identities=" + identities + "]";
	}

}
