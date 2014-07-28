package net.elitesource.gengine.graphics;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import net.elitesource.gengine.entity.Renderable;

import org.lwjgl.opengl.GL11;

/**
 * This class is an object of the render engine, created by a GraphicalWindow to
 * display the graphics stuff. It holds a render loop, and an arrayList of Super
 * type entities to draw.
 * 
 * @author Mike
 * 
 */
public class RenderEngine
{
	protected ArrayList<Renderable> renderables = new ArrayList<Renderable>();

	public RenderEngine()
	{

	}

	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);

		GL11.glTranslatef(0, 0, 0);

		for (int i = 0; i < renderables.size(); i++)
		{
			renderables.get(i).render();
		}

	}

	public void clearRenderables()
	{
		this.renderables = new ArrayList<Renderable>();
	}

	public ArrayList<Renderable> getRenderables()
	{
		return this.renderables;
	}


}
