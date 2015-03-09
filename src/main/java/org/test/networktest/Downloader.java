package org.test.networktest;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author yang
 */
public class Downloader {

	private File profile;
	private List<Map.Entry<Object, Object>> resources = new ArrayList<>();
	
	public Downloader(File profile) throws IOException{
		this.profile = profile;
		
		InputStream in = new FileInputStream(profile);
		Properties properties = new Properties();
		properties.load(in);
		in.close();
		
		for (Map.Entry<Object, Object> entry : properties.entrySet()){
			resources.add(entry);
		}
	}
	
	public void run(){
		System.out.println("Starting profile " + profile.getName().toUpperCase() + " ...");
		
		List<Double> result = new ArrayList<>();
		byte[] buffer = new byte[8 * 1024];
		
		for(Map.Entry<Object, Object> entry : resources) {
			System.out.print("\tdownloading " + entry.getKey() + " ... ");
			
			Date start = new Date();
			
			try{
				URL url = new URL((String)entry.getValue());
				URLConnection conn =  url.openConnection();
				conn.setConnectTimeout(30 * 1000);
				conn.setReadTimeout(30 * 1000);
				conn.connect();
				
				InputStream in = conn.getInputStream();
				while(true){
					int read = in.read(buffer, 0, buffer.length);
					if (read == -1) {
						break;
					}
				}
				in.close();
				
				Date end = new Date();
				long span = end.getTime() - start.getTime();
				double s = ((int)(span / 100)) / 10D;
				result.add(s);
				
				System.out.println("done in:" + s + "s.");
			}catch(IOException e){
				result.add(null);
				System.out.println("error:" + e.getMessage());
			}
		}
		
		System.out.println("\tProfile end.");
		
		//printResult(result);
		
		try{
			save(result);
		}catch(IOException e){
			System.out.println("Fail to save result: " + e.getMessage());
		}
		
		System.out.println("\n");
	}

	private void printResult(List<Double> result) {
		for(Map.Entry<Object, Object> entry : resources) {
			System.out.print("\t" + entry.getKey());
		}
		
		System.out.println();
		
		for(Double r : result){
			if (r == null){
				System.out.print("\t");
			}else{
				System.out.print("\t" + r + "s");
			}
		}
	}
	
	private void save(List<Double> result) throws IOException{
		String name = profile.getName();
		int dotPos = name.indexOf(".");
		String resultFilename = name.substring(0, dotPos) + ".txt";
		File file = new File(resultFilename);
		boolean exists = file.exists();
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
		
		if (!exists) {
			// print header
			writer.write("Time");
			for(Map.Entry<Object, Object> entry : resources) {
				writer.write("\t" + entry.getKey());
			}
			writer.newLine();
		}
		
		writer.write(new Date().toString());
		for(int idx=0; idx<result.size(); idx++) {
			if (result.get(idx) == null){
				writer.write("\t0");
			}else{
				writer.write("\t" + result.get(idx).toString());
			}
		}
		writer.newLine();
		writer.close();
	}
}
