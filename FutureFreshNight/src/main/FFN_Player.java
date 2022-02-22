package main;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGames.util.Key;
import peterGraphics.util.Camera;
import peterGraphics.util.GText;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

public class FFN_Player extends GameObject {
	
	int[] controls = new int[5];
	int speed;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	private Camera camera;
	private Point cOffset;
	private Point mOffset;
	
	private Point lDir;
	public int score;
	private GText scoreText;
	
	public FFN_Player(GameController game, Config Cfg, int Speed) {
		super(game, Cfg);
		tag = "player";
		speed = Speed;
		up = false;
		down = false;
		left = false;
		right = false;
		
		lDir = new Point(1,0);
		
		cOffset = new Point(-290,-250);
		mOffset = new Point(0,0);
		scoreText = new GText(new Point(100,100),"Score: " + score, new Color(50,50,50));
		game.addInfoText(scoreText);
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		mask.addShape(Shape.Rect(0,0,20,20));
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rectF(0,0,20,20,10,10,200);
//		texture.text(0,0,point.x+","+point.y,0,0,0);
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub
//		parentGame.addGTick(this);
		
	}

	@Override
	public void postInit() {
		List<Key> keys = cfg.keys;
		for(int i = 0; i < keys.size(); i++) {
			Key key = keys.get(i);
			if(key.name.equalsIgnoreCase("up")) {
				controls[0] = key.id;
			} else if(key.name.equalsIgnoreCase("down")) {
				controls[1] = key.id;
			} else if(key.name.equalsIgnoreCase("left")) {
				controls[2] = key.id;
			} else if(key.name.equalsIgnoreCase("right")) {
				controls[3] = key.id;
			} else if(key.name.equalsIgnoreCase("forward")) {
				controls[0] = key.id;
			} else if(key.name.equalsIgnoreCase("back")) {
				controls[1] = key.id;
			} else if(key.name.equalsIgnoreCase("fire")) {
				controls[4] = key.id;
			}
		}
		camera = parentGame.getdraw().getCamera();
	}

	@Override
	public void onTick(InputManeger input) {
		if(input.wasKeyPressedI(controls[0])) {
			up = true;
		}
		if(input.wasKeyReleasedI(controls[0])) {
			up = false;
		}
		
		if(input.wasKeyPressedI(controls[1])) {
			down = true;
		}
		if(input.wasKeyReleasedI(controls[1])) {
			down = false;
		}
		
		if(input.wasKeyPressedI(controls[2])) {
			left = true;
		}
		if(input.wasKeyReleasedI(controls[2])) {
			left = false;
		}
		
		if(input.wasKeyPressedI(controls[3])) {
			right = true;
		}
		if(input.wasKeyReleasedI(controls[3])) {
			right = false;
		}
		boolean c = false;
		if(up) {
			c = c || !moveC(0,-speed,"bullet");
		}
		if(down) {
			c = c || !moveC(0,speed,"bullet");
		}
		if(left) {
			c = c || !moveC(-speed,0,"bullet");
		}
		if(right) {
			c = c || !moveC(speed,0,"bullet");
		}
		
		if((up || down) && !(left || right)) {
			lDir.x = 0;
			lDir.y = up?-10:10;
		} else if(!(up || down) && (left || right)) {
			lDir.y = 0;
			lDir.x = left?-10:10;
		} else {
			lDir.x = left?-7:7;
			lDir.y = up?-7:7;
		}
		
		if(input.wasKeyPressedI(controls[4])) {
			Bullet b = new Bullet(parentGame, cfg, (Point) lDir.clone(), true);
			b.move(point.x+10,point.y+10);
			parentGame.addObject(b);
		}
		
		Point camPos = camera.point;
		
//		if(camPos.x - point.x+10 > cOffset.x+mOffset.x) {
//			camPos.x = point.x+10 + (cOffset.x+mOffset.x);
//		}
//		if(camPos.x - point.x+10 < cOffset.x-mOffset.x) {
//			camPos.x = point.x+10 + (cOffset.x-mOffset.x);
//		}
//		if(camPos.y - point.y+10 > cOffset.y+mOffset.y) {
//			camPos.y = point.y+10 + (cOffset.y+mOffset.y);
//		}
//		if(camPos.y - point.y+10 < cOffset.y-mOffset.y) {
//			camPos.y = point.y+10 + (cOffset.y-mOffset.y);
//		}
		camPos.x = point.x + 10 + cOffset.x;
		camPos.y = point.y + 10 + cOffset.y;
		
		scoreText.text = "Score: " + score;
	}

	@Override
	protected void collided(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "FFN_Player";
	}
	
	@Override
	public void onGTick() {
		texture.clear();
		texture.setPoint(point);
		setDraw(texture);
	}
	
	@Override
	public String onSave() {
		String out = "";
		
		out += "\t\tspeed:" + speed + ";" + "\n";
		
		return out;
	}

	@Override
	public GameObject newObj(String[] file) {
		FFN_Player nP = new FFN_Player(parentGame,cfg,0);
		nP.setDefParm(file);
		nP.speed = Integer.parseInt(file[6].substring(8, file[6].length()-1));
//		System.out.println(speed);
		return nP;
	}

}