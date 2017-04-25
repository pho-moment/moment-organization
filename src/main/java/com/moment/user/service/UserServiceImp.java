package com.moment.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.util.MD5Util;
import com.moment.user.dao.UserVOMapper;
import com.moment.user.domain.UserVO;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserVOMapper mapper;
	
	@Override
	public int addUser(UserVO user) throws Throwable {
		String salt=UUID.randomUUID().toString();
		user.setSalt(salt);
		user.setPassword(MD5Util.md5(user.getPassword()+salt));	//密码加盐
		return mapper.insertSelective(user);
	}

	@Override
	public int deleteUser(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);

	}

	@Override
	public int updateUser(UserVO user) throws Throwable {
		return mapper.updateByPrimaryKeySelective(user);

	}

	

	@Override
	public UserVO getUserById(Integer id) throws Throwable {
		return mapper.selectByPrimaryKey(id);
		
	}

	
}
