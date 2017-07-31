package com.zbwfisher.datasource.example.testservice;

import com.zbwfisher.async.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);


//	@Async
	public User addUser(User user){

		try {
			Thread.sleep(1000);
//			List list = null;
//
//			list.add(111);

			logger.info("正在添加用户{}",user.getName());

		} catch (Exception e) {

			logger.info("添加用户 失败{}",user.getName());
			e.printStackTrace();
		}

		return user;
	}
}
