package com.jntua.ui;

import java.io.File;
import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob extends CameraSnapshotJavaFX implements Job {

	
    @Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {
    	System.out.println("Job");
  
   // launch(new String[]{});
    	try {
    		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "my.bat");
    		File dir = new File("e:/my");
    		pb.directory(dir);
    		Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	System.out.println("Job");
      

    }

}