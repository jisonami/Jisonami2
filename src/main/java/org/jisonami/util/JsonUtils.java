package org.jisonami.util;

import org.jisonami.entity.User;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	/**
	 * 初始化User对象
	 * @return user
	 */
	private static User initUser(){
		User user = new User();
		user.setId("1");
		user.setName("jison");
		user.setPassword("jison");
		return user;
	}
	
	public static void main(String[] args) throws Exception {
		// fastjson用法
		fastjson();
		// jackson用法
		jackson();
		// gson用法
		gson();
	}
	
	private static void fastjson(){
//		// 将Java对象序列化为Json字符串
//		String objectToJson = JSON.toJSONString(initUser());
//		System.out.println(objectToJson);
//		// 将Json字符串反序列化为Java对象
//		User user = JSON.parseObject(objectToJson, User.class);
//		System.out.println(user);
	}
	
	private static void jackson() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		// 将Java对象序列化为Json字符串
		String objectToJson = objectMapper.writeValueAsString(initUser());
		System.out.println(objectToJson);
		// 将Json字符串反序列化为Java对象
		User user = objectMapper.readValue(objectToJson, User.class);
		System.out.println(user);
	}
	
	private static void gson(){
//		Gson gson = new GsonBuilder().create();
//		// 将Java对象序列化为Json字符串
//		String objectToJson = gson.toJson(initUser());
//		System.out.println(objectToJson);
//		// 将Json字符串反序列化为Java对象
//		User user = gson.fromJson(objectToJson, User.class);
//		System.out.println(user);
	}
}
