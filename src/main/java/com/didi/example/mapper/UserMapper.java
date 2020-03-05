package com.didi.example.mapper;

import com.didi.example.model.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author huangjin
 */
//如果App类上加了MapScan注解这里可以不用加Mapper注
public interface UserMapper {

    Long create(User user);

    Integer update(User user);

    Integer remove(@Param("uid") long uid);

    User findById(@Param("uid") long uid);

    List<User> findByIds(@Param("ids") List<Long> ids);
}