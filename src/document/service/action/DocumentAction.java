package document.service.action;

import document.model.Text;

import java.util.List;

public class DocumentAction {

    public static int checkCategory(String category) {
        if (category.equals("inbox")) {
            return 1;
        } else if (category.equals("sent")) {
            return 2;
        } else
            return 3;
    }

    public static boolean checkSpace(String textName, String description) {
        if (textName.equals("") || description.equals("")) {
            return false;
        }
        return true;
    }

//    public static String getCategoryName(int categoryId) {
//        if (categoryId == 1) {
//            return "Inbox";
//        } else if (categoryId == 2) {
//            return "Sent";
//        } else
//            return "Draft";
//    }
}
