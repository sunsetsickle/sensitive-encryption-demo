package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    int insert(Employee employee);

    int updateById(Employee employee);

    int deleteById(@Param("id") Long id);

    Employee selectById(@Param("id") Long id);

    List<Employee> selectAll();

    List<Employee> selectByDepartment(@Param("department") String department);

    List<Employee> selectByIdCard(@Param("idCard") String idCard);
}
