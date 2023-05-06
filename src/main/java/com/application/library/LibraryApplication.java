package com.application.library;

import com.application.library.observer.Tracker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		Tracker tracker = Tracker.getInstance();
		ObserverHelper init = ObserverHelper.getInstance();
		init.registerObserver(tracker);
		SpringApplication.run(LibraryApplication.class, args);
	}

}
