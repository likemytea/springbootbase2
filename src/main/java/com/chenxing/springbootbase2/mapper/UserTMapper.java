package com.chenxing.springbootbase2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.chenxing.springbootbase2.domain.UserT;

public interface UserTMapper {
	@Select("SELECT * FROM user_t")
	@Results({ @Result(property = "userName", column = "user_name"),
			@Result(property = "userId", column = "user_id") })
	List<UserT> getAll();

	// @Select("SELECT * FROM users WHERE id = #{id}")
	// @Results({ @Result(property = "userSex", column = "user_sex", javaType =
	// UserSexEnum.class),
	// @Result(property = "nickName", column = "nick_name") })
	// UserEntity getOne(Long id);
	//
	// @Insert("INSERT INTO users(userName,passWord,user_sex)
	// VALUES(#{userName}, #{passWord}, #{userSex})")
	// void insert(UserEntity user);
	//
	// @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName}
	// WHERE id =#{id}")
	// void update(UserEntity user);
	//
	// @Delete("DELETE FROM users WHERE id =#{id}")
	// void delete(Long id);
}
