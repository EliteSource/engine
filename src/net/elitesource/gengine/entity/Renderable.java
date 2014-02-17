package net.elitesource.gengine.entity;

import net.elitesource.gengine.graphics.GraphicalWindow;

public interface Renderable
{
	public void render();

	public GraphicalWindow getWindow();

	public void setWindow(GraphicalWindow window);

}
