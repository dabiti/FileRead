package BBCFileRead;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import BBCFileRead.FileRead.ReadFileThread;

public class TestTimer {
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	 
	public static void main(String[] args){
        long delay = Helper.calcDelay(19, 20, 0);  
        long period = Helper.ONE_DAY;  
        final Runnable beeper = new Runnable() {
                public void run() {
                	try{      	
                		ReadFileThread testThread = new ReadFileThread("D:\\testccbdata\\20130707.txt");
                		testThread.start();
                	}catch(RejectedExecutionException e){
                		e.printStackTrace();
                	}
                }
                
         };
         scheduler.scheduleAtFixedRate(beeper, delay, period, TimeUnit.MILLISECONDS);
	}
}





