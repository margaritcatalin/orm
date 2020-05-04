package ro.unitbv.dao;

import javax.ejb.Remote;

import ro.unitbv.dto.ResourceDTO;

@Remote
public interface ResourceDaoRemote extends GenericDAO<ResourceDTO> {
}
