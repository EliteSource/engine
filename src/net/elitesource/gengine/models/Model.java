package net.elitesource.gengine.models;

import net.elitesource.gengine.graphics.TexturesLoader;

import org.newdawn.slick.opengl.Texture;

public class Model implements IModel
{

	protected String[] textureNames;
	protected Texture[] textures;

	public Model(String[] textureNames)
	{
		this.textureNames = textureNames;
		textures = new Texture[this.textureNames.length];
		for (int i = 0; i < textureNames.length; i++)
		{
			textures[i] = TexturesLoader.loadTexture(textureNames[i]);
		}

	}

	public Model(String textureName)
	{
		this.textureNames = new String[1];
		this.textureNames[0] = textureName;
		this.textures = new Texture[1];
		this.textures[0] = TexturesLoader.loadTexture(textureName);
	}

	@Override
	public String[] getTextureNames()
	{
		return this.textureNames;
	}

	@Override
	public Texture[] getTextures()
	{
		return this.textures;
	}

	@Override
	public void setTextures(Texture[] textures)
	{
		this.textures = textures;
	}

	@Override
	public void bind(int index)
	{
		if (this.textures != null && this.textures.length >= (index + 1))
		{
			this.textures[index].bind();
		} else
		{
			return;
		}
	}

	@Override
	public void bind()
	{
		if (this.textures != null && this.textures.length > 0)
		{
			this.textures[0].bind();
		} else
		{
			return;
		}
	}

}
