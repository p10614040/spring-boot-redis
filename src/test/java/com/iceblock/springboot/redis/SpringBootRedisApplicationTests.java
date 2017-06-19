package com.iceblock.springboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * Redis 客户端测试
	 */
	@Test
	public void testRedis() {
		// hash 和 List 两种常见 key 类型
		String testHashKey = "test_hash_key";
		String testListKey = "test_list_key";
		BoundHashOperations<String, String, String> hashOperations = redisTemplate.boundHashOps(testHashKey);
		BoundListOperations<String, String> listOperations = redisTemplate.boundListOps(testListKey);

		System.out.println("*************** 增加测试 ***************");
		hashOperations.put("a", "test1");
		hashOperations.put("b", "test2");

		listOperations.leftPush("test1");
		listOperations.leftPush("test2");


		System.out.println("*************** 查询测试 ***************");
		System.out.println(hashOperations.get("a"));
		System.out.println(hashOperations.get("b"));

		System.out.println(listOperations.range(0, -1));

		System.out.println("*************** 删除测试 ***************");
		System.out.println(testHashKey + " 存在检查：" + redisTemplate.hasKey(testHashKey));
		System.out.println(testListKey + " 存在检查：" + redisTemplate.hasKey(testListKey));
		redisTemplate.delete(testHashKey);
		redisTemplate.delete(testListKey);
		System.out.println(testHashKey + " 存在检查：" + redisTemplate.hasKey(testHashKey));
		System.out.println(testListKey + " 存在检查：" + redisTemplate.hasKey(testListKey));
	}

}
