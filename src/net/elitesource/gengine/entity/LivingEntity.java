package net.elitesource.gengine.entity;

import java.util.ArrayList;

import net.elitesource.gengine.CollisionEvent;
import net.elitesource.gengine.CollisionType;
import net.elitesource.gengine.Direction;

public abstract class LivingEntity extends AbstractEntity implements ILivingEntity
{

	protected float health, movementSpeed, attackSpeed, attackDamage;

	public LivingEntity(float x, float y, float width, float height)
	{
		super(x, y, width, height);
		this.setDefaults();
	}

	public LivingEntity(float x, float y, float width, float height, float health, float movementSpeed, float attackSpeed, float attackDamage)
	{
		super(x, y, width, height);
		this.health = health;
		this.movementSpeed = movementSpeed;
		this.attackSpeed = attackSpeed;
		this.attackDamage = attackDamage;
	}

	private void setDefaults()
	{
		this.health = 10.0f;
		this.movementSpeed = 1.0f;
		this.attackSpeed = 1.0f;
		this.attackDamage = 1.0f;
	}

	@Override
	public void move(Direction d)
	{

		if (this.isCollidable())
		{
			ArrayList<CollisionEvent> event = this.collisionEvents;
			if (event.size() > 0)
			{
				for (int i = 0; i < event.size(); i++)
				{
					if (event.get(i) != null)
					{
						if (event.get(i).getCollisionType() == CollisionType.TOP && d == Direction.UP)
						{
							return;
						}
						if (event.get(i).getCollisionType() == CollisionType.BOT && d == Direction.DOWN)
						{
							return;
						}
						if (event.get(i).getCollisionType() == CollisionType.LEFT && d == Direction.LEFT)
						{
							return;
						}
						if (event.get(i).getCollisionType() == CollisionType.RIGHT && d == Direction.RIGHT)
						{
							return;
						}
					} else
					{
						continue;
					}
				}
				finalMove(d);
				System.out.println(event.size());
			} else
			{
				finalMove(d);
			}

		} else
		{
			finalMove(d);
		}

	}

	private void finalMove(Direction d)
	{
		if (d == Direction.UP)
		{
			this.setY(this.getY() - this.getMovementSpeed());
		} else if (d == Direction.DOWN)
		{
			this.setY(this.getY() + this.getMovementSpeed());
		} else if (d == Direction.LEFT)
		{
			this.setX(this.getX() - this.getMovementSpeed());
		} else if (d == Direction.RIGHT)
		{
			this.setX(this.getX() + this.getMovementSpeed());
		}
	}

	public float getHealth()
	{
		return health;
	}

	public void setHealth(float health)
	{
		this.health = health;
	}

	public float getMovementSpeed()
	{
		return movementSpeed;
	}

	public void setMovementSpeed(float movementSpeed)
	{
		this.movementSpeed = movementSpeed;
	}

	public float getAttackSpeed()
	{
		return attackSpeed;
	}

	public void setAttackSpeed(float attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}

	public float getAttackDamage()
	{
		return attackDamage;
	}

	public void setAttackDamage(float attackDamage)
	{
		this.attackDamage = attackDamage;
	}

}
