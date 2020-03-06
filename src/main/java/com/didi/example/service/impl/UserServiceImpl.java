package com.didi.example.service.impl;

import com.didi.example.dto.UserDto;
import com.didi.example.event.CreateUserEvent;
import com.didi.example.lib.Err;
import com.didi.example.lib.MyException;
import com.didi.example.lib.redis.RedisPoolUtil;
import com.didi.example.mapper.UserMapper;
import com.didi.example.model.User;
import com.didi.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;
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

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public UserDto findById(long uid) throws MyException, TException {
        log.info("uid: {}", uid);
        if (userMapper == null) {
            log.error("mybatis create mapper fail.");
        }
        String key = "user:" + uid;
//        String res = stringRedisTemplate.opsForValue().get(key);
        String res = RedisPoolUtil.get(key);
        User user = null;
        try {
            if (res != null && res == "") {
                    user = objectMapper.readValue(res, User.class);
                    return user.toDto();
            }
            user = userMapper.findById(uid);
//            stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(user), 120);
            RedisPoolUtil.setEx(key,  objectMapper.writeValueAsString(user),120);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new MyException(Err.ERROR_NOT_FOUND_UID, Err.getErrorMsg(Err.ERROR_NOT_FOUND_UID));
        }

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
        if (user.getUserId() <= 0) {
            throw new MyException(Err.ERROR_PARAM, Err.getErrorMsg(Err.ERROR_PARAM));
        }
        User u = User.fromDto(user);
        userMapper.create(u);

        applicationContext.publishEvent(new CreateUserEvent(applicationContext, u));
        return u.getUserId();
    }

    @Override
    public boolean update(UserDto user) throws MyException, TException {
        if (user.getUserId() <= 0) {
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
