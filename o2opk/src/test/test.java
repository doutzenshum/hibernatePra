package test;
import com.bean.Address;
import com.bean.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

public class test {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    private static void insert(){
        final Session session = getSession();
        session.beginTransaction();
        Address address=new Address("123","123","123","123");
        Customer customer=new Customer("123");
        address.setCustomer(customer);
        customer.setAddress(address);
        session.save(address);
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(final String[] args) throws Exception {
        insert();

    }
}
