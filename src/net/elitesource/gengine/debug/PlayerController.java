package net.elitesource.gengine.debug;

import net.elitesource.gengine.Direction;
import net.elitesource.gengine.control.IControlHandler;
import org.lwjgl.input.Keyboard;

public class PlayerController implements IControlHandler
{

	private Player p;

	public PlayerController(Player p)
	{
		super();
		this.p = p;
	}

	@Override
	public void keyCheck()
	{

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			p.move(Direction.UP);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			p.move(Direction.DOWN);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			p.move(Direction.LEFT);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			p.move(Direction.RIGHT);
		}
	}

}
