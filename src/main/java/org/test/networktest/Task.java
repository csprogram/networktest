package org.test.networktest;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

/**
 *
 * @author yang
 */
public class Task extends TimerTask {

	private File[] profiles;
	private boolean runing;
	
	public Task(File[] profiles) {
		this.profiles = profiles;
	}
	
	@Override
	public void run() {
		if (runing){
			//System.out.println("The previous test still running...");
			return;
		}
		
		runing = true;
		
		for(File profile : profiles) {
			try{
				Downloader downloaderSmall = new Downloader(profile);
				downloaderSmall.run();
			}catch(IOException e){
				// ignore
			}
		}
		
		System.out.println("All profiles complete, please wait 3 minutes to start the next turn...");
		System.out.println();
		
		runing = false;
	}

//	public boolean isRuning() {
//		return runing;
//	}
	
}
