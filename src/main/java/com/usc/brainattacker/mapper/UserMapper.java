package com.usc.brainattacker.mapper;


import com.usc.brainattacker.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

	@Insert("Insert into user (username, password) values (#{username}, #{password})")
	void add(User user);
}
