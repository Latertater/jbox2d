package org.jbox2d.dynamics.contacts;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.pooling.SingletonPool;
import org.jbox2d.structs.collision.ShapeType;
import org.jbox2d.structs.dynamics.contacts.ContactCreateFcn;

public class CircleContact extends Contact implements ContactCreateFcn {

	public CircleContact(Fixture fixtureA, Fixture fixtureB){
		super(fixtureA, fixtureB);
		assert(m_fixtureA.getType() == ShapeType.CIRCLE_SHAPE);
		assert(m_fixtureB.getType() == ShapeType.CIRCLE_SHAPE);
	}
	
	@Override
	protected void evaluate() {
		Body bodyA = m_fixtureA.getBody();
		Body bodyB = m_fixtureB.getBody();
		
		SingletonPool.getCollision().collideCircles(m_manifold,
				m_fixtureA.getShape(), bodyA.getTransform(),
				m_fixtureB.getShape(), bodyB.getTransform());
	}
	/**
	 * Called to create a new circle contact, used from the static methods in {@link Contact}
	 */
	@Override
	public final Contact contactCreateFcn(Fixture fixtureA, Fixture fixtureB) {
		// TODO djm: pool these
		return new CircleContact(fixtureA, fixtureB);
	}

	@Override
	public final void contactDestroyFcn(Contact contact) {
		// TODO djm: put back in pool
	}
}