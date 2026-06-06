package com.example.demo.vo;

import com.example.demo.annotation.Sensitize;
import com.example.demo.annotation.SensitizeRuleEnums;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author liang
 */
@Data
@Schema(description = "员工实体")
public class EmployeeRpVo {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "姓名")
    @Sensitize(rule = SensitizeRuleEnums.CHINESE_NAME)
    private String name;

    @Schema(description = "手机号（入库自动加密）")
    @Sensitize(rule = SensitizeRuleEnums.MOBILE_PHONE)
    private String phone;

    @Schema(description = "身份证号（入库自动加密）")
    @Sensitize(rule = SensitizeRuleEnums.ID_CARD)
    private String idCard;

    @Schema(description = "银行卡号（入库自动加密）")
    @Sensitize(rule = SensitizeRuleEnums.BANK_CARD)
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
