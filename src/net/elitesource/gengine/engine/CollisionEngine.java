package net.elitesource.gengine.engine;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import net.elitesource.gengine.CollisionEvent;
import net.elitesource.gengine.Game;
import net.elitesource.gengine.entity.AbstractEntity;

public class CollisionEngine extends Thread
{

	private Game game;
	private boolean isRunning;

	public CollisionEngine(Game game)
	{
		//TODO On another note, lets just have a static Game object....
		//TODO onCollide doesnt really need to take in an arguement...
		this.game = game;
	}

	@Override
	public void run()
	{
		setRunning(true);
		while (isRunning())
		{
			for (int i = 0; i < game.getWindow().getRenderEngine().getRenderables().size(); i++)
			{
				if (game.getWindow().getRenderEngine().getRenderables().get(i) instanceof AbstractEntity)
				{
					AbstractEntity e1 = (AbstractEntity) game.getWindow().getRenderEngine().getRenderables().get(i);
					if (e1.isCollidable())
					{
						for (int j = 0; j < game.getWindow().getRenderEngine().getRenderables().size(); j++)
						{
							if (game.getWindow().getRenderEngine().getRenderables().get(j) instanceof AbstractEntity)
							{
								AbstractEntity e2 = (AbstractEntity) game.getWindow().getRenderEngine().getRenderables().get(j);
								if (e2.isCollidable())
								{
									if (e1 != e2)
									{
										doCollisionInteractions(e1, e2);
									} else
									{
										continue;
									}
								} else
								{
									continue;
								}
							} else
							{
								continue;
							}
						}
					} else
					{
						continue;
					}
				} else
				{
					continue;
				}
			}
		}
	}

	//TODO Need to make it so if the entity is collided, it kinda shoves them away so they're not on top of eachother.
	// FIXME Ok so several things happen when I test collision. Firstly, for whatever reason, the dies arent working correctly.
	// probably need to review the coordinate collision checks. Another bug, is when I collided inside the entity, for some reason
	// it goes haywire and adds a billion events (that dont get removed), AND I get jetted somewhere.

	private synchronized void doCollisionInteractions(AbstractEntity e1, AbstractEntity e2)
	{
		ArrayList<CollisionEvent> event = e1.collides(e2);
		if (event.size() > 0)
		{
			e1.onCollide(event);
		}

		if (e1.getCollisionEvents().size() > 0)
		{
			reloop: for (int c = 0; c < e1.getCollisionEvents().size(); c++)
			{
				e1.getCollisionEvents().get(c).validate();
				if (!e1.getCollisionEvents().get(c).isValid())
				{
					e1.getCollisionEvents().remove(c);
					System.out.println("TERY5");
					break reloop;
				}
			}

		}

	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

}
