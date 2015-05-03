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
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Boss> Bosss = new ArrayList<Boss>();
	
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
	
	public int genboss = 0;
	public int bossfix = 60;
	
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
		
	private void generateBoss(){
				Boss BB = new Boss((int)(Math.random()*390), 30);
				gp.sprites.add(BB);
				Bosss.add(BB);
		}

	
	private void process(){
		
		if(time >0)
			time--;
		
		if(time1 >0)
			time1--;
	    
		if(genboss == bossfix){
			generateBoss();
			genboss =0;
		}
		
		if(Math.random() < difficulty){
			genboss ++;
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
				score += 100;
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
		//bullet
		Iterator<Bullet> b_iter = bullets.iterator();  
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
			}
		}
		
		
		
		Iterator<Boss> boss_iter = Bosss.iterator(); 
		while(boss_iter.hasNext()){
			Boss BB = boss_iter.next();
			BB.proceed();
		
			if(!BB.isAlive()){
				boss_iter.remove();
				gp.sprites.remove(BB);
			}
		}
		
		//
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double er1; // item
		Rectangle2D.Double br; //bullet
		Rectangle2D.Double boss1; //boss
		
		
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
		for(Bullet b : bullets){   
				br = b.getRectangle();
				if(br.intersects(er)){
					score+=1000;
				    e.getHit();
					b.getHit();
					return;
					}
					
				for(Boss BB : Bosss){	
				vr = v.getRectangle();
				boss1 = BB.getRectangle();
					if(boss1.intersects(br)){
				    BB.hit();
					b.getHit();
					if(BB.GETHP_Boss() == 0){
						BB.bossdie();
					}
					return;
					}
					
			    }
			}
		}
		
		//boss to spaceship
		for(Boss BB : Bosss){
				vr = v.getRectangle();
				boss1 = BB.getRectangle();
				if(boss1.intersects(vr)){
					xaxisHP = xaxisHP + 360;
					widthHP = widthHP - 360;
					 die();
					return;
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
		case KeyEvent.VK_SPACE:
			Shooter();
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
	
	private void Shooter(){
		Bullet b = new Bullet(v.x+45, v.y);
		gp.sprites.add(b);
		bullets.add(b);
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
