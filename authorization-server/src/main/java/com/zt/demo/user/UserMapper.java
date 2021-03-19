package com.zt.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangkang
 */
@Repository
public interface UserMapper extends JpaRepository<AuthorizationUser, Long> {
    /**
     * xx
     *
     * @param username XX
     * @return XX
     */
    AuthorizationUser findUserByUsername(String username);


}