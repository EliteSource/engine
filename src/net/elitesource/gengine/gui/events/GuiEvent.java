package net.elitesource.gengine.gui.events;

import net.elitesource.gengine.gui.IGui;

public class GuiEvent implements IGuiEvent
{

	private IGui source;

	public GuiEvent(IGui source)
	{
		this.source = source;
	}

	@Override
	public IGui getSource()
	{
		return this.source;
	}

}
