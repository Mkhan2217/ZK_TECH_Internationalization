package com.example.demo;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyLocaleResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String lang=request.getHeader("Accept_Language");
		if (lang==null|| lang.isEmpty()) {
			return Locale.forLanguageTag("en");
		}
		Locale locale= Locale.forLanguageTag(lang);
		if (LangConfig.Locales.contains(locale)) {
			return locale;
		}
		return Locale.forLanguageTag("en");
		
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// TODO Auto-generated method stub
		
	}

}
