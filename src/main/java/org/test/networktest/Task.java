package org.test.networktest;

import java.io.IOException;
import java.util.TimerTask;

/**
 *
 * @author yang
 */
public class Task extends TimerTask {

	private boolean runing;
	
	@Override
	public void run() {
		if (runing){
			//System.out.println("The previous test still running...");
			return;
		}
		
		runing = true;
		
		try{
			Downloader downloaderSmall = new Downloader("small.properties");
			downloaderSmall.run();
		}catch(IOException e){
			// ignore
		}
		
		try{
			Downloader downloaderBig = new Downloader("big.properties");
			downloaderBig.run();
		}catch(IOException e){
			// ignore
		}
		
		runing = false;
	}

	public boolean isRuning() {
		return runing;
	}
	
}
