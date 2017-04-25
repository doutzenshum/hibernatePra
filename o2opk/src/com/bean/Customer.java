package com.bean;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 73963 on 2017/4/24.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable{
    private int id;
    private String name;
    private Address address;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

//    @Id
//    @Column(name = "id", nullable = false)
//    @TableGenerator(name="GEN_INDEX")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_INDEX")

    @Id
//    @GenericGenerator(name="pkGenerator",strategy="foreign", parameters = { @Parameter(name = "property", value = "customer") })
//    @GeneratedValue(generator="pkGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = {CascadeType.ALL},mappedBy = "customer")
    @PrimaryKeyJoinColumn
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
