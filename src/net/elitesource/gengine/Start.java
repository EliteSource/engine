package net.elitesource.gengine;

import net.elitesource.gengine.control.Controller;
import net.elitesource.gengine.debug.Player;
import net.elitesource.gengine.debug.PlayerController;
import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.graphics.GraphicsType;

public class Start
{

	public static Game g;
	public static Player player;
	public static PlayerController playerCtrl;

	public static void main(String[] args)
	{
		g = new Game(new GraphicalWindow("New Window", 400, 400, GraphicsType.Dim2), new Controller(g, 30));
		player = new Player(0, 0, 25, 25, 50, 1.0f, 1, 1);
		g.getWindow().getRenderEngine().getRenderables().add(player);
		g.getWindow().getRenderEngine().getRenderables().add(new Player(50, 50, 25, 25, 50, 1, 1, 1));
		playerCtrl = new PlayerController(player);
		g.getController().addControlHandler(playerCtrl);
		g.launch();
	}

}
