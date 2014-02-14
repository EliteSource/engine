package net.elitesource.gengine.event;

public abstract class AbstractEvent implements IEvent
{

	protected boolean repeat;

	public AbstractEvent(boolean repeating)
	{
		this.repeat = repeating;
	}

	public AbstractEvent()
	{
		this.repeat = false;
	}

	public boolean isRepeating()
	{
		return this.repeat;
	}

}
