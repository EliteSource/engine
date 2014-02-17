package net.elitesource.gengine.graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;
import java.util.Hashtable;

import net.elitesource.gengine.entity.Renderable;
import net.elitesource.gengine.event.AbstractEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * 
 * This class is an object of the OpenGL Display thingy. It has a render engine
 * object, which tells the window (this) what to draw. A Graphics Type is
 * basically 2D or 3D. On anothe note, maybe have this class do like,
 * "doEvent(Event e)" for doing OpenGL commands, since this is the thread opengl
 * uses.
 * 
 * 
 * @author Mike
 * 
 */
public class GraphicalWindow
{

	public static int WIDTH, HEIGHT;

	protected int width, height;
	protected GraphicsType gType;
	protected RenderEngine rEngine;
	protected String title;
	protected ArrayList<AbstractEvent> events = new ArrayList<AbstractEvent>();

	public GraphicalWindow(String windowTitle, int width, int height, GraphicsType gType)
	{
		this.width = width;
		WIDTH = width;
		HEIGHT = height;
		this.height = height;
		this.gType = gType;
		this.rEngine = new RenderEngine();
		this.title = windowTitle;

		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e)
		{
			System.err.println("Display could not be created: " + e.getStackTrace());
		}

		initOpenGl(gType);
	}

	public GraphicalWindow(String windowTitle, int width, int height, GraphicsType gType, RenderEngine rEngine)
	{
		this.width = width;
		this.height = height;
		this.gType = gType;
		this.rEngine = rEngine;
		this.title = windowTitle;

		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e)
		{
			System.err.println("Display could not be created: " + e.getStackTrace());
		}

		initOpenGl(gType);
	}

	private void initOpenGl(GraphicsType type)
	{
		if (type.equals(GraphicsType.Dim2))
		{
			glMatrixMode(GL_PROJECTION);
			glMatrixMode(GL_MODELVIEW);
			glOrtho(0, width, height, 0, -1, 1);
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}
	}

	// TODO: Fix events, seems to recreate and redo when events are tryed. Or
	// could be the controller bugging out.

	public void startGameLoop(int fps)
	{
		while (!Display.isCloseRequested())
		{
			Display.setTitle(title);
			for (int i = 0; i < events.size(); i++)
			{
				events.get(i).execute();
			}

			for (int i = 0; i < events.size(); i++)
			{
				if (!events.get(i).isRepeating())
				{
					events.remove(events.get(i));
				}
			}
			getRenderEngine().render();
			Display.update();
			Display.sync(fps);
		}
	}

	public RenderEngine getRenderEngine()
	{
		return this.rEngine;
	}

	public void setRenderEngine(RenderEngine rEngine)
	{
		this.rEngine = rEngine;
	}

	public void stop()
	{
		this.rEngine.clearRenderables();
		Display.destroy();
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void doEvent(AbstractEvent e)
	{
		this.events.add(e);
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void addRenderable(Renderable r)
	{
		this.rEngine.getRenderables().add(r);
		r.setWindow(this);
	}

}
