package action;

import com.opensymphony.xwork.ActionSupport;

public class HelloAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	   private String employeeName;
	   private String name;

    @Override
    public String execute() {
    	System.out.print("Helloaction executed");
    	this.name = "Juan Dela Cruz"; 
        return SUCCESS;
       
    }
    public String display() {


        System.out.println("request param = " );
        System.out.println("property = " + employeeName);
    	
    	return SUCCESS;
    }
    public String display2() {


        System.out.println("request param = " );
        System.out.println("property = " + employeeName);
    	
    	return SUCCESS;
    }
    // kailangan may getter para mabasa ni Velocity
    public String getName() {
        return name;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
