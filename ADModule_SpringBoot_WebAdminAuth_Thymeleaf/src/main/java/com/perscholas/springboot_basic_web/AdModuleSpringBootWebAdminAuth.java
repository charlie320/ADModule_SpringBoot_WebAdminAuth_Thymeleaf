package com.perscholas.springboot_basic_web;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.perscholas.springboot_basic_web.filters.AuthenticationFilter;
import com.perscholas.springboot_basic_web.filters.AuthorizationFilter;

@SpringBootApplication
public class AdModuleSpringBootWebAdminAuth {

	public static void main(String[] args) {
		SpringApplication.run(AdModuleSpringBootWebAdminAuth.class, args);
	}

	@Bean(name="loginFilter")
	public FilterRegistrationBean<AuthenticationFilter> authFilter() {
		FilterRegistrationBean<AuthenticationFilter> registrationBean = 
				new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthenticationFilter());
		registrationBean.setUrlPatterns(Arrays.asList(new String[] {"/homePage", "/aboutPage", 
				"/expenseForm", "/createExpense", "/allExpenses", "/expensesByDateRange", 
				"/retrieveExpensesByDateReport", "/expensesByDateRange", "/customDateRangeReport"}));
		return registrationBean;
	}
	
	@Bean(name="adminFilter")
	public FilterRegistrationBean<AuthorizationFilter> authFilterAdmin() {
		FilterRegistrationBean<AuthorizationFilter> registrationBean = 
				new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new AuthorizationFilter());
		
		registrationBean.setUrlPatterns(Arrays.asList(new String[] {"/adminPage"}));

		return registrationBean;
	}
}
