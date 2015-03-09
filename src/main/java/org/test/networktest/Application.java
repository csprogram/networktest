package org.test.networktest;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 *
 * @author yang
 */
public class Application {
	
	public static void main(String[] args) throws IOException, InterruptedException{
	
		System.out.println("Network speed test");
		System.out.println("==================");
		System.out.print("Loading profile files...");
		
		File currentDir = new File(".");
		File[] profiles = currentDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith(".properties"));
			}
		});
		
		if (profiles.length == 0) {
			System.out.println("can not find any profile files.");
			System.out.println("Please create profile file with format:");
			System.out.println("SERVER_NAME=TEST_RESOURCE_URL");
			System.out.println("such as:");
			System.out.println("MyVPS=http://www.mynewvpsdomain.com/big-file.dat");
			System.out.println("then save it with extension name '.properties'.");
			return;
		}
		
		System.out.println(String.format("found %s profile(s).", profiles.length));
		System.out.println();
		
		Task task = new Task(profiles);
		
		while(true){
			task.run(); 
		
			System.out.println("All profiles complete, please wait 3 minutes to start the next turn...");
			System.out.println("Press Ctrl+C to exit.");
			System.out.println();
			
			Thread.sleep(3L * 60 * 1000);
		}
		
	}
}
