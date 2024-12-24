package com.czh.haopicturebackend.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.Getter;

@Getter
public enum UserRoleEnum {

    USER("用户","user"),
    ADMIN("管理员","admin");

    private final String text;
    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)){
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()){
            if (anEnum.value.equals(value)){
                return anEnum;
            }
        }
        return null;
    }
}
