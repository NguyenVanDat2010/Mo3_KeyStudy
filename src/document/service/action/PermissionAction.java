package document.service.action;

import document.model.Permission;

import java.util.List;

public class PermissionAction {

    public static boolean checkAdminTrueFalse(List<Permission> list){
        for (Permission per : list){
            if (per.getPer_id() == 1){
                return true;
            }
        }
        return false;
    }
}
