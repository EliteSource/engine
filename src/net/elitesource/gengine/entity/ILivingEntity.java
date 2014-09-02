package net.elitesource.gengine.entity;

import net.elitesource.gengine.Direction;

public interface ILivingEntity
{

	public void move(Direction d);

	public void onMove();

}
