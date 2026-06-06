package com.example.demo.annotation;

import com.example.demo.util.DesensitizedUtils;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author liyangyang
 * @since 2026年03月11日 10:30
 * &#064;description  数据脱敏枚举
 */

public enum SensitizeRuleEnums {


    /**
     * 中文姓名脱敏
     */
    CHINESE_NAME(DesensitizedUtils::chineseName),

    /**
     * 身份证脱敏
     */
    ID_CARD(s -> DesensitizedUtils.universalString(s, 6, 4)),


    /**
     * 手机号脱敏
     */
    MOBILE_PHONE(s-> DesensitizedUtils.universalString(s, 3, 4)),

    /**
     * 地址脱敏
     */
    ADDRESS(DesensitizedUtils::address),

    /**
     * 电子邮箱脱敏
     */
    EMAIL(DesensitizedUtils::email),



    /**
     * 银行卡脱敏
     */
    BANK_CARD(s-> DesensitizedUtils.universalString(s, 6, 4));

    /**
     * 可自行添加其他脱敏策略.....
     */
    private final Function<String, String> sensitize;

    public Function<String, String> sensitize() {
        return sensitize;
    }

    SensitizeRuleEnums(UnaryOperator<String> sensitize) {
        this.sensitize = sensitize;
    }
}
