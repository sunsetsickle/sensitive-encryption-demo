package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.handler.EncryptTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_user")
@Schema(description = "用户实体")
public class User {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @TableField(typeHandler = EncryptTypeHandler.class)
    @Schema(description = "手机号（入库自动加密）")
    private String phone;

    @TableField(typeHandler = EncryptTypeHandler.class)
    @Schema(description = "身份证号（入库自动加密）")
    private String idCard;

    @TableField(typeHandler = EncryptTypeHandler.class)
    @Schema(description = "邮箱（入库自动加密）")
    private String email;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
