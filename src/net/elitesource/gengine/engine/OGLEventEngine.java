package net.elitesource.gengine.engine;

import java.util.ArrayList;

import net.elitesource.gengine.event.OGLEvent;

public class OGLEventEngine
{

	protected ArrayList<OGLEvent> events = new ArrayList<OGLEvent>();

	public OGLEventEngine()
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

	public void registerEvent(OGLEvent event)
	{
		this.events.add(event);
	}

	public ArrayList<OGLEvent> getEvents()
	{
		return events;
	}

	public void setEvents(ArrayList<OGLEvent> events)
	{
		this.events = events;
	}

}
