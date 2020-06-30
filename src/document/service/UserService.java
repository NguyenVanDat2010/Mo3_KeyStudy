package document.service;

import document.model.Users;
import document.service.action.UserAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
//    Service service =new Service();

    private String jdbcUrl = "jdbc:mysql://localhost:3306/document_management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    //SQL Query
    private static final String INSERT_USERS_VALUES = "insert into users(name, email, password, birthday, gender)" + "value(?, ?, ? ,? ,?)";
    private static final String CHECK_EMAIL_PASS_USERS = "select id, name , email from users where email= ? and password = ?;";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String SELECT_ALL_NAME_EMAILS = "select id, name, email from users;";
    private static final String SELECT_USERS_BY_ID = "select * from users where id = ?";
    private static final String UPDATE_USERS_ROW = "update users set name = ?,email = ?, password = ?, birthday = ?, gender = ? where id = ?";
    private static final String DELETE_USERS_ROW = "delete from users where id = ?";



    private static final String SELECT_ACTION_CODE = "select action_code from Users as U"
            + " inner join User_Permission UP on U.id = UP.user_id"
            + " inner join Permission as P on UP.per_id = P.per_id"
            + " inner join Permission_detail Pd on P.per_id = Pd.per_id"
            + " where U.id = ? and UP.licensed = 1 and check_action = 1;";


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
    public List<Users> SelectAllUsers() {
        List<Users> users = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(SELECT_ALL_USERS);
        ) {
            System.out.println(prstmt);
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthday = rs.getString("birthday");
                String gender = rs.getString("gender");
                users.add(new Users(id, name, email, password,birthday,gender));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

        @Override
    public Users selectUserById(int id) {
            Users user = null;
            try (
                    Connection connection = getConnection();
                    PreparedStatement prstmt = connection.prepareStatement(SELECT_USERS_BY_ID);
            ) {
                System.out.println(prstmt);
                prstmt.setInt(1, id);
                ResultSet rs = prstmt.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String birthday = rs.getString("birthday");
                    String gender = rs.getString("gender");

                    user = new Users(id, name, email, password,birthday,gender);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return user;
    }


    @Override
    public List<Users> selectAllNameAndEmail() {
        List<Users> users = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(SELECT_ALL_NAME_EMAILS);
        ) {
            System.out.println(prstmt);
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("name");
                int id = rs.getInt("id");
                users.add(new Users(id ,name, email));
            }
        } catch (SQLException throwables) {
            UserAction.printSQLException(throwables);
        }
        return users;
    }

    @Override
    public Users selectNameIdByEmailPass(String email, String password) {
        Users user = null;
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(CHECK_EMAIL_PASS_USERS);
        ) {
            System.out.println(prstmt);
            prstmt.setString(1, email);
            prstmt.setString(2, password);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                user = new Users(id, name, email);
            }
        } catch (SQLException throwables) {
            UserAction.printSQLException(throwables);
        }
        return user;
    }

    @Override
    public void insertUser(Users user) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(INSERT_USERS_VALUES);
        ) {
            System.out.println(prstmt);
            prstmt.setString(1, user.getName());
            prstmt.setString(2, user.getEmail());
            prstmt.setString(3, user.getPassword());
            prstmt.setString(4, user.getBirthday());
            prstmt.setString(5, user.getGender());

            System.out.println(prstmt);

            prstmt.executeUpdate();
            System.out.println("Insert record successfully.");
        } catch (SQLException e) {
            UserAction.printSQLException(e);
        }
    }

    @Override
    public boolean updateUser(Users user) {
        boolean rowUpdated = false;
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(UPDATE_USERS_ROW);
        ) {
            System.out.println(prstmt);
            prstmt.setString(1, user.getName());
            prstmt.setString(2, user.getEmail());
            prstmt.setString(3, user.getPassword());
            prstmt.setString(4, user.getBirthday());
            prstmt.setString(5, user.getGender());
            prstmt.setInt(6, user.getId());

            System.out.println(prstmt);

            rowUpdated = prstmt.executeUpdate() > 0;
            System.out.println("Update successful yet? " + rowUpdated);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(DELETE_USERS_ROW);
        ) {
            System.out.println(prstmt);
            prstmt.setInt(1, id);
            rowDeleted = prstmt.executeUpdate() > 0;
            System.out.println("Successfully deleted? " + rowDeleted);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public List<Users> searchUsers(String value) throws SQLException {
        return null;
    }

    @Override
    public List<Users> sortUsers(String sortBy, String styleSort) {
        return null;
    }

    @Override
    public void insertUserProcedures(Users user) throws SQLException {

    }

    @Override
    public Users getUserByIdProcedures() {
        return null;
    }

    @Override
    public void addUserTransaction(Users user, int[] permission) throws SQLException {

    }

}
