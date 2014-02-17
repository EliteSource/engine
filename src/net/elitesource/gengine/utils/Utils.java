package net.elitesource.gengine.utils;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Utils
{
	public static float getScaledFontSize(String text, Rectangle rect, Graphics g, Font pFont)
	{
		float fontSize = 20.0f;
		Font font = pFont;
		font = g.getFont().deriveFont(fontSize);
		int width = g.getFontMetrics(font).stringWidth(text);
		fontSize = (rect.width / width) * fontSize;
		return fontSize;
	}
}
