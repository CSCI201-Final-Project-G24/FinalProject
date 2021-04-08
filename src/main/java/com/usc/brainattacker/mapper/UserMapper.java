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

	@Insert("Insert into userProfile (uid, win_number, game_number) values (0, 0)")
	void addProfile();

	@Select("Select COUNT(username) from user where username = #{username}")
	int ifOccupied(String username);

	@Select("select password from user where username = #{username}")
    String getUserPassword(String username);

	@Select("select uid from user where username = #{username}")
    int getUserID(String username);
	
	@Select("select win_number from userProfile where uid = #{uid}")
    int getUserWinNumber(int uid);

	@Select("select game_number from userProfile where uid = #{uid}")
    int getUserGameNumber(int uid);

}
