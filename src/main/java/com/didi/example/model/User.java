package com.didi.example.model;


import com.didi.example.dto.UserDto;
import com.didi.example.lib.DateUtil;
import lombok.*;

import java.util.Date;

/**
 * @author huangjin
 */
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long userId;
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
            .userId(dto.getUserId())
            .name(dto.getName())
            .mobile(dto.getMobile())
            .isDeleted(dto.isIsDeleted())
            .build();
        if (dto.getCreateTime() > 0) {
            user.setCreateTime(DateUtil.millisecondToDate(dto.getCreateTime()));
        }
        if (dto.getUpdateTime() > 0) {
            user.setUpdateTime(DateUtil.millisecondToDate(dto.getUpdateTime()));
        }
        return user;
    }
}
