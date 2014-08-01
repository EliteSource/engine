package net.elitesource.gengine.engine;

public abstract class Engine implements IEngine, Runnable

{

	private boolean isRunning = false;

	@Override
	public void stop()
	{
		this.setRunning(false);
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

}
