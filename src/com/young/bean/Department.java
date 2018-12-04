package com.young.bean;

import java.io.Serializable;
import java.util.List;

public class Department /*implements Serializable*/ {
    private Integer id;
    private String deptName;

    public Department() {
    }

    public Department(Integer id, String deptName, List<Employee> employees) {

        this.id = id;
        this.deptName = deptName;
        this.employees = employees;
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    //复杂属性:一对多
    private List<Employee> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", employees=" + employees +
                '}';
    }
}
