package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@ComponentScan("com.example.demo")
@PropertySource("application.properties")
public class DemoApplication {

	public static void main(String[] args) {

		/*ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getBean(ConsoleContactsApp.class).start();*/
		//+ 1 other way to create context
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();*/
		System.out.println("Before App Context Created");
		try (AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoApplication.class)) {

			System.out.println(" App Context Created");
			ConsoleContactsApp consoleContactsApp = context
					//.getBean("main", ConsoleContactsApp.class);
					.getBean(ConsoleContactsApp.class);// экземпляр бина уже сконфигурированнный
			consoleContactsApp.start();

		}
	}
}


