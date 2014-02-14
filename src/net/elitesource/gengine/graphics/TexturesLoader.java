package net.elitesource.gengine.graphics;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TexturesLoader
{
	public static Texture loadTexture(String name)
	{
		try
		{
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/images/" + name));
		} catch (FileNotFoundException e)
		{
			System.err.println("File: '" + "res/images/" + name + ".png" + "' was not found.");
		} catch (IOException e)
		{
			System.err.println("Generic IO Exception has occured: " + e.getStackTrace());
		}
		return null;
	}

}