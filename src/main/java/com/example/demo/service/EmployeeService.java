package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.vo.EmployeeRpVo;

import java.util.List;

public interface EmployeeService {

    void add(Employee employee);

    void update(Employee employee);

    void delete(Long id);

    Employee getById(Long id);

    List<EmployeeRpVo> listAll();

    List<Employee> searchByDepartment(String department);

    List<Employee> getByIdCard(String idCard);
}
