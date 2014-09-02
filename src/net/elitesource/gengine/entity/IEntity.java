package net.elitesource.gengine.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import net.elitesource.gengine.CollisionEvent;

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

	public Rectangle getCollisionBox();

	public void onCollide(ArrayList<CollisionEvent> event);

	public boolean isCollidable();

	public void setCollidable(boolean collidable);

	public ArrayList<CollisionEvent> collides(AbstractEntity e);
	
	public ArrayList<CollisionEvent> getCollisionEvents();
}
