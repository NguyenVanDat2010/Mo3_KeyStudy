package document.service;

import document.model.Permission;
import document.model.Users;
import document.service.action.UserAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionService implements IPermissionService{
    private String jdbcUrl = "jdbc:mysql://localhost:3306/document_management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String CHECK_ADMIN_SELECT_PER_ID = "select P.per_id from Permission P"
            + " inner join User_Permission UP on P.per_id = UP.per_id"
            + " inner join Users U on UP.user_id = U.id"
            + " where U.id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            System.out.println("Connection success");
        } catch (ClassNotFoundException e) {
            // TODO auto-generated catch block
            e.printStackTrace();
            System.out.println("Connection failed");
        } catch (SQLException throwables) {
            // TODO auto-generated catch block
            throwables.printStackTrace();
            System.out.println("Connection failed");
        }
        return connection;
    }

    @Override
    public List<Permission> checkAdmin(Users user) {
        List<Permission> permissions = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(CHECK_ADMIN_SELECT_PER_ID);
        ) {
            System.out.println(prstmt);
            prstmt.setInt(1,user.getId());
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                int per_id = rs.getInt("per_id");
                permissions.add(new Permission(per_id));
            }
        } catch (SQLException throwables) {
            UserAction.printSQLException(throwables);
        }
        return permissions;
    }
}
