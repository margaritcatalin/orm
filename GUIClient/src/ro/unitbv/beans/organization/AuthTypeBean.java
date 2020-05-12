package ro.unitbv.beans.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.unitbv.dao.AuthTypeDaoRemote;
import ro.unitbv.dto.AuthTypeDTO;

@ManagedBean
@SessionScoped
public class AuthTypeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private AuthTypeDTO authDTO = new AuthTypeDTO();
	private AuthTypeDTO selectedAuth = new AuthTypeDTO();
	private List<AuthTypeDTO> allAuthTypes;

	@EJB
	AuthTypeDaoRemote authTypeDaoRemote;

	@PostConstruct
	public void init() {
		initAllList();
	}

	public String createAuth() {
		AuthTypeDTO auth = authTypeDaoRemote.create(authDTO);
		initAllList();
		initAuth(auth);
		return "/authtype/auth-types.xhtml?faces-redirect=true";
	}

	public String deleteAuth() {
		authTypeDaoRemote.delete(selectedAuth.getAuthTypesId());
		initAllList();
		initAuth(selectedAuth);
		return "/authtype/auth-types.xhtml?faces-redirect=true";
	}

	public String updateAuth() {
		return "/authtype/update-auth.xhtml?faces-redirect=true";
	}

	public String doUpdateAuth() {
		authTypeDaoRemote.update(selectedAuth);
		initAllList();
		initAuth(selectedAuth);
		return "/authtype/auth-types.xhtml?faces-redirect=true";
	}

	public String authTypes() {
		return "/identity/organization-members.xhtml?faces-redirect=true";
	}

	private void initAuth(AuthTypeDTO auth) {
		auth.setType("");
	}
	
	void initAllList() {
		allAuthTypes = authTypeDaoRemote.findAll();
	}

	public AuthTypeDTO getAuthDTO() {
		return authDTO;
	}

	public void setAuthDTO(AuthTypeDTO authDTO) {
		this.authDTO = authDTO;
	}

	public AuthTypeDTO getSelectedAuth() {
		return selectedAuth;
	}

	public void setSelectedAuth(AuthTypeDTO selectedAuth) {
		this.selectedAuth = selectedAuth;
	}

	public List<AuthTypeDTO> getAllAuthTypes() {
		return allAuthTypes;
	}

	public void setAllAuthTypes(List<AuthTypeDTO> allAuthTypes) {
		this.allAuthTypes = allAuthTypes;
	}

}
