package net.elitesource.gengine;

import net.elitesource.gengine.control.Controller;
import net.elitesource.gengine.event.EventEngine;
import net.elitesource.gengine.graphics.GraphicalWindow;

public class Game
{

	protected GraphicalWindow window;
	protected Controller controller;
	protected EventEngine eventEngine;
	protected Thread eventThread;

	public Game(GraphicalWindow window, Controller controller, EventEngine eventEngine)
	{
		this.window = window;
		this.controller = controller;
		this.eventEngine = eventEngine;
		this.eventThread = new Thread(this.eventEngine);
		this.eventThread.setName("EventThread");
	}

	public Game(GraphicalWindow window)
	{
		this.window = window;
		this.controller = new Controller(this);
		this.eventEngine = new EventEngine();
		this.eventThread = new Thread(this.eventEngine);
		this.eventThread.setName("EventThread");
	}

	public void launch()
	{
		this.controller.use();
		this.eventThread.start();
		this.window.startGameLoop(60);
		try
		{
			terminate();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void terminate() throws InterruptedException
	{
		this.controller.setListening(false);
		this.window.stop();
		this.controller.join();
		this.eventEngine.setRunning(false);
		System.exit(0);
	}

	public GraphicalWindow getWindow()
	{
		return window;
	}

	public Controller getController()
	{
		return controller;
	}

}
