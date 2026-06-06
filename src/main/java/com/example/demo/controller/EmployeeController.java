package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.vo.EmployeeRpVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mybatis xml 测试
 * @author liang
 */
@Tag(name = "员工管理", description = "员工CRUD（XML Mapper + TypeHandler 加密）")
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "新增员工", description = "phone/idCard/bankCard 通过 XML typeHandler 自动加密入库")
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        employeeService.add(employee);
        return employee;
    }

    @Operation(summary = "根据ID查询", description = "敏感字段自动解密返回明文")
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @Operation(summary = "查询全部员工(脱敏)")
    @GetMapping("/list")
    public List<EmployeeRpVo> list() {
        return employeeService.listAll();
    }

    @Operation(summary = "按部门搜索")
    @GetMapping("/search")
    public List<Employee> searchByDepartment(@RequestParam String department) {
        return employeeService.searchByDepartment(department);
    }

    @Operation(summary = "根据证件号查询", description = "XML 中 typeHandler 自动加密参数后查询")
    @GetMapping("/id-card")
    public List<Employee> getByIdCard(@RequestParam String idCard) {
        return employeeService.getByIdCard(idCard);
    }

    @Operation(summary = "更新员工")
    @PutMapping
    public Employee update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return employeeService.getById(employee.getId());
    }

    @Operation(summary = "删除员工")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "删除成功";
    }
}
