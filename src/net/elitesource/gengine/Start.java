package net.elitesource.gengine;

import java.awt.Color;

import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.graphics.GraphicsType;
import net.elitesource.gengine.models.ModelLoader;

public class Start
{

	public static Game g;

	public static void main(String[] args)
	{
		g = new Game(new GraphicalWindow("New Window", 400, 400, GraphicsType.Dim2), new Controller(g, 30));
		ModelLoader.loadModels();
		g.launch();
	}

}
