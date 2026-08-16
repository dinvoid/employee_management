package action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

import dao.EmployeeDao;
import entity.Employee;
import util.IPartyHashMap;
import util.PartyHashMapAware;

public class EmployeeAction extends ActionSupport implements PartyHashMapAware {
    private List<Employee> empList;
    private String selectedId;
    private String selectedName;

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }
    private List<Map<String, Object>> empList2;

    public List<Map<String, Object>> getEmpList2() {
        return empList2;
    }

    public void setEmpList2(List<Map<String, Object>> empList2) {
        this.empList2 = empList2;
    }
    @SuppressWarnings({"unchecked","rawtypes"})
    private HashMap componentMap;
    private String name;
    private String department;
    private Object[] fields;
    private Date nklStart;
    private Date nklEnd;
    private String test;
    private String message;

    public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getNklStart() {
        return nklStart;
    }

    public void setNklStart(Date nklStart) {
    	 System.out.println("SET nklStart = " + nklStart);
        this.nklStart = nklStart;
    }

    public Date getNklEnd() {
        return nklEnd;
    }

    public void setNklEnd(Date nklEnd) {
    	 System.out.println("SET nklEnd = " + nklEnd);
        this.nklEnd = nklEnd;
    }


    public Object[] getFields() {
        return fields;
    }

    public void setFields(Object[] fields) {
        this.fields = fields;
    }

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
    public String getEmpList22() {
    	System.out.print("get223");
        empList2 = new ArrayList<Map<String, Object>>();

        Map<String, Object> emp1 = new HashMap<String, Object>();
        emp1.put("id", "EMP001");
        emp1.put("name", "Juan Dela Cruz");
        emp1.put("department", "IT");

        empList2.add(emp1);


        Map<String, Object> emp2 = new HashMap<String, Object>();
        emp2.put("id", "EMP0023");
        emp2.put("name", "Pedro Santos");
        emp2.put("department", "Finance");

        empList2.add(emp2);


        Map<String, Object> emp3 = new HashMap<String, Object>();
        emp3.put("id", "EMP003");
        emp3.put("name", "Maria Garcia");
        emp3.put("department", "HR");

        empList2.add(emp3);
        System.out.println("empList2 size = " + empList2.size());


        return "modal";
    }

    public String createEmployee() {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setDepartment(department);
        System.out.println("Employee form SAVE " + name + " " + department);
        System.out.println("Employee form SAVE using getter " + getName() + " " + getDepartment());
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
    public String searchEmployeeParamsv2() {


        if (name == null || name.trim().isEmpty()
                || department == null || department.trim().isEmpty()) {

            setMessage("Please input required fields");

            System.out.println("VALIDATION FAILED");

            return "ajaxError";
        }

        System.out.println("VALIDATION PASSED");

        empList = EmployeeDao.searchEmployeeParams(name, department);
        

        System.out.println("naa sa success"+empList.size());
        setMessage("Success");


        return "ajaxSuccess";
    }
    public String searchEmployeeParams() {

        try {

            if (name == null || name.trim().isEmpty()) {
                throw new RuntimeException(
                        "Please input Name.");
            }

            if (department == null || department.trim().isEmpty()) {
                throw new RuntimeException(
                        "Please select Department.");
            }

            empList = EmployeeDao.searchEmployeeParams(
                    name,
                    department);
            if (empList == null || empList.isEmpty()) {
                setMessage(
                    "Specified employee parameters do not exist on file.");
                return ERROR;
            }

            setMessage("Success retrieve");

            return SUCCESS;

        } catch (RuntimeException e) {

            System.out.println("RuntimeException :::: ");
            e.printStackTrace();

            setMessage(e.getMessage());

            return ERROR;

        } catch (Exception e) {

            System.out.println("Exception :::: ");
            e.printStackTrace();

            setMessage(e.getMessage());

            return ERROR;
        }
    }


    public String saveNKL() {

        Object[] fields = getFields();

        if (fields != null) {

            for (int i = 0; i < fields.length; i++) {

                if (fields[i] != null &&
                    StringUtils.isNotBlank(fields[i].toString())) {

                    System.out.println("Index: " + i +
                                       " Value: " + fields[i]);
                }
            }
        }
        System.out.println("test: " + test );
        System.out.println("getNklStart: " + getNklStart() +
                " getNklEnd: : " + getNklEnd());
        System.out.println("nklStart: " + nklStart +
                " nklEnd: : " + nklEnd);
        System.out.println("REQ nklStart = " +
        	    ServletActionContext.getRequest().getParameter("nklStart"));
        	 

        return SUCCESS;
    }
    public String showNKL() {
    	 System.out.println("OPEN A PAGE IN NKL");
    	return SUCCESS;
    }
        
    public String getEmployeeListApi() {
        try {
            // 1. I-prep ang HttpServletResponse para sa JSON Output
            HttpServletResponse response = ServletActionContext.getResponse();

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "*");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // 2. MOCK / DUMMY LIST MUNA (List ng Employees)
            List<Employee> employeeList = new ArrayList<Employee>();

            // Gagawa ng sample Employee 1
            Employee emp1 = new Employee();
            emp1.setId(1);            
            emp1.setName("Juans Dela Cruz");
            emp1.setDepartment("IT Departments");

            // Gagawa ng sample Employee 2
            Employee emp2 = new Employee();
            emp2.setId(2);
            emp2.setName("Maria Clara");
            emp2.setDepartment("HR Department");

            // Gagawa ng sample Employee 3
            Employee emp3 = new Employee();
            emp3.setId(4);
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
            response.getWriter().flush();
            response.getWriter().close();

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;


        // IMPORTANT: Always 'return null;' para hindi na maghanap si WebWork ng .vm o .jsp page!
       // return null;
    } 
    public String createEmployeePdf() {
        System.out.println("Creating PDF...");
        return SUCCESS;
    }

    public String downloadEmployeePdf() {
        System.out.println("Downloading PDF...");
        return SUCCESS;
    }

    public String exitEmployee() {
        System.out.println("Exit page...");
        return SUCCESS;
    }

    public String printEmp(){
    	System.out.println("goes to employee report vm and printEmp method");
    	return SUCCESS;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
