package com.example.demo;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageConfig {
	@Bean
	public MessageSource messagesource() {
		ResourceBundleMessageSource  messagesource=new ResourceBundleMessageSource();
		messagesource.setBasename("message");
		messagesource.setDefaultEncoding("UTF-8");
		return messagesource;
		
	}
}
