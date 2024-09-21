package com.campususedtrading.mapper;

import com.campususedtrading.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into users(username,password,created_time)" +
            " values (#{username},#{password},now())")
    void addUser(String username, String password);


    @Update("update users set email=#{email} where id=#{id}")
    void update(User user);

    @Update("update users set avatar_url=#{avatarUrl} where id=#{id}")
    void updateAvatarUrl(String avatarUrl, Integer id);

    @Update("update users set password=#{password} where id=#{id}")
    void updatePwd(String password, Integer id);

    @Select("select * from users where id = #{id}")
    User findById(Integer id);
}
