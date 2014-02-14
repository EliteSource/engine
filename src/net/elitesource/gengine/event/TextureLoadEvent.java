package net.elitesource.gengine.event;

import org.newdawn.slick.opengl.Texture;

import net.elitesource.gengine.graphics.TexturesLoader;
import net.elitesource.gengine.models.Model;

public class TextureLoadEvent extends AbstractEvent
{

	protected String[] textureNames;
	protected Model model;

	public TextureLoadEvent(String[] textureName, Model model)
	{
		this.textureNames = textureName;
		this.model = model;
	}

	@Override
	public void execute()
	{
		Texture[] textures = new Texture[this.textureNames.length];
		for (int i = 0; i < textureNames.length; i++)
		{
			textures[i] = TexturesLoader.loadTexture(textureNames[i]);
		}
		model.setTextures(textures);
	}

}
