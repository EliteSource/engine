package net.elitesource.gengine.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class AbstractEntity implements IEntity
{

	protected float width, height, x, y, r, g, b, a;
	protected Rectangle collisionBox;
	protected boolean isCollidable;
	protected ArrayList<AbstractEntity> collidedEntities;

	public AbstractEntity(float x, float y, float width, float height)
	{
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.r = 1.0f;
		this.g = 1.0f;
		this.b = 1.0f;
		this.a = 1.0f;
		this.collisionBox = new Rectangle();
		this.collisionBox.setBounds((int) x, (int) y, (int) width, (int) height);
		this.isCollidable = true;
		this.collidedEntities = new ArrayList<AbstractEntity>();
	}

	@Override
	public float getWidth()
	{
		return width;
	}

	@Override
	public void setWidth(float width)
	{
		this.width = width;
	}

	@Override
	public float getHeight()
	{
		return height;
	}

	@Override
	public void setHeight(float height)
	{
		this.height = height;
	}

	@Override
	public float getX()
	{
		return x;
	}

	@Override
	public void setX(float x)
	{
		this.x = x;
	}

	@Override
	public float getY()
	{
		return y;
	}

	@Override
	public void setY(float y)
	{
		this.y = y;
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
		result[0] = r;
		result[1] = g;
		result[2] = b;
		result[3] = a;
		return result;
	}

	@Override
	public Rectangle getCollisionBox()
	{
		this.collisionBox.setBounds((int) x, (int) y, (int) width, (int) height);
		return this.collisionBox;
	}

	@Override
	public boolean isCollidable()
	{
		return this.isCollidable;
	}

	@Override
	public void setCollidable(boolean collidable)
	{
		this.isCollidable = collidable;
	}

	public boolean collides(AbstractEntity e2)
	{
		return this.getCollisionBox().intersects(e2.getCollisionBox());
	}

}
