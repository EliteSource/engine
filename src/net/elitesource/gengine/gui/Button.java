package net.elitesource.gengine.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.gui.events.ButtonClickEvent;
import net.elitesource.gengine.gui.events.ButtonHoverEvent;
import net.elitesource.gengine.gui.events.ButtonIdleEvent;
import net.elitesource.gengine.models.Model;
import net.elitesource.gengine.utils.Utils;

public class Button implements IButton
{

	private String text;
	private ArrayList<ActionListener> actionListeners;
	private ArrayList<Model> models;
	private boolean isClicked, isIdle, isHovered;
	private float x, y, width, height, r, g, b, a;
	private Rectangle buttonBounds = new Rectangle();
	private GraphicalWindow window;
	private TrueTypeFont ttf;
	private Insets textPadding;

	public Button(float x, float y, float width, float height, String text)
	{
		this.text = text;
		this.models = new ArrayList<Model>();
		this.actionListeners = new ArrayList<ActionListener>();
		this.isClicked = false;
		this.isHovered = false;
		this.isIdle = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonBounds.setBounds((int) x, (int) y, (int) width, (int) height);
		this.r = 1.0f;
		this.g = 1.0f;
		this.b = 1.0f;
		this.a = 1.0f;
		this.textPadding = new Insets(20, 20, 20, 20);
		try
		{
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/default.ttf");
			Font defaultFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			Rectangle textBounds = new Rectangle();
			textBounds.setBounds((int) x, (int) y, (int) width, (int) height);
			defaultFont.deriveFont(Utils.getScaledFontSize(this.text, textBounds, new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB).getGraphics(), defaultFont));
			this.ttf = new TrueTypeFont(defaultFont, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void render()
	{

		int mx = Mouse.getX();
		int my = window.getHeight() - Mouse.getY();
		buttonBounds.setBounds((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
		isHovered = buttonBounds.contains(mx, my) && !(Mouse.isButtonDown(0));
		isClicked = buttonBounds.contains(mx, my) && Mouse.isButtonDown(0);
		isIdle = !buttonBounds.contains(mx, my);

		if (isIdle)
		{
			this.onIdle();
		} else if (isHovered)
		{
			this.onHover();
		} else if (isClicked)
		{
			this.onClick();
		}

		glPushMatrix();
		glBegin(GL_QUADS);
		glColor4f(r, g, b, a);
		glTexCoord2f(0, 0);
		glVertex2d(x, y);
		glTexCoord2f(1, 0);
		glVertex2d(x + width, y);
		glTexCoord2f(1, 1);
		glVertex2d(x + width, y + height);
		glTexCoord2f(0, 1);
		glVertex2d(x, y + height);
		glEnd();
		glPopMatrix();

		this.ttf.drawString(x + this.textPadding.left, y + this.textPadding.top, text);
	}

	@Override
	public void onClick()
	{
		this.isClicked = true;
		this.isHovered = false;
		this.isIdle = false;

		for (int i = 0; i < this.actionListeners.size(); i++)
		{
			this.actionListeners.get(i).actionEvent(new ButtonClickEvent(this));
		}

		this.models.get(2).bind();
	}

	@Override
	public void onIdle()
	{
		this.isClicked = false;
		this.isHovered = false;
		this.isIdle = true;

		for (int i = 0; i < this.actionListeners.size(); i++)
		{
			this.actionListeners.get(i).actionEvent(new ButtonIdleEvent(this));
		}

		this.models.get(0).bind();
	}

	@Override
	public void onHover()
	{
		this.isClicked = true;
		this.isHovered = false;
		this.isIdle = false;

		for (int i = 0; i < this.actionListeners.size(); i++)
		{
			this.actionListeners.get(i).actionEvent(new ButtonHoverEvent(this));
		}

		this.models.get(1).bind();
	}

	@Override
	public ArrayList<Model> getModels()
	{
		return this.models;
	}

	public void setTextFont(TrueTypeFont ttf)
	{
		this.ttf = ttf;
	}

	public TrueTypeFont getTextFont()
	{
		return this.ttf;
	}

	public void setTextPadding(Insets i)
	{
		this.textPadding = i;
		try
		{
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/default.ttf");
			Font defaultFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			Rectangle textBounds = new Rectangle();
			textBounds.setBounds((int) x, (int) y, (int) width, (int) height);
			defaultFont.deriveFont(Utils.getScaledFontSize(this.text, textBounds, new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB).getGraphics(), defaultFont));
			this.ttf = new TrueTypeFont(defaultFont, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setX(float f)
	{
		this.x = f;
	}

	@Override
	public void setY(float f)
	{
		this.y = f;
	}

	@Override
	public float getX()
	{
		return this.x;
	}

	@Override
	public float getY()
	{
		return this.y;
	}

	@Override
	public void setWidth(float f)
	{
		this.width = f;
	}

	@Override
	public void setHeight(float f)
	{
		this.height = f;
	}

	@Override
	public float getWidth()
	{
		return this.width;
	}

	@Override
	public float getHeight()
	{
		return this.height;
	}

	@Override
	public void setColor(float r, float g, float b, float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public float[] getColor()
	{
		float[] result = new float[4];
		result[0] = this.r;
		result[1] = this.g;
		result[2] = this.b;
		result[3] = this.a;
		return result;
	}

	@Override
	public boolean isClicked()
	{
		return this.isClicked;
	}

	@Override
	public boolean isIdle()
	{
		return this.isIdle;
	}

	@Override
	public boolean isHovered()
	{
		return this.isHovered;
	}

	@Override
	public ArrayList<ActionListener> getActionListeners()
	{
		return this.actionListeners;
	}

	@Override
	public String getText()
	{
		return this.text;
	}

	@Override
	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public GraphicalWindow getWindow()
	{
		return this.window;
	}

	@Override
	public void setWindow(GraphicalWindow window)
	{
		this.window = window;
	}

}
