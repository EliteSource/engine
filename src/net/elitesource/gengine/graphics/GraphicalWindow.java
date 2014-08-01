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
import net.elitesource.gengine.engine.OGLEventEngine;
import net.elitesource.gengine.engine.RenderEngine;
import net.elitesource.gengine.entity.Renderable;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * 
 * This class is an object of the OpenGL Display thingy. It has a render engine
 * object, which tells the window (this) what to draw. A Graphics Type is
 * basically 2D or 3D. On another note, maybe have this class do like,
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
	protected OGLEventEngine oglEventEngine;
	protected String title;

	public GraphicalWindow(String windowTitle, int width, int height, GraphicsType gType)
	{
		this.width = width;
		WIDTH = width;
		HEIGHT = height;
		this.height = height;
		this.gType = gType;
		this.rEngine = new RenderEngine();
		this.title = windowTitle;
		this.oglEventEngine = new OGLEventEngine();

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

	public GraphicalWindow(String windowTitle, int width, int height, GraphicsType gType, RenderEngine rEngine, OGLEventEngine oglEventEngine)
	{
		this.width = width;
		this.height = height;
		this.gType = gType;
		this.rEngine = rEngine;
		this.title = windowTitle;
		this.oglEventEngine = oglEventEngine;

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

	/**
	 * Set the display mode to be used 
	 * 
	 * @param width The width of the display required
	 * @param height The height of the display required
	 * @param fullscreen True if we want fullscreen mode
	 */
	public void setDisplayMode(int width, int height, boolean fullscreen)
	{

		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullscreen))
		{
			return;
		}

		try
		{
			DisplayMode targetDisplayMode = null;

			if (fullscreen)
			{
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i = 0; i < modes.length; i++)
				{
					DisplayMode current = modes[i];

					if ((current.getWidth() == width) && (current.getHeight() == height))
					{
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq))
						{
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()))
							{
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						// if we've found a match for bpp and frequence against the 
						// original display mode then it's probably best to go for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()))
						{
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else
			{
				targetDisplayMode = new DisplayMode(width, height);
			}

			if (targetDisplayMode == null)
			{
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		} catch (LWJGLException e)
		{
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
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

	public void startGameLoop(int fps)
	{
		while (!Display.isCloseRequested())
		{
			Display.setTitle(title);
			getOGLEventEngine().runEvents();
			getRenderEngine().render();
			Display.update();
			Display.sync(fps);
		}
	}

	private OGLEventEngine getOGLEventEngine()
	{
		return this.oglEventEngine;
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
	}

}
