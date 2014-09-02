package net.elitesource.gengine;

import net.elitesource.gengine.control.Controller;
import net.elitesource.gengine.engine.CollisionEngine;
import net.elitesource.gengine.graphics.GraphicalWindow;

public class Game
{

	public static Game game;
	protected GraphicalWindow window;
	protected Controller controller;
	protected CollisionEngine collisionEngine;

	public Game(GraphicalWindow window, Controller controller)
	{
		this.window = window;
		this.controller = controller;
		this.collisionEngine = new CollisionEngine(this);
		Game.game = this;
	}

	public Game(GraphicalWindow window)
	{
		this.window = window;
		this.controller = new Controller(this);
		this.collisionEngine = new CollisionEngine(this);
		Game.game = this;
	}

	public void launch()
	{
		this.controller.use();
		this.collisionEngine.start();
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

	public CollisionEngine getCollisionEngine()
	{
		return collisionEngine;
	}

	public void setCollisionEngine(CollisionEngine collisionEngine)
	{
		this.collisionEngine = collisionEngine;
	}

	public void setWindow(GraphicalWindow window)
	{
		this.window = window;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}

}
