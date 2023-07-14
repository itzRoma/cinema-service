package com.itzroma.mate.cinemaservice.dao;

import com.itzroma.mate.cinemaservice.model.Role;
import java.util.Optional;

public interface RoleDao extends CrudDao<Role> {
    Optional<Role> getByName(Role.RoleName roleName);
}
