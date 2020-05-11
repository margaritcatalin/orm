package ro.unitbv.beans.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.unitbv.dao.RightDaoRemote;
import ro.unitbv.dto.RightDTO;

@ManagedBean
@SessionScoped
public class RightBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private RightDTO rightDTO = new RightDTO();
	private RightDTO selectedRight = new RightDTO();
	private List<RightDTO> allRights;

	@EJB
	RightDaoRemote rightDaoRemote;

	@PostConstruct
	public void init() {
		allRights = rightDaoRemote.findAll();
	}

	public String createRight() {
		RightDTO right = rightDaoRemote.create(rightDTO);
		allRights.add(right);
		initRight(rightDTO);
		return "/right/rights.xhtml?faces-redirect=true";
	}

	public String deleteRight() {
		allRights.remove(selectedRight);
		rightDaoRemote.delete(selectedRight.getRightId());
		initRight(selectedRight);
		return "/right/rights.xhtml?faces-redirect=true";
	}

	public String updateRight() {
		return "/right/update-right.xhtml?faces-redirect=true";
	}

	public String doUpdateUser() {
		rightDaoRemote.update(selectedRight);
		for (RightDTO right : allRights) {
			if (selectedRight.getRightId() == right.getRightId()) {
				right.setRightName(selectedRight.getRightName());
				right.setRightDescription(selectedRight.getRightDescription());
			}
		}
		initRight(selectedRight);
		return "/right/rights.xhtml?faces-redirect=true";
	}

	public String allRights() {
		return "/right/rights.xhtml?faces-redirect=true";
	}

	private void initRight(RightDTO right) {
		right.setRightName("");
		right.setRightDescription("");
	}

	public RightDTO getRightDTO() {
		return rightDTO;
	}

	public void setRightDTO(RightDTO rightDTO) {
		this.rightDTO = rightDTO;
	}

	public RightDTO getSelectedRight() {
		return selectedRight;
	}

	public void setSelectedRight(RightDTO selectedRight) {
		this.selectedRight = selectedRight;
	}

	public List<RightDTO> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<RightDTO> allRights) {
		this.allRights = allRights;
	}

}
