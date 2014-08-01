package net.elitesource.gengine.event;

import java.awt.Rectangle;

import net.elitesource.gengine.Game;
import net.elitesource.gengine.entity.AbstractEntity;
import net.elitesource.gengine.entity.LivingEntity;
import net.elitesource.gengine.entity.Renderable;
import net.elitesource.gengine.utils.Location;

public class CollisionCheckEvent extends AbstractEvent
{

	private LivingEntity source;
	private Location location;
	private boolean isCollided = false;
	private boolean isExecuted = false;

	public CollisionCheckEvent(LivingEntity source, Location location)
	{
		super();
		this.source = source;
		this.location = location;
	}

	@Override
	public void execute()
	{
		System.out.println("Running event");
		for (int i = 0; i < Game.game.getWindow().getRenderEngine().getRenderables().size(); i++)
		{
			Renderable r = Game.game.getWindow().getRenderEngine().getRenderables().get(i);
			if (r instanceof AbstractEntity)
			{
				AbstractEntity entity = (AbstractEntity) r;
				if (entity.isCollidable() && !entity.equals(source))
				{
					Rectangle rect = source.getCollisionBox();
					int ms = (int) source.getMovementSpeed();

					if (source.getX() > location.getX())
					{
						rect.setBounds((int) (rect.getX() - ms), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
					}

					if (source.getX() < location.getX())
					{
						rect.setBounds((int) (rect.getX() + ms), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
					}

					if (source.getY() > location.getY())
					{
						rect.setBounds((int) rect.getX(), (int) (rect.getY() - ms), (int) rect.getWidth(), (int) rect.getHeight());
					}

					if (source.getY() < location.getY())
					{
						rect.setBounds((int) rect.getX(), (int) (rect.getY() + ms), (int) rect.getWidth(), (int) rect.getHeight());
					}

					if (entity.getCollisionBox().intersects(rect))
					{
						entity.onCollide(source);
						source.onCollide(entity);
						this.isCollided = true;
					}

				}
			}
		}
		this.isExecuted = true;
	}

	public LivingEntity getSource()
	{
		return source;
	}

	public Location getLocation()
	{
		return location;
	}

	public boolean isCollided()
	{
		return isCollided;
	}

	public boolean isExecuted()
	{
		return isExecuted;
	}

}
