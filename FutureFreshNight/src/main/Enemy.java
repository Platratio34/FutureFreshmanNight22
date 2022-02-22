package main;

import java.util.Random;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

public class Enemy extends GameObject {

	public Enemy(GameController game, Config Cfg) {
		super(game, Cfg);
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

	}

	@Override
	public String getType() {
		return "Enemy";
	}

	@Override
	public GameObject newObj(String[] file) {
		Enemy nE = new Enemy(parentGame,cfg);
		nE.setDefParm(file);
		return nE;
	}
	
	@Override
	public void collided(GameObject other) {
		if(other.getTag().equals("bullet")) {
			destroy();
			createNewEnemy(parentGame, cfg);
		}
	}
	
	public static void createNewEnemy(GameController game, Config cfg) {
		Random rand = new Random();
		int x = rand.nextInt(900) - 450;
		int y = rand.nextInt(900) - 450;
		while(x > 0 && x < 200) {
			x = rand.nextInt(900) - 450;
		}
		while(y > 0 && y < 200) {
			y = rand.nextInt(900) - 450;
		}
		Enemy e = new Enemy(game, cfg);
		e.move(x,y);
		game.addObject(e);
		Main.player.score++;
	}

}
