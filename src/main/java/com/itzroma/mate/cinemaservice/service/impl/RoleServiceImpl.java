package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.RoleDao;
import com.itzroma.mate.cinemaservice.model.Role;
import com.itzroma.mate.cinemaservice.service.AbstractCrudService;
import com.itzroma.mate.cinemaservice.service.RoleService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractCrudService<Role> implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        super(roleDao, Role.class);
        this.roleDao = roleDao;
    }

    @Override
    public Role getByName(Role.RoleName roleName) {
        return roleDao.getByName(roleName).orElseThrow(
                () -> new NoSuchElementException("Can't get role by name " + roleName)
        );
    }
}
