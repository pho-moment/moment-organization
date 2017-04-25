package com.moment.user.service;

import com.moment.user.domain.UserVO;

public interface UserService {
	public int addUser(UserVO user) throws Throwable;
	
	public int deleteUser(Integer id) throws Throwable;
	
	public UserVO getUserById(Integer id) throws Throwable;
	
	public int updateUser(UserVO user) throws Throwable;
	
}
