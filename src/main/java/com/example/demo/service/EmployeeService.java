package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void add(Employee employee);

    void update(Employee employee);

    void delete(Long id);

    Employee getById(Long id);

    List<Employee> listAll();

    List<Employee> searchByDepartment(String department);

    List<Employee> getByIdCard(String idCard);
}
