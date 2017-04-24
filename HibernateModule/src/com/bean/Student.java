package com.bean;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;



/**
 * Created by 73963 on 2017/4/18.
 */
@Entity
public class Student implements Serializable {
    private Integer id;
    private String className;
    private Person person;

    public Student() {
    }

    public Student(String className) {
        this.className = className;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
//    @PrimaryKeyJoinColumn
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Basic
    @Column(name = "className", nullable = true, length = 50)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (className != null ? !className.equals(student.className) : student.className != null) return false;
        if (person != null ? !person.equals(student.person) : student.person != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
}
