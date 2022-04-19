package main;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

import java.awt.Point;

import dataManagment.JsonObj;

public class Bullet extends GameObject {
	
	Point speed;
	boolean fromP;

	public Bullet(GameController game, Point speed, boolean fromP) {
		super(game);
		this.speed = speed;
		this.fromP = fromP;
		tag = "bullet";
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		mask.addShape(Shape.Rect(-4,-4,8,8));
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rectF(-4,-4,8,8,0,0,0);
		
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTick(InputManeger input) {
		// TODO Auto-generated method stub
		moveC(speed,fromP?"player":"enemy");
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameObject newObj(String[] file) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void collided(GameObject other) {
		if(!other.getTag().equals(fromP?"player":"enemy")) {
			destroy();
		}
	}
	
	@Override
	public void onDestroy() {
		parentGame.removeObject(this);
	}

	@Override
	public GameObject newObj(JsonObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
