package net.elitesource.gengine.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import net.elitesource.gengine.Game;

public class Controller extends Thread implements ActionListener
{

	protected Game game;
	private boolean listenInput;
	protected Timer apsTimer;
	protected ArrayList<IControlHandler> controlHandlers = new ArrayList<IControlHandler>();

	/**
	 * Creates a new controller object with a specified actions per second (how
	 * many times per second controls are ticked). If aps <= 0 then the aps is
	 * set to default (60).
	 * 
	 * @param game
	 * @param aps
	 */
	public Controller(Game game, int aps)
	{
		this.game = game;
		this.setListening(true);
		if (aps <= 60)
		{
			this.apsTimer = new Timer(1000 / aps, this);
		}
	}

	/**
	 * Creates a new controller object with the default actions per second (how
	 * many times per second controls are ticked). By default, the actions per
	 * second is 60.
	 * 
	 * @param game
	 * @param aps
	 */
	public Controller(Game game)
	{
		this.game = game;
		this.setListening(true);
		this.apsTimer = new Timer(1000 / 60, this);
	}

	/**
	 * Returns the game object declared in the constructor.
	 * 
	 * @return
	 */
	public Game getGame()
	{
		return this.game;
	}

	/**
	 * Enables this use of this controller. If this isn't called (which it is by
	 * default by the Game object) the controller will not listen for
	 * keystrokes.
	 */
	public void use()
	{
		this.start();
		this.setListening(true);
	}

	/**
	 * Still a WIP, it does what it says. It reloads the controller (for when
	 * you set controls in game and need to reload them).
	 * 
	 * @see The comments inside this method.
	 * 
	 * @throws InterruptedException
	 */
	public void reload() throws InterruptedException
	{
		this.join();
		// Use to reload new configurations for controls later.
		// For now, ill just hard code the controls
		this.start();
	}

	/**
	 * Listens for keystrokes. Called a given number per second (dictated by the
	 * APS declared in the constructor).
	 */
	public void listen()
	{
		for (int i = 0; i < controlHandlers.size(); i++)
		{
			controlHandlers.get(i).keyCheck();
		}
	}

	/**
	 * Runs this thread. Ignore this. Not too important.
	 */
	@Override
	public void run()
	{
		this.apsTimer.start();
	}

	/**
	 * Action per second stuff. Again, not important, ignore.
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource().equals(apsTimer))
		{
			this.listen();
		}
	}

	public boolean isListening()
	{
		return listenInput;
	}

	public void setListening(boolean listenInput)
	{
		this.listenInput = listenInput;
	}
}
