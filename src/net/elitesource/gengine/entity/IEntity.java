package net.elitesource.gengine.entity;


public interface IEntity extends Renderable
{
	public void setX(float f);

	public void setY(float f);

	public float getX();

	public float getY();

	public void setWidth(float f);

	public void setHeight(float f);

	public float getWidth();

	public float getHeight();

	public void setColor(float r, float g, float b, float a);

	public float[] getColor();
}