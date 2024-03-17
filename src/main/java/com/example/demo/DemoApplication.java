package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class DemoApplication {

	public static void main(String[] args) throws IOException {

		/*ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getBean(ConsoleContactsApp.class).start();*/
		//+ 1 other way to create context
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();*/
		try (AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class)) {

			ConsoleContactsApp consoleContactsApp = context
					//.getBean("main", ConsoleContactsApp.class);
					.getBean(ConsoleContactsApp.class);// экземпляр бина уже сконфигурированнный
			consoleContactsApp.start();

		}
	}
}


