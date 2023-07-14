package com.itzroma.mate.cinemaservice.service;

import com.itzroma.mate.cinemaservice.model.Role;

public interface RoleService extends CrudService<Role> {
    Role getByName(Role.RoleName roleName);
}
