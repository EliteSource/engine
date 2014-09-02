package net.elitesource.gengine;

import java.util.ArrayList;

import net.elitesource.gengine.entity.AbstractEntity;

public class CollisionEvent
{

	private AbstractEntity entity, collidedEntity;
	private CollisionType collisionType;
	private boolean isValid;

	public CollisionEvent(AbstractEntity collidedEntity, AbstractEntity entity, CollisionType collisionType)
	{
		super();
		this.collidedEntity = collidedEntity;
		this.entity = entity;
		this.collisionType = collisionType;
		this.isValid = true;
	}

	public AbstractEntity getCollidedEntity()
	{
		return collidedEntity;
	}

	public void setCollidedEntity(AbstractEntity collidedEntity)
	{
		this.collidedEntity = collidedEntity;
	}

	public CollisionType getCollisionType()
	{
		return collisionType;
	}

	public void setCollisionType(CollisionType collisionType)
	{
		this.collisionType = collisionType;
	}

	public AbstractEntity getEntity()
	{
		return entity;
	}

	public void setEntity(AbstractEntity entity)
	{
		this.entity = entity;
	}

	public void validate()
	{
		ArrayList<CollisionEvent> events = entity.collides(collidedEntity);
		this.isValid = false;
		if (events.size() > 0)
		{
			for (CollisionEvent e : events)
			{
				if (e.getCollisionType() == getCollisionType() && e.getCollidedEntity() == getCollidedEntity() && e.getEntity() == getEntity())
				{
					this.isValid = true;
				}
			}
		}
	}

	public boolean isValid()
	{
		if (!isValid)
		{
			try
			{
				this.finalize();
			} catch (Throwable e)
			{
				e.printStackTrace();
			}
		}
		return isValid;
	}

	public void setValid(boolean isValid)
	{
		this.isValid = isValid;
	}

}
