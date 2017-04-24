package com.test;
import com.bean.Person;
import com.bean.Student;
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
        Person person=new Person("sss","222","g");
        Student student=new Student("123");
        person.setStudent(student);
        student.setPerson(person);
        session.save(person);
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(final String[] args) throws Exception {
        insert();

    }
}
