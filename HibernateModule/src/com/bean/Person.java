package com.bean;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by 73963 on 2017/4/18.
 */
@Table(name = "person")
@Entity
public class Person implements Serializable{
    private Integer id;
    private String name;
    private String heigth;
    private String sex;

    @PrimaryKeyJoinColumn(name = "id", referencedColumnName="id")
    private Student student;

    public Person(String name, String heigth, String sex) {
        this.name = name;
        this.heigth = heigth;
        this.sex = sex;
    }

    public Person() {
    }

    @Id
//    @Column(name = "id", nullable = false)
//    @GenericGenerator(name = "ForeignKey",strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(name = "property",value = "student")})
//    @GeneratedValue(generator = "ForeignKey")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "foreign", parameters = { @Parameter(name = "property", value = "student")})
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "person")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "heigth", nullable = true, length = 4)
    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (heigth != null ? !heigth.equals(person.heigth) : person.heigth != null) return false;
        if (sex != null ? !sex.equals(person.sex) : person.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (heigth != null ? heigth.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }
}
