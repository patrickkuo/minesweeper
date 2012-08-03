package pat.game.MS;

import javax.swing.JTextField;

public class StopWatch implements Runnable{

	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;
	private JTextField timeField;
	
	public StopWatch(JTextField timeField) {
		super();
		this.timeField = timeField;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}

	// elaspsed time in milliseconds
	public long getElapsedTime() {
		long elapsed;
		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
	}

	// elaspsed time in seconds
	public String getElapsedTimeSecs() {
		long elapsed;
		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime)/1000);
		} else {
			elapsed = ((stopTime - startTime)/1000);
		}
		String min= "0";
		String second = (elapsed%60 < 10)?"0"+Integer.toString((int) (elapsed%60)):Integer.toString((int) (elapsed%60));
		if (elapsed>59){
			min = Integer.toString((int)((elapsed - elapsed%60)/60));
		}
		
		return min+":"+second;
	}

	public void run() {
		// TODO Auto-generated method stub
		start();
		while(true){
			synchronized (timeField) {
				timeField.setText(getElapsedTimeSecs());
				try {
					timeField.wait(500);
				} catch (InterruptedException e) {
					return;
				}
				
			}
		}
	}
}