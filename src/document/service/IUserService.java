package document.service;

import document.model.Users;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    List<Users> SelectAllUsers();

    Users selectUserById(int id);

    List<Users> selectAllNameAndEmail();

    Users selectNameIdByEmailPass(String email,String password);


    void insertUser(Users user) throws SQLException;

    boolean updateUser(Users user);

    boolean deleteUser(int id);

    List<Users> searchUsers(String value) throws SQLException;

    List<Users> sortUsers(String sortBy,String styleSort);

    void insertUserProcedures(Users user)throws SQLException;

    Users getUserByIdProcedures();

    void addUserTransaction(Users user, int[] permission)throws SQLException;
}
