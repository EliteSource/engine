package net.elitesource.gengine.debug;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;
import net.elitesource.gengine.entity.AbstractEntity;
import net.elitesource.gengine.entity.LivingEntity;

public class Player extends LivingEntity
{

	public Player(float x, float y, float width, float height, float health, float movementSpeed, float attackSpeed, float attackDamage)
	{
		super(x, y, width, height, health, movementSpeed, attackSpeed, attackDamage);
	}

	@Override
	public void onMove()
	{

	}

	@Override
	public void onCollide(AbstractEntity collidedObject)
	{

	}

	@Override
	public void render()
	{
		glPushMatrix();
		glBegin(GL_QUADS);
		glColor4f(r, g, b, a);
		glTexCoord2f(0, 0);
		glVertex2d(x, y);
		glTexCoord2f(1, 0);
		glVertex2d(x + width, y);
		glTexCoord2f(1, 1);
		glVertex2d(x + width, y + height);
		glTexCoord2f(0, 1);
		glVertex2d(x, y + height);
		glEnd();
		glPopMatrix();
	}

}
