package net.elitesource.gengine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;

public class Controller extends Thread implements ActionListener
{

	protected Game game;
	protected boolean listenInput;
	protected Timer apsTimer;

	public Controller(Game game, int aps)
	{
		this.game = game;
		this.listenInput = true;
		this.apsTimer = new Timer(1000 / aps, this);
	}

	public Controller(Game game)
	{
		this.game = game;
		this.listenInput = true;
		this.apsTimer = new Timer(1000 / 60, this);
	}

	public Game getGame()
	{
		return this.game;
	}

	public void use()
	{
		this.start();
		this.listenInput = true;
	}

	public void reload() throws InterruptedException
	{
		this.join();
		// Use to reload new configurations for controls later.
		// For now, ill just hard code the controls
		this.start();
	}

	public void listen()
	{
	}

	@Override
	public void run()
	{
		this.apsTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource().equals(apsTimer))
		{
			this.listen();
		}
	}
}
