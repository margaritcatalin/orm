package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.OrganizationDTO;

@Remote
public interface OrganizationDaoRemote extends GenericDAO<OrganizationDTO> {
}
