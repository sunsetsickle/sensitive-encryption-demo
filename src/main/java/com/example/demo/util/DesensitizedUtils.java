package com.example.demo.util;


import org.apache.commons.lang3.StringUtils;

/**
 * @author liyangyang
 * @since 2026年03月11日 17:06
 */
public class DesensitizedUtils {

    /**
     * 通用字符串脱敏
     * @param str   需要脱敏的字符串
     * @param front 显示前缀长度
     * @param end   显示后缀长度
     */
    public static String universalString(String str, int front, int end) {
        if (StringUtils.isBlank(str)) {
            return "";
        } else if (front + end > str.length()) {
            return "";
        } else {
            return front >= 0 && end >= 0 ? hide(str, front, str.length() - end) : "";
        }
    }

    /**
     * 中文姓名脱敏
     * @param fullName  需要脱敏的姓名
     * 脱敏规则：两字留首字;三字及以上留首尾，中间用*
     */
    public static String chineseName(String fullName) {
        if (StringUtils.isBlank(fullName)){
            return "";
        }else if (fullName.length() <= 2){
            return universalString(fullName,1,0);
        }else {
            return universalString(fullName,1,1);
        }
    }

    /**
     * 详细地址脱敏
     * @param originalValue  需要脱敏的身份证号详细地址
     */
    public static String address(String originalValue) {
        String[] addressKeywords = {
                "小区", "社区", "街道", "路", "大道", "巷", "弄", "里", "村", "镇", "乡", "区", "市", "省"
        };
        if (StringUtils.isBlank(originalValue)) {
            return originalValue;
        }

        String address = originalValue.trim();
        int addressLength = address.length();

        for (String keyword : addressKeywords) {
            int keywordIndex = address.indexOf(keyword);
            if (keywordIndex != -1) {
                int cutIndex = keywordIndex + keyword.length();

                // 防止越界（如关键词在最后）
                if (cutIndex <= addressLength) {
                    return address.substring(0, cutIndex);
                }
            }
        }

        // 无匹配关键词时，保留前10位，不足则返回原地址
        int defaultCutLength = 10;
        return addressLength <= defaultCutLength ? address : address.substring(0, defaultCutLength) + "...";
    }

    /**
     * 邮箱脱敏: 保留邮箱前两位，@及后缀
     * @param email  需要脱敏的邮箱
     */
    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        } else {
            int index = StringUtils.indexOf(email, '@');
            return index <= 2 ? email : hide(email, 2, index);
        }
    }



    public static String hide(CharSequence str, int startInclude, int endExclude) {
        return replace(str, startInclude, endExclude, '*');
    }

    public static String replace(CharSequence str, int startInclude, int endExclude, char replacedChar) {
        if (isEmpty(str)) {
            return str(str);
        } else {
            String originalStr = str(str);
            int[] strCodePoints = originalStr.codePoints().toArray();
            int strLength = strCodePoints.length;
            if (startInclude > strLength) {
                return originalStr;
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }

                if (startInclude > endExclude) {
                    return originalStr;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();

                    for(int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            stringBuilder.append(replacedChar);
                        } else {
                            stringBuilder.append(new String(strCodePoints, i, 1));
                        }
                    }

                    return stringBuilder.toString();
                }
            }
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }
}
