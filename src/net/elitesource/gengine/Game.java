package net.elitesource.gengine;

import net.elitesource.gengine.graphics.GraphicalWindow;

public class Game
{

	protected GraphicalWindow window;
	protected Controller controller;

	public Game(GraphicalWindow window, Controller controller)
	{
		this.window = window;
		this.controller = controller;
	}

	public Game(GraphicalWindow window)
	{
		this.window = window;
		this.controller = new Controller(this);
	}

	public void launch()
	{
		this.controller.use();
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

	@SuppressWarnings("deprecation")
	public void terminate() throws InterruptedException
	{
		this.controller.listenInput = false;
		this.window.stop();
		this.controller.join();
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
