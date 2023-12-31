import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AppInitializer {
    public static void main(String[] args) {
        try {
            Customer customer = new Customer(1001, "mash", "colombo", 2500, new Date());

            if (saveCustomer(customer)) {
                System.out.println("success");
            } else System.out.println("save error");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    private static Connection getConnections() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        // create connetion
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/hidernate", "root", "12345"
//        );
//    }

    private static boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();


        return true;
    }

            // query
//            String sql = "INSERT INTO customer VALUES(?, ? ,?, ?)";
//
//            PreparedStatement preparedStatement = getConnections().prepareStatement(sql);
//            preparedStatement.setLong(1, c.getId());
//            preparedStatement.setString(1, c.getName());
//            preparedStatement.setString(1, c.getAddress());
//            preparedStatement.setDouble(1, c.getSalary());
//            preparedStatement.setObject(1, c.getDate());
//
//            //execute
//
//            return preparedStatement.executeUpdate() > 0;



}
