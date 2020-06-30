package document.controller;

import document.model.Permission;
import document.model.Text;
import document.model.Users;
import document.service.*;
import document.service.action.PermissionAction;
import document.service.action.UserAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DocumentServlet",urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    IUserService userService = new UserService();
    IPermissionService permissionService = new PermissionService();
    IDocumentService documentService = new DocumentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "editUserByAd":
                    updateUserByAdmin(request,response);
                    break;
                case "signin":
                    signinUsers(request, response);
                    break;
                case "signup":
                    signupUsers(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        int id = Integer.parseInt(request.getParameter("id"));

        Users user = new Users(id, name, email, password,birthday,gender);
        this.userService.updateUser(user);

        request.getRequestDispatcher("document/admin/administration.jsp");
        response.sendRedirect("/users?action=admin");
    }

    String showName = null;

    private void signinUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        List<Permission> permissionList;

        Users user = this.userService.selectNameIdByEmailPass(email, password);
        RequestDispatcher dispatcher;
        if (user == null) {
            dispatcher = request.getRequestDispatcher("document/signin.jsp");
            request.setAttribute("message", "The email or password is incorrect with any account. Please try again!");
            dispatcher.forward(request, response);
        } else {
            showName = UserAction.getNameToShow(user);
            permissionList = this.permissionService.checkAdmin(user);
            if (PermissionAction.checkAdminTrueFalse(permissionList)) {
                request.getRequestDispatcher("document/admin/homeAdmin.jsp");
                response.sendRedirect("/users?action=homeAdmin");

            } else {
                request.getRequestDispatcher("document/homeUser.jsp");
                response.sendRedirect("/users?action=homeUser");
            }
//            request.setAttribute("user", user);
        }
    }

    private void signupUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cfPassword");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");

        List<Users> listUsers = this.userService.selectAllNameAndEmail();

        RequestDispatcher dispatcher;

        if (UserAction.checkStr(name, password, cfPassword, email, gender, birthday) && UserAction.assertEqualPass(password, cfPassword) && UserAction.checkEmail(email, listUsers) && UserAction.validate(password)) {
            Users user = new Users(name, email, password, birthday, gender);
            this.userService.insertUser(user);
            request.getRequestDispatcher("document/signup.jsp");
            response.sendRedirect("/users");
        } else {
            if (!UserAction.assertEqualPass(password, cfPassword)) {
                request.setAttribute("message", "Password is not match!");
            } else if (!UserAction.validate(password)) {
                request.setAttribute("message", "Password must contain at least 8 characters that contain at least one lowercase letter, one uppercase letter, a number and a special character!");
            } else if (!UserAction.checkEmail(email, listUsers)) {
                request.setAttribute("message", "Email already in use, Please try another email!");
            } else {
                request.setAttribute("message", "Do not leave fields blank!");
            }
            dispatcher = request.getRequestDispatcher("document/signup.jsp");
            dispatcher.forward(request, response);
        }
    }


//------------------------------------------------------------------------------------------------------------------


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "deleteUserByAd":
                deleteUserByAdmin(request,response);
                break;
            case "editUserByAd":
                showUpdateUserByAdmin(request,response);
                break;
            case "admin":
                showAdminForm(request, response);
                break;
            case "homeAdmin":
                showHomeAdminForm(request, response);
                break;
            case "homeUser":
                showHomeUserForm(request, response);
                break;
            case "signin":
                showSigninForm(request, response);
                break;
            case "signup":
                showSignupForm(request, response);
                break;
            default:
                actionSignin(request, response);
                break;
        }
    }

    private void deleteUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.userService.deleteUser(id);

        List<Users> userList = this.userService.SelectAllUsers();
        request.setAttribute("users", userList);

        request.getRequestDispatcher("document/admin/administration.jsp");
        response.sendRedirect("/users?action=admin");
    }

    private void showUpdateUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Text> listInbox = this.documentService.selectDocumentByCategoryName("Inbox");
        List<Text> listSent = this.documentService.selectDocumentByCategoryName("Sent");
        List<Text> listDraft = this.documentService.selectDocumentByCategoryName("Draft");
        List<Text> listAllDoc = this.documentService.selectAllDocument();

        Users user = this.userService.selectUserById(id);

        int lengthAllDoc = listAllDoc.size();
        int lengthInbox = listInbox.size();
        int lengthSent = listSent.size();
        int lengthDraft = listDraft.size();

        request.setAttribute("messInbox", lengthInbox);
        request.setAttribute("messSent", lengthSent);
        request.setAttribute("messDraft", lengthDraft);
        request.setAttribute("messAllDoc", lengthAllDoc);
        request.setAttribute("messAllAddDraft", lengthAllDoc + lengthDraft);

        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/editUserByAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void showAdminForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Text> listInbox = this.documentService.selectDocumentByCategoryName("Inbox");
        List<Text> listSent = this.documentService.selectDocumentByCategoryName("Sent");
        List<Text> listDraft = this.documentService.selectDocumentByCategoryName("Draft");
        List<Text> listAllDoc = this.documentService.selectAllDocument();

        List<Users> usersList = this.userService.SelectAllUsers();

        int lengthAllDoc = listAllDoc.size();
        int lengthInbox = listInbox.size();
        int lengthSent = listSent.size();
        int lengthDraft = listDraft.size();

        request.setAttribute("messInbox", lengthInbox);
        request.setAttribute("messSent", lengthSent);
        request.setAttribute("messDraft", lengthDraft);
        request.setAttribute("messAllDoc", lengthAllDoc);
        request.setAttribute("messAllAddDraft", lengthAllDoc + lengthDraft);

        request.setAttribute("users",usersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/administration.jsp");
        dispatcher.forward(request, response);
    }

    private void actionSignin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/users?action=signin");
    }

    private void showHomeAdminForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Text> listInbox = this.documentService.selectDocumentByCategoryName("Inbox");
        List<Text> listSent = this.documentService.selectDocumentByCategoryName("Sent");
        List<Text> listDraft = this.documentService.selectDocumentByCategoryName("Draft");
        List<Text> listAllDoc = this.documentService.selectAllDocument();

        int lengthAllDoc = listAllDoc.size();
        int lengthInbox = listInbox.size();
        int lengthSent = listSent.size();
        int lengthDraft = listDraft.size();

        request.setAttribute("messInbox", lengthInbox);
        request.setAttribute("messSent", lengthSent);
        request.setAttribute("messDraft", lengthDraft);
        request.setAttribute("messAllDoc", lengthAllDoc);
        request.setAttribute("messAllAddDraft", lengthAllDoc + lengthDraft);
        request.setAttribute("message", showName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/homeAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void showHomeUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", showName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/homeUser.jsp");
        dispatcher.forward(request, response);
    }

    private void showSignupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/signup.jsp");
        dispatcher.forward(request, response);
    }

    private void showSigninForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/signin.jsp");
        dispatcher.forward(request, response);
    }
}
