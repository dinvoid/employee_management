package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate.SessionFactoryUtils;

import entity.Employee;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import util.DbUtil;
import util.SpringHelper;

public class EmployeeDao {
	public static List<Employee> searchEmployee() {
		 Connection conn=null;
		 SessionFactory sessionFactory =
		            (SessionFactory) SpringHelper
		                .getAppctx()
		                .getBean("empmSessionFactory");

		 Session session =
		            SessionFactoryUtils.getSession(sessionFactory, true);

		 Statement stmt = null;
		 ResultSet rs=null;
		 List<Employee> empList=new ArrayList<Employee>();
		        try {
		        	
		        	conn=session.connection();
		        	stmt=conn.createStatement();
		        			
		        	String sql="select name,department from employee";
		        	rs=stmt.executeQuery(sql);
		        	Employee emp=null;
		        	while(rs.next()) {
		        		emp=new Employee();
		        		emp.setName(rs.getString("NAME"));
		        		emp.setDepartment(rs.getString("DEPARTMENT"));
		        		
		        		empList.add(emp);
		        	}
		        	emp=null;
		        
			    } catch (SQLException e) {
			
			        e.printStackTrace();
			
			    } catch (HibernateException e) {
			
			        e.printStackTrace();
			
			    } finally {
			    	DbUtil.closeQuietly(rs);
			    	DbUtil.closeQuietly(stmt);
			    	DbUtil.releaseSession(session, sessionFactory);
			    }
		        return empList;

}
		        
		        
		


    public static void addEmployee(Employee employee) {

        SessionFactory sessionFactory =
            (SessionFactory) SpringHelper
                .getAppctx()
                .getBean("empmSessionFactory");

        Session session =
            SessionFactoryUtils.getSession(sessionFactory, true);

        PreparedStatement pstmtInsert = null;
        System.out.println("Employee form in the dao class"+ employee.getName() +"" + employee.getDepartment());

        try {

            String insertSql =
                "INSERT INTO EMPLOYEE (ID, NAME, DEPARTMENT) "
              + "VALUES (nextval('EMPLOYEE_SEQ'),?, ?)";

            System.out.println("Employee insertSql : " + insertSql);
      
            pstmtInsert =
                session.connection().prepareStatement(insertSql);

            
            pstmtInsert.setString(1, employee.getName());
 
            pstmtInsert.setString(2, employee.getDepartment());

            pstmtInsert.executeUpdate();
            session.connection().commit();

            System.out.println("Employee successfully saved.");

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (HibernateException e) {

            e.printStackTrace();

        } finally {

            try {

                if (pstmtInsert != null) {
                    pstmtInsert.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

            DbUtil.releaseSession(session, sessionFactory);
        }

    }



    public static List<Employee> searchEmployeeParams(String name, String department) {

        Connection conn = null;
        SessionFactory sessionFactory =
            (SessionFactory) SpringHelper
                .getAppctx()
                .getBean("empmSessionFactory");

        Session session =
            SessionFactoryUtils.getSession(sessionFactory, true);

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Employee> empList = new ArrayList<Employee>();

        try {

            conn = session.connection();

            String sql =
                "SELECT ID, NAME, DEPARTMENT " +
                "FROM EMPLOYEE " +
                "WHERE NAME = ? " +
                "AND DEPARTMENT = ?";

            System.out.println("Employee searchSql: " + sql);
            System.out.println("Name parameter: " + name);
            System.out.println("Department parameter: " + department);

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, department);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                Employee emp = new Employee();

                emp.setId(rs.getInt("ID"));
                emp.setName(rs.getString("NAME"));
                emp.setDepartment(rs.getString("DEPARTMENT"));

                empList.add(emp);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (HibernateException e) {

            e.printStackTrace();

        } finally {

            DbUtil.closeQuietly(rs);
            DbUtil.closeQuietly(pstmt);
            DbUtil.releaseSession(session, sessionFactory);
        }

        return empList;
    }

	public static List<Employee> searchEmployeeParamsv1(String name, String department) {
		 Connection conn=null;
		 SessionFactory sessionFactory =
		            (SessionFactory) SpringHelper
		                .getAppctx()
		                .getBean("empmSessionFactory");

		 Session session =
		            SessionFactoryUtils.getSession(sessionFactory, true);

		 Statement stmt = null;
		 ResultSet rs=null;
		 List<Employee> empList=new ArrayList<Employee>();
		        try {
		        	
		        	conn=session.connection();
		        	stmt=conn.createStatement();
		        			
		        	String sql = "SELECT name, department " +
		                    "FROM employee " +
		                    "WHERE name = ? " +
		                    "AND department = ?";
		        	rs=stmt.executeQuery(sql);
		        	Employee emp=null;
		        	while(rs.next()) {
		        		emp=new Employee();
		        		emp.setName(rs.getString("NAME"));
		        		emp.setDepartment(rs.getString("DEPARTMENT"));
		        		
		        		empList.add(emp);
		        	}
		        	emp=null;
		        
			    } catch (SQLException e) {
			
			        e.printStackTrace();
			
			    } catch (HibernateException e) {
			
			        e.printStackTrace();
			
			    } finally {
			    	DbUtil.closeQuietly(rs);
			    	DbUtil.closeQuietly(stmt);
			    	DbUtil.releaseSession(session, sessionFactory);
			    }
		        return empList;
	}

}