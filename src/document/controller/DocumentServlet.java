package document.controller;

import document.model.Text;
import document.service.DocumentService;
import document.service.IDocumentService;
import document.service.action.DocumentAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/document")
public class DocumentServlet extends HttpServlet {
    IDocumentService documentService = new DocumentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }try {
            switch (action){
                case "editDoc":
                    updateDocAdmin(request,response);
                    break;
                case "composeAdmin":
                    composeText(request,response);
                    break;
                default:
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void updateDocAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String textName= request.getParameter("textName");
        String description= request.getParameter("description");
        String category= request.getParameter("category");
        int id = Integer.parseInt(request.getParameter("id"));

        Text text = new Text(id,textName, description, category);
        this.documentService.updateDocument(text);

        request.getRequestDispatcher("document/admin/listDocumentAdmin.jsp");
        response.sendRedirect("/document?action=allDcmtAdmin");

//        dispatcher.forward(request, response);

    }

    private void composeText(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String textName= request.getParameter("textName");
        String description= request.getParameter("description");
        String category= request.getParameter("category");
//        int categoryId=DocumentAction.checkCategory(category);

        Text text  = new Text(textName,description,category);

        if (DocumentAction.checkSpace(textName,description)){
            this.documentService.insertDocument(text);
            request.setAttribute("message","Add record successfully!");
        }else {
            request.setAttribute("message","Fields cannot be empty!");
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("document/admin/composeAdmin.jsp");
        dispatcher.forward(request, response);
    }


//-------------------------------------------------------------------------------------------------------------------------


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action){
            case "inboxAdmin":
                showDocByInbox(request,response);
                break;
            case "sentAdmin":
                showDocBySent(request,response);
                break;
            case "draftAdmin":
                showDocByDraft(request,response);
                break;
            case "deleteDoc":
                deleteDocAdmin(request,response);
                break;
            case "editDoc":
                showEditDocAdminForm(request,response);
                break;
            case "allDcmtAdmin":
                showAllDocument(request,response);
                break;
            case "composeAdmin":
                showComposeForm(request,response);
                break;
            default:
                break;
        }
    }

    private void showDocByInbox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Text> textList =this.documentService.selectDocumentByCategoryName("Inbox");

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

        request.setAttribute("documents",textList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/inboxDocAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void showDocBySent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Text> textList =this.documentService.selectDocumentByCategoryName("Sent");

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

        request.setAttribute("documents",textList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/sentDocAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void showDocByDraft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Text> textList =this.documentService.selectDocumentByCategoryName("Draft");

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

        request.setAttribute("documents",textList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/draftDocAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteDocAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.documentService.deleteDocument(id);

        List<Text> textList = this.documentService.selectAllDocument();
        request.setAttribute("texts", textList);

        request.getRequestDispatcher("document/admin/listDocumentAdmin.jsp");
        response.sendRedirect("/document?action=allDcmtAdmin");
    }

    private void showEditDocAdminForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Text text = this.documentService.selectTextById(id);

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

        request.setAttribute("text", text);
        RequestDispatcher dispatcher= request.getRequestDispatcher("document/admin/editDocAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Text> textList =this.documentService.selectAllDocument();

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

        request.setAttribute("documents",textList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/listDocumentAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void showComposeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("document/admin/composeAdmin.jsp");
        dispatcher.forward(request, response);
    }
}
