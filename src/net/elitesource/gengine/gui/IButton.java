package net.elitesource.gengine.gui;

import java.util.ArrayList;

public interface IButton extends IGui
{

	public void onClick();

	public void onIdle();

	public void onHover();

	public boolean isClicked();

	public boolean isIdle();

	public boolean isHovered();

	public String getText();

	public void setText(String text);

	public ArrayList<ActionListener> getActionListeners();

}
