package net.elitesource.gengine.engine;

import java.util.ArrayList;

import net.elitesource.gengine.Game;
import net.elitesource.gengine.entity.AbstractEntity;

public class CollisionEngine extends Engine
{

	private Game game;

	public CollisionEngine(Game game)
	{

	}

	@Override
	public void run()
	{
		setRunning(true);
		while (isRunning())
		{
			ArrayList<AbstractEntity> checkEntities = new ArrayList<AbstractEntity>();
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
										if (e1.collides(e2))
										{

											//FIXME This wont work. I need to use footprints like I did in "amumu adventures..."
											// Actually it coud work... try usiung instersectsLine() ??
											e1.onCollide(e2);
											e2.onCollide(e1);

										}
									}
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

}
