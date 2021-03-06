package main;

import java.awt.Color;
import java.awt.Point;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGraphics.util.Camera;
import peterGraphics.util.GText;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

public class FFN_Player extends GameObject {
	
//	int[] controls = new int[5];
	int speed;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	private Camera camera;
	private Point cOffset;
	private Point lDir;
	public int score;
	private GText scoreText;
	public GText deadText = null;
	
	public FFN_Player(GameController game, int Speed) {
		super(game);
		tag = "player";
		speed = Speed;
		up = false;
		down = false;
		left = false;
		right = false;
		
		lDir = new Point(1,0);
		
		cOffset = new Point(-290,-250);
		new Point(0,0);
		scoreText = new GText(new Point(10,10),"Score: " + score, new Color(0,0,0));
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
//		List<Key> keys = parentGame.getConfig().keys;
//		for(int i = 0; i < keys.size(); i++) {
//			Key key = keys.get(i);
//			if(key.name.equalsIgnoreCase("up")) {
//				controls[0] = key.id;
//			} else if(key.name.equalsIgnoreCase("down")) {
//				controls[1] = key.id;
//			} else if(key.name.equalsIgnoreCase("left")) {
//				controls[2] = key.id;
//			} else if(key.name.equalsIgnoreCase("right")) {
//				controls[3] = key.id;
//			} else if(key.name.equalsIgnoreCase("forward")) {
//				controls[0] = key.id;
//			} else if(key.name.equalsIgnoreCase("back")) {
//				controls[1] = key.id;
//			} else if(key.name.equalsIgnoreCase("fire")) {
//				controls[4] = key.id;
//			}
//		}
		camera = parentGame.getdraw().getCamera();
		parentGame.addInfoText(scoreText);
		parentGame.addMouseUser(this);
	}

	@Override
	public void onTick(InputManeger input) {
		if(input.wasKeyPressed("up")) {
			up = true;
		}
		if(input.wasKeyReleased("up")) {
			up = false;
		}
		
		if(input.wasKeyPressed("down")) {
			down = true;
		}
		if(input.wasKeyReleased("down")) {
			down = false;
		}
		
		if(input.wasKeyPressed("left")) {
			left = true;
		}
		if(input.wasKeyReleased("left")) {
			left = false;
		}
		
		if(input.wasKeyPressed("right")) {
			right = true;
		}
		if(input.wasKeyReleased("right")) {
			right = false;
		}
		if(deadText == null) {
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
			
//			if(input.wasKeyPressedI(controls[4])) {
////				Bullet b = new Bullet(parentGame, cfg, (Point) lDir.clone(), true);
////				b.move(point.x+10,point.y+10);
////				parentGame.addObject(b);
//				fire(lDir);
//			}
			
			if(score >= 20) {
				deadText = new GText(new Point(257,230),"You Win",new Color(0,200,0));
				parentGame.addInfoText(deadText);
			}
		} else {
			if(input.wasKeyReleased("fire")) {
				parentGame.removeInfoText(deadText);
				deadText = null;
				moveA(90,90);
				score = 0;
			}
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
	
	private void fire(int x, int y) {
		fire(new Point(x,y));
	}
	private void fire(Point p) {
		Bullet b = new Bullet(parentGame, (Point)p.clone(), true);
		b.move(point.x+10,point.y+10);
		parentGame.addObject(b);
	}

	@Override
	protected void collided(GameObject object) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onMousePressed(int x, int y) {
		x -= 290;
		y -= 270;
		double l = Math.sqrt((x*x)+(y*y));
		x /= l/7d;
		y /= l/7d;
//		System.out.println("fire, "+ x +", " +y);
		fire(x,y);
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
	public void onSave(JsonObj obj) {
		obj.setKey("speed", speed);
	}

	@Override
	public GameObject newObj(String[] file) {
		FFN_Player nP = new FFN_Player(parentGame,0);
		nP.setDefParm(file);
		nP.speed = Integer.parseInt(file[6].substring(8, file[6].length()-1));
//		System.out.println(speed);
		return nP;
	}

	@Override
	public GameObject newObj(JsonObj obj) {
		FFN_Player nP = new FFN_Player(parentGame,0);
		nP.setDefParm(obj);
		nP.speed = obj.getKey("speed").integer();
		return nP;
	}

	public void kill() {
		deadText = new GText(new Point(257,230),"You Died",new Color(100,0,0));
		parentGame.addInfoText(deadText);
	}

}