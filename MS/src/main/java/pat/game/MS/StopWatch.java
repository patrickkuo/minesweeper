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
	public long getElapsedTimeSecs() {
		long elapsed;
		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000);
		} else {
			elapsed = ((stopTime - startTime) / 1000);
		}
		return elapsed;
	}

	public void run() {
		// TODO Auto-generated method stub
		start();
		while(true){
			synchronized (timeField) {
				timeField.setText(Long.toString(getElapsedTimeSecs()));
				try {
					timeField.wait(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}