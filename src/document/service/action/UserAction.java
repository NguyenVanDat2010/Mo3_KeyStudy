package document.service.action;

import document.model.Users;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAction {
    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static String clearSpaceValue(String value) {
        if (value != "") {
            if (value.charAt(0) == ' ' || value.charAt(value.length() - 1) == ' ') {
                value = value.trim(); //xóa khoảng trắng ở đầu và cuối thôi
            }
            return value;
        }
        return "";
    }

    public static boolean assertEqualPass(String pass, String cfPass) {
        if (!pass.equals(cfPass)) {
            return false;
        }
        return true;
    }

    public static boolean checkStr(String name, String pass, String cfPass, String email, String gender, String birthday) {
        if (name.equals("") || pass.equals("") || cfPass.equals("") || email.equals("") || gender.equals("") || birthday.equals("")) {
            return false;
        }
        return true;
    }

    public static boolean checkEmail(String email, List<Users> usersList) {
        for (Users users : usersList) {
            if (users.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public static boolean validate(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
        /*
         * (?=.*[0-9]) a digit must occur at least once
         * (?=.*[a-z]) a lower case letter must occur at least once
         * (?=.*[A-Z]) an upper case letter must occur at least once
         * (?=.*[@#$%^&+=]) a special character must occur at least once
         * (?=\\S+$) no whitespace allowed in the entire string
         * .{8,} at least 8 characters
         * Ex: "aaZZa44@"
         * */
    }

    public static String getNameToShow(Users users){
        return users.getName();
    }
}
