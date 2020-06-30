package document.service;

import document.model.Text;
import document.model.Users;
import document.service.action.DocumentAction;
import document.service.action.UserAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static document.service.action.UserAction.printSQLException;

public class DocumentService implements IDocumentService{
    private String jdbcUrl = "jdbc:mysql://localhost:3306/document_management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    //SQL Query
    private static final String INSERT_TEXT_VALUES = "insert into text(text_name, description, category_id) value (?,?,?); ";
    private static final String SELECT_ALL_DOCUMENT = "select text_id, text_name, description, date_created, c.name from text t inner join Category C on t.category_id = C.category_id;";
    private static final String UPDATE_DOCUMENT_VALUES = "update text set text_name = ?,description = ?, date_created = current_timestamp, category_id = ? where text_id = ?";
    private static final String SELECT_TEXT_BY_ID = "select t.text_id, t.text_name, t.description, c.name from text t inner join Category C on t.category_id = C.category_id where text_id = ?";
    private static final String DELETE_DOCUMENT_ROW = "delete from text where text_id = ?";
    private static final String SELECT_DOCUMENT_BY_CATEGORY_NAME = "select text_id, text_name, description, date_created, c.name from text t inner join Category C on t.category_id = C.category_id where c.name = ?;";



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
    public List<Text> selectAllDocument() {
        List<Text> texts = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(SELECT_ALL_DOCUMENT);
        ) {
            System.out.println(prstmt);
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                int textId = rs.getInt("text_id");
                String textName = rs.getString("text_name");
                String description = rs.getString("description");
                Timestamp  dateCreate = rs.getTimestamp("date_created");
                String categoryName = rs.getString("name");

                texts.add(new Text(textId, textName, description, dateCreate,categoryName));
            }
        } catch (SQLException throwables) {
            printSQLException(throwables);
        }
        return texts;
    }

    @Override
    public boolean deleteDocument(int id) {
        boolean rowDeleted = false;
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(DELETE_DOCUMENT_ROW);
        ) {
            System.out.println(prstmt);
            prstmt.setInt(1, id);
            rowDeleted = prstmt.executeUpdate() > 0;
            System.out.println("Successfully deleted? " + rowDeleted);
        } catch (SQLException throwables) {
            printSQLException(throwables);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateDocument(Text text) {
        boolean rowUpdated = false;
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(UPDATE_DOCUMENT_VALUES);
        ) {
            System.out.println(prstmt);
            int categoryId = DocumentAction.checkCategory(text.getCategoryName());

            prstmt.setString(1, text.getTextName());
            prstmt.setString(2, text.getDescription());
            prstmt.setInt(3, categoryId);
            prstmt.setInt(4, text.getTextId());
//            prstmt.setTimestamp(5,text.getDateCreate().getTime());

            System.out.println(prstmt);

            rowUpdated = prstmt.executeUpdate() > 0;
            System.out.println("Update successful yet? " + rowUpdated);
        } catch (SQLException throwables) {
            printSQLException(throwables);
        }
        return rowUpdated;
    }

    @Override
    public Text selectTextById(int id) {
        Text text = null;
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(SELECT_TEXT_BY_ID);
        ) {
            System.out.println(prstmt);
            prstmt.setInt(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                int textId = rs.getInt("text_id");
                String textName = rs.getString("text_name");
                String description = rs.getString("description");
                String categoryName = rs.getString("name");
                text = new Text(textId, textName, description,categoryName);
            }
        } catch (SQLException throwables) {
            printSQLException(throwables);
        }
        return text;
    }

    @Override
    public void insertDocument(Text text) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(INSERT_TEXT_VALUES);
        ) {
            int categoryId = DocumentAction.checkCategory(text.getCategoryName());
            System.out.println(prstmt);
            prstmt.setString(1, text.getTextName());
            prstmt.setString(2, text.getDescription());
            prstmt.setInt(3,categoryId);

            prstmt.executeUpdate();
            System.out.println("Insert record successfully.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public List<Text> selectDocumentByCategoryName(String categoryName) {
        List<Text> texts = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement prstmt = connection.prepareStatement(SELECT_DOCUMENT_BY_CATEGORY_NAME);
        ) {
            System.out.println(prstmt);
            prstmt.setString(1,categoryName);
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                int textId = rs.getInt("text_id");
                String textName = rs.getString("text_name");
                String description = rs.getString("description");
                Timestamp  dateCreate = rs.getTimestamp("date_created");
//                String categoryName = rs.getString("name");

                texts.add(new Text(textId, textName, description, dateCreate,categoryName));
            }
        } catch (SQLException throwables) {
            printSQLException(throwables);
        }
        return texts;
    }
}
