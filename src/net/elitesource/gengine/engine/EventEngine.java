package net.elitesource.gengine.engine;

import java.util.ArrayList;

import net.elitesource.gengine.event.AbstractEvent;
import net.elitesource.gengine.event.OGLEvent;

@Deprecated
public class EventEngine implements Runnable
{

	private ArrayList<AbstractEvent> events = new ArrayList<AbstractEvent>();
	protected boolean isRunning;

	public EventEngine()
	{

	}

	public void runEvents()
	{

		for (int i = 0; i < events.size(); i++)
		{
			events.get(i).execute();
		}

		for (int i = 0; i < events.size(); i++)
		{
			if (!events.get(i).isRepeating())
			{
				events.remove(i);
			}
		}

	}

	public void registerEvent(AbstractEvent event)
	{
		if (event instanceof OGLEvent)
		{
			System.err.println("Unable to add event; Was OGLEvent.");
			return;
		} else
		{
			this.events.add(event);
		}
	}

	public ArrayList<AbstractEvent> getEvents()
	{
		return events;
	}

	public void setEvents(ArrayList<AbstractEvent> events)
	{
		this.events = events;
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	@Override
	public void run()
	{
		this.isRunning = true;
		while (isRunning())
		{
			runEvents();
		}
	}

}
