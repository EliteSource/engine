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
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.gui.event.ButtonClickEvent;
import net.elitesource.gengine.gui.event.ButtonHoverEvent;
import net.elitesource.gengine.gui.event.ButtonIdleEvent;
import net.elitesource.gengine.models.Model;
import net.elitesource.gengine.utils.Utils;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Button implements IButton
{

	private String text;
	private ArrayList<ActionListener> actionListeners;
	private ArrayList<Model> models;
	private boolean isClicked, isIdle, isHovered;
	private float x, y, width, height, r, g, b, a;
	private Rectangle buttonBounds = new Rectangle();
	private Rectangle textBounds = new Rectangle();
	private Insets textPadding = new Insets(5, 5, 5, 5);
	private GraphicalWindow window;
	private TrueTypeFont textFont;
	private Color textColor;

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
		this.textBounds.setBounds(buttonBounds.x + textPadding.left, buttonBounds.y + textPadding.top, buttonBounds.width - textPadding.right, buttonBounds.height - textPadding.bottom);
		this.r = 1.0f;
		this.g = 1.0f;
		this.b = 1.0f;
		this.a = 1.0f;
		this.textColor = new Color(1f, 1f, 1f, 1f);
		Font defaultFont = new Font("Times New Roman", Font.PLAIN, 24);
		setFont(defaultFont, true);

	}

	@Override
	public void render()
	{

		int mx = Mouse.getX();
		int my = window.getHeight() - Mouse.getY();
		this.buttonBounds.setBounds((int) x, (int) y, (int) width, (int) height);
		this.textBounds.setBounds(buttonBounds.x + textPadding.left, buttonBounds.y + textPadding.top, buttonBounds.width - textPadding.right, buttonBounds.height - textPadding.bottom);
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

		this.textFont.drawString(x + (getWidth() / 2) - (textFont.getWidth(text) / 2), y + (getHeight() / 2) - (textFont.getHeight() / 2), text, textColor);
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

	public void setFont(Font font, boolean antialias)
	{
		font = font.deriveFont(Utils.getScaledFontSize(this.text, textBounds, new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB).getGraphics(), font));
		this.textFont = new TrueTypeFont(font, antialias);
	}

	public TrueTypeFont getTextFont()
	{
		return this.textFont;
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

	public void setTextColor(float r, float g, float b, float a)
	{
		this.textColor = new Color(r, g, b, a);
	}

}
