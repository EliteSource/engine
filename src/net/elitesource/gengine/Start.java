package net.elitesource.gengine;

import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.graphics.GraphicsType;
import net.elitesource.gengine.gui.Button;
import net.elitesource.gengine.models.Model;

public class Start
{

	public static Game g;

	public static void main(String[] args)
	{
		g = new Game(new GraphicalWindow("New Window", 400, 400, GraphicsType.Dim2), new Controller(g, 30));
		Button b = new Button(100, 100, 300, 200, "Test Button");
		b.getModels().add(new Model("res/images/button_idle"));
		b.getModels().add(new Model("res/images/button_hover"));
		b.getModels().add(new Model("res/images/button_click"));
		g.window.addRenderable(b);
		g.launch();
	}

}
