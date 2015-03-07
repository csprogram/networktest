package org.test.networktest;

import java.io.IOException;
import java.util.Timer;

/**
 *
 * @author yang
 */
public class Application {
	
	public static void main(String[] args) throws IOException{
	
		System.out.println("Network speed test");
		System.out.println("==================");
		
		Timer timer = new Timer();
		Task task = new Task();
		timer.scheduleAtFixedRate(task, 3 * 60 * 1000, 5 * 1000);
		
		task.run(); // run the first time

	}
}
