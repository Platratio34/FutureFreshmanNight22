package main;

import java.awt.event.KeyEvent;

import peterGames.GameController;
import peterGames.objects.GameBlock;
import peterGames.util.Config;
import peterGraphics.util.Camera;

public class Main {
	
private static GameController game;
	
	public static void main(String[] args) {
		game = new GameController();
		Config cfg = game.getconfig();
		game.addDefObj(new FFN_Player(game,cfg,2));
		game.addDefObj(new Enemy(game,cfg));
		cfg.addKey(0,"up",KeyEvent.VK_W);
		cfg.addKey(1,"down",KeyEvent.VK_S);
		cfg.addKey(2,"left",KeyEvent.VK_A);
		cfg.addKey(3,"right",KeyEvent.VK_D);
		cfg.addKey(4,"fire",KeyEvent.VK_SPACE);
//		Player player = new Player(game, cfg);
//		player.move(10,10);
//		game.addObject(player);
//		Player player2 = new Player(game, cfg);
//		player2.move(10,50);
//		game.addObject(player2);
		GameBlock gB = new GameBlock(game,cfg,2,2,255,0,0);
		gB.move(0,0);
		game.addObject(gB);
//		game.loadWorld("save.txt");
//		for(int i = 0; i < 600; i += 10) {
//			GameBlock gB2 = new GameBlock(game,cfg,2,2,(i%50==0?255:0),(i%50==0?0:255),0);
//			gB2.move(i,0);
//			game.addObject(gB2);
//			gB2 = new GameBlock(game,cfg,2,2,(i%50==0?255:0),(i%50==0?0:255),0);
//			gB2.move(0,i);
//			game.addObject(gB2);
//			if(i%50 == 0) {
//				GameText gT = new GameText(game,cfg,i+"");
//				gT.move(i,0);
//				game.addObject(gT);
//				gT = new GameText(game,cfg,i+"");
//				gT.move(1,i);
//				game.addObject(gT);
//			}
//		}
//		game.addObject(new FFN_Player(game,cfg,2));
		game.run();
		Camera camera = game.getdraw().getCamera();
		camera.point.x = -100;
		camera.point.y = -100;
//		game.saveWorld("save.txt");
	}
}
