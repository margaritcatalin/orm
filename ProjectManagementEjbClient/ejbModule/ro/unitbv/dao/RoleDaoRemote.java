package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.RoleDTO;

@Remote
public interface RoleDaoRemote extends GenericDAO<RoleDTO> {
}
