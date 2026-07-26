package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

import dao.EmployeeDao;
import entity.Employee;
import util.IPartyHashMap;
import util.PartyHashMapAware;

public class EmployeeAction extends ActionSupport implements PartyHashMapAware {
    private List<Employee> empList;
    @SuppressWarnings({"unchecked","rawtypes"})
    private HashMap componentMap;
    private String name;
    private String department;

    // Helper function para hindi paulit-ulit
    private void ensureComponentMap() {
        if (componentMap == null) {
            componentMap = new HashMap<>();
            System.out.println("componentMap initialized");
        }
    }

    @Override
    public void setIPartyHashMap(IPartyHashMap ihash) {
        componentMap = ihash.getPartyHashMap();
        if (componentMap == null) {
            componentMap = new HashMap();
            ihash.setPartyHashMap(componentMap);
            System.out.println("component initialized via PartyHashMapAware");
        }
    }

    @Override
    public String execute() {
        System.out.println("Employee form");
       // componentMap.clear();
        return SUCCESS;
    }

    public String createEmployee() {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setDepartment(department);
        System.out.println("Employee form SAVE " + name + " " + department);
        EmployeeDao.addEmployee(emp);

        return SUCCESS;
    }

    public String searchEmployee() {
        empList = EmployeeDao.searchEmployee();

        System.out.println("Get employee from dbb " + empList);
        System.out.println("emp size " + empList.size());

        // Debug
        System.out.println("empList.size() = " + empList.size());
        System.out.println("getEmpList().size() = " + getEmpList().size());

       // ensureComponentMap();
       // componentMap.put("empList", empList);
        //System.out.println("cpmap"+componentMap);

        System.out.println("end");

        return SUCCESS;
    }
    public String getEmployeeListApi() {
        try {
            // 1. I-prep ang HttpServletResponse para sa JSON Output
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // 2. MOCK / DUMMY LIST MUNA (List ng Employees)
            List<Employee> employeeList = new ArrayList<Employee>();

            // Gagawa ng sample Employee 1
            Employee emp1 = new Employee();
            emp1.setName("Juan Dela Cruz");
            emp1.setDepartment("IT Department");

            // Gagawa ng sample Employee 2
            Employee emp2 = new Employee();
            emp2.setName("Maria Clara");
            emp2.setDepartment("HR Department");

            // Gagawa ng sample Employee 3
            Employee emp3 = new Employee();
            emp3.setName("Crisostomo Ibarra");
            emp3.setDepartment("Finance Department");

            // Ilagay sa List
            employeeList.add(emp1);
            employeeList.add(emp2);
            employeeList.add(emp3);

            // 3. I-convert ang List papuntang JSON String gamit ang Gson
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(employeeList);

            // 4. Isulat at I-send sa Response pabalik sa Angular Frontend
            response.getWriter().write(jsonOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // IMPORTANT: Always 'return null;' para hindi na maghanap si WebWork ng .vm o .jsp page!
        return null;
    } 

    // Getters and setters
    public List<Employee> getEmpList() {
        return empList;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}
