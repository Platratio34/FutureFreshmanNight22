package main;

import java.awt.Point;
import java.util.Random;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;
import peterLibrary.PeterMath;

public class Enemy extends GameObject {

	public Enemy(GameController game) {
		super(game);
		tag = "enemy";
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		mask.addShape(Shape.Rect(-10,-10,20,20));
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rectF(-10,-10,20,20,255,0,0);
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
		if(Main.player != null && Main.player.deadText == null) {
			Point pP = Main.player.getPoint();
			pP.x += 10;
			pP.y += 10;
			Point dP = new Point(point.x-pP.x, point.y-pP.y);
			dP.x = -1 * PeterMath.clampI(dP.x,2,-2);
			dP.y = -1 * PeterMath.clampI(dP.y,2,-2);
			moveC(dP);
		}
	}

	@Override
	public String getType() {
		return "Enemy";
	}

	@Override
	public GameObject newObj(String[] file) {
		Enemy nE = new Enemy(parentGame);
		nE.setDefParm(file);
		return nE;
	}

	@Override
	public GameObject newObj(JsonObj obj) {
		Enemy nE = new Enemy(parentGame);
		nE.setDefParm(obj);
		return nE;
	}
	
	@Override
	public void collided(GameObject other) {
		if(other.getTag().equals("bullet")) {
			destroy();
			createNewEnemy(parentGame);
		} else if(other.getTag().equals("player")) {
			((FFN_Player)other).kill();
		} else {
			Point oP = other.getPoint();
			Point dP = new Point(point.x-oP.x, point.y-oP.y);
			dP.x = 1 * PeterMath.clampI(dP.x,2,-2);
			dP.y = 1 * PeterMath.clampI(dP.y,2,-2);
			moveC(dP);
		}
	}
	
	public static void createNewEnemy(GameController game) {
		Random rand = new Random();
		int x = rand.nextInt(900) - 450;
		int y = rand.nextInt(900) - 450;
		while(x > 0 && x < 200) {
			x = rand.nextInt(900) - 450;
		}
		while(y > 0 && y < 200) {
			y = rand.nextInt(900) - 450;
		}
		Enemy e = new Enemy(game);
		e.move(x,y);
		game.addObject(e);
		Main.player.score++;
	}
	
	@Override
	public void onDestroy() {
		parentGame.removeObject(this);
	}

}
