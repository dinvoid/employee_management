package util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import org.springframework.orm.hibernate.SessionFactoryUtils; // O kung anong Spring package gamit mo

public class DbUtil {

    // Helper para sa ResultSet
    public static void closeQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper para sa Statement / PreparedStatement
    public static void closeQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper para sa Spring/Hibernate Session Release
    public static void releaseSession(Session session, SessionFactory sessionFactory) {
        if (session != null && sessionFactory != null) {
            SessionFactoryUtils.releaseSession(session, sessionFactory);
        }
    }

    // Master Helper: Isang tawag para isara LAHAT nang sabay-sabay!
    public static void closeAll(ResultSet rs, Statement stmt, Session session, SessionFactory sessionFactory) {
        closeQuietly(rs);
        closeQuietly(stmt);
        releaseSession(session, sessionFactory);
    }
}