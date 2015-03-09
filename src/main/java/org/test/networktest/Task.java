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
	
	public Task(File[] profiles) {
		this.profiles = profiles;
	}
	
	@Override
	public void run() {

		for(File profile : profiles) {
			try{
				Downloader downloader = new Downloader(profile);
				downloader.run();
			}catch(IOException e){
				// ignore
			}
		}
		
	}

}
