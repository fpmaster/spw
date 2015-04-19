package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Enemy> items = new ArrayList<Enemy>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private int time = 0;
	private int time1 = 0;
	
	public int xaxisHP = 10;
	public int useItem = 0;
	public int item = 0;
	public int widthHP = 363;
	
	private double difficulty = 0.05;
	private double difficulty1 = 0.003;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateItem(){
				item e1 = new item((int)(Math.random()*390), 30);
				gp.sprites1.add(e1);
				items.add(e1);
		}
	
	private void process(){
		if(time >0)
			time--;
		
		if(time1 >0)
			time1--;
	
		if(Math.random() < difficulty){
			generateEnemy();
		}
		if(Math.random() < difficulty1){
			generateItem();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Enemy> e_iter1 = items.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 500;
				if(score >= 5000 &&  score % 5000 == 0){
				gp.RandomColor();
				}
			}
		}
		
		while(e_iter1.hasNext() ){
				Enemy e1 = e_iter1.next();
			//Enemy e1 = e_iter1.next();
			e1.proceed();

			if(!e1.isAlive()){
				e_iter1.remove();
				gp.sprites1.remove(e1);

			}

		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double er1; // item
		
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(time == 0){
				xaxisHP += 70;
				widthHP -= 70;
				time = 25;
				if(xaxisHP > 363){
					die();
				}
				return;
			}
		}
		}
		// item intersec
		for(Enemy e1 : items){
				er1 = e1.getRectangle();
				if(er1.intersects(vr)){
					if(time1 == 0){
						//gp.sprites1.remove(e1);
						e1.getIntersect();
						item++;

					 time1 = 30;
					return;
					}
				}
			}
		if(item > 3)
			item=3;
		
		gp.updateGameUI(this);
		gp.HPgent(xaxisHP,widthHP);
	}
	
	public void die(){
		gp.HPgent(xaxisHP,widthHP);
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_DOWN:
			v.move_Y(1);
			break;
		case KeyEvent.VK_UP:
			v.move_Y(-1);
			break;
		 case KeyEvent.VK_Q:
			if( item >= 1 && item <= 3){
				
					xaxisHP = 10;
					widthHP = 363;
					
					
				item--;
			 if(item <= 0) 
				item=0;
			useItem++;
			//System.out.println(d);
			break;
			}
		}
		if(useItem>0)	
			useItem=0;
	}
	public int getItem(){
		return item;
	}

	public int getUseItem(){
		return useItem;
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
