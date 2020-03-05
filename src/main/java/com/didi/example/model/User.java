package com.didi.example.model;


import com.didi.example.dto.UserDto;
import com.didi.example.lib.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * @author huangjin
 */
@Builder
@Data
@ToString
public class User {
    long userId;
    String name;
    String mobile;
    Date createTime;
    Date updateTime;
    Boolean isDeleted;

    public UserDto toDto() {
        return new UserDto()
            .setUserId(userId)
            .setName(name)
            .setMobile(mobile)
            .setCreateTime(DateUtil.dateToMillisecond(createTime))
            .setUpdateTime(DateUtil.dateToMillisecond(updateTime))
            .setIsDeleted(isDeleted);
    }

    public static User fromDto(UserDto dto) {
        User user =  User.builder()
            .userId(dto.userId)
            .name(dto.name)
            .mobile(dto.mobile)
            .build();
        if (dto.createTime > 0) {
            user.setCreateTime(DateUtil.millisecondToDate(dto.createTime));
        }
        if (dto.updateTime > 0) {
            user.setUpdateTime(DateUtil.millisecondToDate(dto.updateTime));
        }
        return user;
    }
}
