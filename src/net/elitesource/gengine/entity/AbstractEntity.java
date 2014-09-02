package net.elitesource.gengine.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import net.elitesource.gengine.CollisionEvent;
import net.elitesource.gengine.CollisionType;

public abstract class AbstractEntity implements IEntity
{

	protected float width, height, x, y, r, g, b, a;
	protected Rectangle collisionBox;
	protected boolean isCollidable;
	//TODO NEed to find a way to remove the collision events.. maybe add a check in the collision engine for those entities (if they're still collided)
	protected ArrayList<CollisionEvent> collisionEvents = new ArrayList<CollisionEvent>();

	//TODO IDEA: Collisions Have custom IDS

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

	@Override
	public ArrayList<CollisionEvent> collides(AbstractEntity e)
	{
		ArrayList<CollisionEvent> result = new ArrayList<CollisionEvent>();

		if (e.getCollisionBox().intersects(this.getCollisionBox()))
		{

			if (this.getCollisionBox().intersectsLine(e.getX(), e.getY(), e.getX() + e.getWidth(), e.getY()))
			{
				/*	OTHER TOP
				 * x----x
				 * |	|
				 * |	|
				 * ------
				 */
				result.add(new CollisionEvent(e, this, CollisionType.BOT));
			}
			if (this.getCollisionBox().intersectsLine(e.getX() + e.getWidth(), e.getY(), e.getX() + e.getWidth(), e.getY() + e.getHeight()))
			{
				/*	OTHER RIGHT
				 * -----x
				 * |	|
				 * |	|
				 * -----x
				 */
				result.add(new CollisionEvent(e, this, CollisionType.LEFT));
			}
			if (this.getCollisionBox().intersectsLine(e.getX() + e.getWidth(), e.getY() + e.getHeight(), e.getX(), e.getY() + e.getHeight()))
			{
				/* OTHER BOTTOM
				 * ------
				 * |	|
				 * |	|
				 * x----x
				 */
				result.add(new CollisionEvent(e, this, CollisionType.TOP));
			}
			if (this.getCollisionBox().intersectsLine(e.getX(), e.getY(), e.getX(), e.getY() + e.getHeight()))
			{
				/* OTHER LEFT
				 * x-----
				 * |	|
				 * |	|
				 * x-----
				 */
				result.add(new CollisionEvent(e, this, CollisionType.RIGHT));
			}

		}

		//FIXME Probably something wrong with this code..
		if (result.size() > 0)
		{
			ArrayList<CollisionEvent> nonDupedEvents = new ArrayList<CollisionEvent>();

			for (CollisionEvent resultEvent : result)
			{
				if (getCollisionEvents().size() > 0)
				{
					for (CollisionEvent collisionEvent : getCollisionEvents())
					{
						System.out.println(result.size());
						if (resultEvent.getCollisionType() == collisionEvent.getCollisionType() && resultEvent.getCollidedEntity() == collisionEvent.getCollidedEntity() && resultEvent.getEntity() == collisionEvent.getEntity())
						{

						} else
						{
							nonDupedEvents.add(resultEvent);
						}

					}

				} else
				{
					getCollisionEvents().add(resultEvent);
				}
			}

			for (CollisionEvent e3 : nonDupedEvents)
			{
				collisionEvents.add(e3);
			}

		}

		if (collisionEvents.size() > 200)
		{
			try
			{
				throw new Exception("Uh Oh!");
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(1);
			}
		}

		return result;
	}

	@Override
	public ArrayList<CollisionEvent> getCollisionEvents()
	{
		return this.collisionEvents;
	}

}
