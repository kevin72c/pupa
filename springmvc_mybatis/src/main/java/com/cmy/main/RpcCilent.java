package com.cmy.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcCilent {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		        "spring/applicationContext*.xml");
		context.start();
		try { 
			System.out.println("begine invoke");
//			UserService us = context.getBean("userService", UserService.class);
//			SportUser su = us.selectUserByTel("18658835702");
//			System.out.println(JSONObject.toJSONString(userService.getUserInfo("1234561")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.close();

	}
}
