package com.burtsev.rest_test.service;

import com.burtsev.rest_test.model.Role;

import java.util.List;

public interface RoleService {
    void add(Role role);

    List<Role> getListRoles();

    List<Role> getRolesListById(List<Integer> id);

    Role getRoleById(int id);
}
