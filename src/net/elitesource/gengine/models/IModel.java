package net.elitesource.gengine.models;

import org.newdawn.slick.opengl.Texture;

public interface IModel
{

	public String[] getTextureNames();

	public Texture[] getTextures();

	public void setTextures(Texture[] textures);

	public void bind();

	public void bind(int index);

}
