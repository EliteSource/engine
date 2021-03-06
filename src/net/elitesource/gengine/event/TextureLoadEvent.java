package net.elitesource.gengine.event;

import net.elitesource.gengine.graphics.TexturesLoader;
import net.elitesource.gengine.models.Model;

import org.newdawn.slick.opengl.Texture;

public class TextureLoadEvent extends OGLEvent
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
