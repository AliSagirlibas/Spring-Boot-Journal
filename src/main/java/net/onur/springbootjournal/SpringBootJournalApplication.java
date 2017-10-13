package net.onur.springbootjournal;

import java.io.PrintStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


import net.onur.springbootjournal.model.Journal;
import net.onur.springbootjournal.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication {

	
	@Bean
	InitializingBean saveData(JournalRepository repo)
	{
		return () -> 
		{
			repo.save(new Journal("Get to know Spring Boot","Today I will learn Spring Boot","01/01/2016"));
			repo.save(new Journal("Simple Spring Boot Project","I will do my first Spring Boot Project","01/02/2016"));
			repo.save(new Journal("Spring Boot Reading","Read more about Spring Boot","02/01/2016"));
			repo.save(new Journal("Spring Boot in the Cloud","Spring Boot using Cloud Foundry","03/01/2016"));
		};
	}
	
	public static void main(String[] args) 
	{
		
		SpringApplication.run(SpringBootJournalApplication.class, args);
		
		
		//new SpringApplicationBuilder().bannerMode(Mode.OFF).sources(SpringBootJournalApplication.class).run(args);
		
		/*
		SpringApplication mySpringApplication=new SpringApplication(SpringBootJournalApplication.class);
		mySpringApplication.setBannerMode(Mode.CONSOLE);
		mySpringApplication.setBanner(new Banner() {
			
			@Override
			public void printBanner(Environment arg0, Class<?> arg1, PrintStream out) {
				out.print(
"						 ___  _______  __   __  ______    __    _  _______  ___      " +"\n"+
"					    |   ||       ||  | |  ||    _ |  |  |  | ||   _   ||   |    " +"\n"+
"					    |   ||   _   ||  | |  ||   | ||  |   |_| ||  |_|  ||   |    "+"\n"+
"					    |   ||  | |  ||  |_|  ||   |_||_ |       ||       ||   |    "+"\n"+
"					 ___|   ||  |_|  ||       ||    __  ||  _    ||       ||   |___ "+"\n"+
"					|       ||       ||       ||   |  | || | |   ||   _   ||       |"+"\n"+
"					|_______||_______||_______||___|  |_||_|  |__||__| |__||_______|"+"\n"+
"\n\n");
				
			}
		});
		mySpringApplication.run(args);
		*/
	}
	
	
}
