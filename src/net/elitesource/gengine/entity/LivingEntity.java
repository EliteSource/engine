package net.elitesource.gengine.entity;

import net.elitesource.gengine.Game;
import net.elitesource.gengine.event.CollisionCheckEvent;
import net.elitesource.gengine.utils.Location;

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
