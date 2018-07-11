package com.chenxing.springbootbase2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxing.springbootbase2.domain.UserT;
import com.chenxing.springbootbase2.mapper.UserTMapper;

@Service
public class TestServiceImpl implements TestService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserTMapper userTMapper;

	public void testSearch() {
		List<UserT> res = userTMapper.getAll();
		for (UserT ut : res) {
			log.info(ut.getUserName());
			log.info("" + ut.getUserId());
		}
	}
}
