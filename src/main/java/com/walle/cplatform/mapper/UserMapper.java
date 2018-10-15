package com.walle.cplatform.mapper;

import com.walle.cplatform.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findAll();
}
