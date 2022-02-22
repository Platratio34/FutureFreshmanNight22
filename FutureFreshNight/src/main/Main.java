package main;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import peterGames.GameController;
import peterGames.util.Config;

public class Main {
	
	private static GameController game;
	public static FFN_Player player;
	
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
		
		ArrayList<String> strs = new ArrayList<String>();
		try {
			InputStream in = Main.class.getResourceAsStream("default.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//			System.out.println(reader.lines().toArray(String[]::new).length);
			game.loadWorld(reader.lines().toArray(String[]::new));
			
//			loadWorld();
		} catch (Exception e) {
			System.out.println("Faild to load " + e);
		}
		
//		game.loadWorld()
		game.run();
		player = (FFN_Player)game.getObjectsByTag("player")[0];
		player.score = 19;
	}
}
