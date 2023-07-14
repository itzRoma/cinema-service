package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.RoleDao;
import com.itzroma.mate.cinemaservice.exception.DataProcessingException;
import com.itzroma.mate.cinemaservice.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleCrudDaoImpl extends AbstractCrudDao<Role> implements RoleDao {
    public RoleCrudDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(Role.RoleName roleName) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Role "
                            + "WHERE roleName = :roleNameParam", Role.class)
                    .setParameter("roleNameParam", roleName)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get role by name " + roleName, e);
        }
    }
}
