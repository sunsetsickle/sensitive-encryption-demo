package com.example.demo.service.impl;
import java.util.ArrayList;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.EmployeeService;
import com.example.demo.vo.EmployeeRpVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Override
    public void add(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.updateById(employee);
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteById(id);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public List<EmployeeRpVo> listAll() {
        List<Employee> employees = employeeMapper.selectAll();
        List<EmployeeRpVo> collect = employees.stream().map(employee -> {
            EmployeeRpVo employeeRpVo = new EmployeeRpVo();
            employeeRpVo.setId(employee.getId());
            employeeRpVo.setName(employee.getName());
            employeeRpVo.setPhone(employee.getPhone());
            employeeRpVo.setIdCard(employee.getIdCard());
            employeeRpVo.setBankCard(employee.getBankCard());
            employeeRpVo.setSalary(employee.getSalary());
            employeeRpVo.setDepartment(employee.getDepartment());
            employeeRpVo.setCreateTime(employee.getCreateTime());
            employeeRpVo.setUpdateTime(employee.getUpdateTime());
            return employeeRpVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Employee> searchByDepartment(String department) {
        return employeeMapper.selectByDepartment(department);
    }

    @Override
    public List<Employee> getByIdCard(String idCard) {
        return employeeMapper.selectByIdCard(idCard);
    }
}
