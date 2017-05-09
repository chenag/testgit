package com.waterapp.repository.user;

import com.waterapp.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

    @Query(value = "select * from user a where a.username=:username and a.password=:password",nativeQuery = true)
    User getAllUserInfo(@Param("username") String username,@Param("password") String password);

    @Query(value = "select * from user a where a.id=:id",nativeQuery = true)
    User getUserByUserId(@Param("id") Integer id);
}
