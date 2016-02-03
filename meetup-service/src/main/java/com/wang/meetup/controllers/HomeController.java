package com.wang.meetup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wang.meetup.dao.mybatis.mysql.CategoryDao;

@RestController
public class HomeController {
	@Autowired CategoryDao categoryDao;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "home";
	}
}
