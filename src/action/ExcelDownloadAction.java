package action;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

import util.ExcelGenerator;
// import mo yung totoong Employee model/VM class niyo

public class ExcelDownloadAction extends ActionSupport {

    private List empList; // gamitin yung totoong type ng employee list niyo

    public String execute() throws Exception {

        // Kunin yung employee list - pareho sa ginagamit sa Process/Search
       // empList = getEmployeeList(); // palitan ng totoong DAO/service call niyo

        byte[] excelBytes = ExcelGenerator.generateExcel();

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"employee_report.xlsx\"");
        response.setContentLength(excelBytes.length);

        OutputStream out = response.getOutputStream();
        out.write(excelBytes);
        out.flush();
        out.close();

        return null;
    }

}