package com.didi.example.service.impl;

import com.didi.example.dto.UserDto;
import com.didi.example.lib.Err;
import com.didi.example.lib.MyException;
import com.didi.example.model.User;
import com.didi.example.service.UserService;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;

import java.util.Date;
import java.util.List;

/**
 * @author huangjin
 */
public class UserServiceImpl implements UserService.Iface {
    @Override
    public UserDto findById(long uid) throws MyException, TException {
        User user = User.builder()
            .id(1)
            .name("hjin")
            .mobile("86-15201330136")
            .createTime(new Date())
            .updateTime(new Date())
            .isDeleted(false)
            .build();
        return user.toDto();
    }

    @Override
    public List<UserDto> findByIds(List<Long> uids) throws TException {
        User user = User.builder()
            .id(1)
            .name("hjin")
            .mobile("86-15201330136")
            .createTime(new Date())
            .updateTime(new Date())
            .isDeleted(false)
            .build();
        return Lists.newArrayList(user.toDto());
    }

    @Override
    public long create(UserDto user) throws MyException, TException {
        return 2;
    }

    @Override
    public boolean update(UserDto user) throws MyException, TException {
        return false;
    }

    @Override
    public boolean remove(long uid) throws MyException, TException {
        throw new MyException(Err.ERROR_NOT_FOUND_UID, Err.getErrorMsg(Err.ERROR_NOT_FOUND_UID));
//        return false;
    }
}
