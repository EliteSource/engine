package net.elitesource.gengine.debug;

import net.elitesource.gengine.control.IControlHandler;
import net.elitesource.gengine.utils.Location;

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

	}

}
