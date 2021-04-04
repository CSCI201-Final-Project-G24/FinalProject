package com.usc.brainattacker.mapper;


import com.usc.brainattacker.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

	@Insert("Insert into user (username, password) values (#{username}, #{password})")
	void add(User user);

	@Select("select password from user where username = #{username}")
    String getUserPassword(String username);

	@Select("select uid from user where username = #{username}")
    int getUserID(String username);
	
	@Select("select win_number from userprofile where username = #{username}")
    int getUserWinNumber(String username);

	@Select("select game_number from userorofile where username = #{username}")
    int getUserGameNumber(String username);

}
