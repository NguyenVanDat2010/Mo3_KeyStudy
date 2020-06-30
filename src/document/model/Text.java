package document.model;

import java.sql.Timestamp;
import java.util.Date;

public class Text {
    private int textId;
    private String textName;
    private String description;
    private Timestamp  dateCreate;
    private String categoryName;

    public Text() {
    }

    public Text(String textName, String description, String categoryName) {
        this.textName = textName;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Text(int textId, String textName, String description, String categoryName) {
        this.textId = textId;
        this.textName = textName;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Text(int textId, String textName, String description, Timestamp dateCreate, String categoryName) {
        this.textId = textId;
        this.textName = textName;
        this.description = description;
        this.dateCreate = dateCreate;
        this.categoryName = categoryName;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String  categoryName) {
        this.categoryName = categoryName;
    }

}
