package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "员工实体")
public class Employee {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号（入库自动加密）")
    private String phone;

    @Schema(description = "身份证号（入库自动加密）")
    private String idCard;

    @Schema(description = "银行卡号（入库自动加密）")
    private String bankCard;

    @Schema(description = "薪资")
    private BigDecimal salary;

    @Schema(description = "部门")
    private String department;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
