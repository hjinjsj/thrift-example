package com.didi.example.service.impl;

import com.didi.example.dto.UserDto;
import com.didi.example.lib.MyException;
import com.didi.example.service.UserService;
import org.apache.thrift.TException;

import java.util.List;

public class UserServiceImpl implements UserService.Iface {
    @Override
    public UserDto findById(long uid) throws MyException, TException {
        return null;
    }

    @Override
    public List<UserDto> findByIds(List<Long> uids) throws TException {
        return null;
    }

    @Override
    public long create(UserDto user) throws MyException, TException {
        return 0;
    }

    @Override
    public boolean update(UserDto user) throws MyException, TException {
        return false;
    }

    @Override
    public boolean remove(long uid) throws MyException, TException {
        return false;
    }
}
