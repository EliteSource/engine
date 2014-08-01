package net.elitesource.gengine;

import net.elitesource.gengine.control.Controller;
import net.elitesource.gengine.engine.EventEngine;
import net.elitesource.gengine.graphics.GraphicalWindow;

public class Game
{

	public static Game game;
	protected GraphicalWindow window;
	protected Controller controller;

	public Game(GraphicalWindow window, Controller controller)
	{
		this.window = window;
		this.controller = controller;

		Game.game = this;
	}

	public Game(GraphicalWindow window)
	{
		this.window = window;
		this.controller = new Controller(this);
		Game.game = this;
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

	public void terminate() throws InterruptedException
	{
		this.controller.setListening(false);
		this.window.stop();
		this.controller.join();
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
