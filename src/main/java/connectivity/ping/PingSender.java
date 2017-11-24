package connectivity.ping;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import connectivity.inteface.PingSenderInterface;

public class PingSender implements Runnable {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private PingSenderInterface pingSenderInterface;
	private boolean isRunning = false;

	public PingSenderInterface getPingSenderInterface() {
		return pingSenderInterface;
	}

	public void setPingSenderInterface(PingSenderInterface pingSenderInterface) {
		this.pingSenderInterface = pingSenderInterface;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public PingSender(PingSenderInterface pingSenderInterface) {
		this.setPingSenderInterface(pingSenderInterface);
		this.start();
	}

	private void start() {
		this.scheduler.scheduleAtFixedRate(this, 0, 2, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		this.setRunning(true);
		if (this.getPingSenderInterface() != null)
			this.getPingSenderInterface().onPing();
	}
}
