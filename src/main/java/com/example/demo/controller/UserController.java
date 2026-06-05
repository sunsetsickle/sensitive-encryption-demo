package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mybatis-plus 测试
 */
@Tag(name = "用户管理", description = "用户CRUD操作（敏感字段自动加密）")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "创建用户", description = "phone/idCard/email 入库时自动加密存储")
    @PostMapping
    public User create(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @Operation(summary = "根据ID查询", description = "phone/idCard/email 自动解密返回明文")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "查询全部用户")
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @Operation(summary = "按用户名搜索")
    @GetMapping("/search")
    public List<User> searchByUsername(@RequestParam String username) {
        return userService.list(
                new LambdaQueryWrapper<User>()
                        .like(User::getUsername, username)
        );
    }

    @Operation(summary = "根据证件号查询", description = "Service层手动加密后查询（MP Wrapper方式）")
    @GetMapping("/id-card")
    public List<User> getByIdCard(@RequestParam String idCard) {
        return userService.getByIdCard(idCard);
    }

    @Operation(summary = "更新用户")
    @PutMapping
    public User update(@RequestBody User user) {
        userService.updateById(user);
        return userService.getById(user.getId());
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.removeById(id);
        return "删除成功";
    }
}
