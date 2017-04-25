package test;

import com.bean.Address;
import com.bean.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
//import org.hibernate.HibernateException;
//import org.hibernate.Metamodel;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;
//import org.hibernate.service.ServiceRegistry;

import javax.persistence.metamodel.EntityType;
import java.util.EnumSet;

public class test {
    private static final SessionFactory ourSessionFactory;
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

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

    private static void insert() {
        final Session session = getSession();
        session.beginTransaction();
        Address address = new Address("123", "123", "123", "123");
        Customer customer = new Customer("123");
        address.setCustomer(customer);
        customer.setAddress(address);
        session.save(customer);
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }

    private static void insert2() {
        Configuration configiguration = new Configuration().configure();
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.beginTransaction();
//        Address address = new Address("123", "123", "123", "123");
//        Customer customer = new Customer("123");
        Address address1 = new Address("1234", "123", "123", "123");
        Customer customer1= new Customer("1234");
//        Address address2 = new Address("12345", "123", "123", "123");
//        Customer customer2 = new Customer("12345");
        address1.setCustomer(customer1);
        customer1.setAddress(address1);
        session.save(address1);
        session.save(customer1);
        session.getTransaction().commit();
        session.close();
    }


    public static void testShemaExport() {


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    public static void main(final String[] args) throws Exception {
        insert();
//        insert2();
//        testShemaExport();
    }
}
