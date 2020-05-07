package ro.unitbv.dto;

import java.io.Serializable;

public class AuthTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int authTypesId;
	private String type;
	private ResourceDTO resource;

	public AuthTypeDTO() {
		super();
	}

	public AuthTypeDTO(int authTypesId, String type, ResourceDTO resource) {
		super();
		this.authTypesId = authTypesId;
		this.type = type;
		this.resource = resource;
	}

	public AuthTypeDTO(int authTypesId, String type) {
		super();
		this.authTypesId = authTypesId;
		this.type = type;
	}

	public int getAuthTypesId() {
		return authTypesId;
	}

	public void setAuthTypesId(int authTypesId) {
		this.authTypesId = authTypesId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ResourceDTO getResource() {
		return resource;
	}

	public void setResource(ResourceDTO resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "AuthTypeDTO [authTypesId=" + authTypesId + ", type=" + type + ", resource=" + resource + "]";
	}

}
