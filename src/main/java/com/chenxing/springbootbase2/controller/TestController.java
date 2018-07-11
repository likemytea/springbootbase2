package com.chenxing.springbootbase2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chenxing.springbootbase2.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "userController相关api")
@RequestMapping("/hello")
public class TestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TestService testService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@ApiOperation(value = "获取用户信息", notes = "获取usermessage")
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String index() {
		testRedis();
		log.info("你好，晨星");
		// testService.testSearch();
		return "hello chenxing，你好么";
	}

	private void testRedis() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		stringRedisTemplate.opsForValue().set("aaa", "111哈哈");
		log.info("########:" + stringRedisTemplate.opsForValue().get("aaa"));
	}
}
