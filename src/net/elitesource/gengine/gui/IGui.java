package net.elitesource.gengine.gui;

import java.util.ArrayList;

import net.elitesource.gengine.entity.IEntity;
import net.elitesource.gengine.models.Model;

public interface IGui extends IEntity
{
	public ArrayList<Model> getModels();
}
