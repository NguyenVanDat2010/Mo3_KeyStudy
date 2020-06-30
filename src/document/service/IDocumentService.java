package document.service;

import document.model.Text;
import document.model.Users;

import java.sql.SQLException;
import java.util.List;

public interface IDocumentService {
    void insertDocument(Text text) throws SQLException;

    List<Text> selectAllDocument();

    boolean updateDocument(Text text);

    boolean deleteDocument(int id);

    Text selectTextById(int id);

    List<Text> selectDocumentByCategoryName(String categoryName);
}
