package net.elitesource.gengine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import net.elitesource.gengine.control.Controller;
import net.elitesource.gengine.event.ButtonClickEvent;
import net.elitesource.gengine.event.ButtonHoverEvent;
import net.elitesource.gengine.event.ButtonIdleEvent;
import net.elitesource.gengine.event.IGuiEvent;
import net.elitesource.gengine.graphics.GraphicalWindow;
import net.elitesource.gengine.graphics.GraphicsType;
import net.elitesource.gengine.gui.ActionListener;
import net.elitesource.gengine.gui.Button;
import net.elitesource.gengine.models.Model;

import org.newdawn.slick.util.ResourceLoader;

public class Start implements ActionListener
{

	public static Game g;
	public static Button b;

	public static void main(String[] args)
	{
		g = new Game(new GraphicalWindow("New Window", 400, 400, GraphicsType.Dim2), new Controller(g, 30));
		b = new Button(100, 75, 160, 80, "Test Button");
		b.getModels().add(new Model("res/images/button_idle"));
		b.getModels().add(new Model("res/images/button_hover"));
		b.getModels().add(new Model("res/images/button_click"));
		b.getActionListeners().add(new Start());
		b.setColor(1.0f, 0, 0, 1.0f);
		b.setTextColor(0, 0, 1.0f, 1.0f);
		InputStream in = ResourceLoader.getResourceAsStream("res/fonts/default.ttf");
		Font font = null;

		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT, in);
		} catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		b.setFont(font, true);
		g.window.addRenderable(b);
		g.launch();
	}

	@Override
	public void actionEvent(IGuiEvent e)
	{
		if (e.getSource().equals(b))
		{
			if (e instanceof ButtonHoverEvent)
			{
				b.setText("Hovered!");
			}

			if (e instanceof ButtonIdleEvent)
			{
				b.setText("Idle!");
			}

			if (e instanceof ButtonClickEvent)
			{
				b.setText("Clicked!");
			}
		}
	}

}
