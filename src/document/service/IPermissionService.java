package document.service;

import document.model.Permission;
import document.model.Users;

import java.util.List;

public interface IPermissionService {

    List<Permission> checkAdmin(Users user);
}
