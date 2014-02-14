package net.elitesource.gengine;

import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.models.ModelLoader;

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
		terminate();
	}

	@SuppressWarnings("deprecation")
	public void terminate()
	{
		this.controller.listenInput = false;
		this.window.stop();
		this.controller.stop();
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
