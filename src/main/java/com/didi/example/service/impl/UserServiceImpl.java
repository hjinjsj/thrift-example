package com.didi.example.service.impl;

import com.didi.example.dto.UserDto;
import com.didi.example.lib.Err;
import com.didi.example.lib.MyException;
import com.didi.example.mapper.UserMapper;
import com.didi.example.model.User;
import com.didi.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangjin
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService.Iface {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findById(long uid) throws MyException, TException {
        log.info("uid: {}", uid);
        if (userMapper == null) {
            log.error("mybatis create mapper fail.");
        }
        User user = userMapper.findById(uid);
        if (user == null) {
            throw new MyException(Err.ERROR_NOT_FOUND_UID, Err.getErrorMsg(Err.ERROR_NOT_FOUND_UID));
        }
        return user.toDto();
    }

    @Override
    public List<UserDto> findByIds(List<Long> uids) throws TException {
        List<User> userList = userMapper.findByIds(uids);
        if (userList == null) {
            return new ArrayList<>();
        }
        return userList.stream().map(User::toDto).collect(Collectors.toList());
    }

    @Override
    public long create(UserDto user) throws MyException, TException {
        if (user.userId <= 0) {
            throw new MyException(Err.ERROR_PARAM, Err.getErrorMsg(Err.ERROR_PARAM));
        }
        User u = User.fromDto(user);
        userMapper.create(u);
        return u.getUserId();
    }

    @Override
    public boolean update(UserDto user) throws MyException, TException {
        if (user.userId <= 0) {
            throw new MyException(Err.ERROR_PARAM, Err.getErrorMsg(Err.ERROR_PARAM));
        }
        int rows = userMapper.update(User.fromDto(user));
        return rows > 0 ? true : false;
    }

    @Override
    public boolean remove(long uid) throws MyException, TException {
        throw new MyException(Err.ERROR_NOT_FOUND_UID, Err.getErrorMsg(Err.ERROR_NOT_FOUND_UID));
//        return false;
    }
}
