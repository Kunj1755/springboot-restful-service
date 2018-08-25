package com.personal.kunj.springbootrestfulservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personal.kunj.springbootrestfulservice.helloworld.HelloWorldBean;

@RestController
public class HelloWorldController {

	// @GetMapping(path = "/hello-world")
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	@GetMapping(path = "/hello-world/path-var/{name}")
	public HelloWorldBean helloWorldWithPath(@PathVariable("name") String myName) {
		return new HelloWorldBean(String.format("Hello World, %s", myName));
	}
}
