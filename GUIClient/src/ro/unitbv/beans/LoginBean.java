package ro.unitbv.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.unitbv.dao.IdentityDaoRemote;
import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.dto.LoginDTO;
import ro.unitbv.exception.LoginException;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	LoginDTO loginDTO = new LoginDTO();

	@EJB
	IdentityDaoRemote identityDAORemote;

	IdentityDTO identiyDTO;

	public String loginIdentity() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			identiyDTO = identityDAORemote.loginIdentity(loginDTO);
			facesContext.getExternalContext().getSessionMap().put("identiyDTO", identiyDTO);
			if (identiyDTO.getRoles().stream().filter(role -> role.getRoleName().equalsIgnoreCase("CEO")).findAny()
					.isPresent()) {
				return "/organization/organizations.xhtml?faces-redirect=true";
			} else if (Objects.nonNull(identiyDTO.getOrganization())) {
				if (Objects.nonNull(facesContext.getExternalContext().getSessionMap().get("selectedOrganization"))) {
					facesContext.getExternalContext().getSessionMap().remove("selectedOrganization");
				}
				facesContext.getExternalContext().getSessionMap().put("selectedOrganization",
						identiyDTO.getOrganization());
				return "/identity/organization-members.xhtml?faces-redirect=true";
			}
			return "";

		} catch (LoginException e) {
			System.out.println("Invalid username or password");
			facesContext.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}

	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		identiyDTO = null;
		return "/index?faces-redirect=true";
	}

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public IdentityDTO getIdentityDTO() {
		return identiyDTO;
	}

	public void setIdentityDTO(IdentityDTO identiyDTO) {
		this.identiyDTO = identiyDTO;
	}

}