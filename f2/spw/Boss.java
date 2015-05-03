package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;



	
import java.awt.Toolkit;
import java.awt.Image;
public class Boss extends Sprite{

	private int step = 1;
	private boolean alive = true;
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	public  int HP_Boss = 7;
	//private boolean hit = false; //check if this enemy hit with bullet
	
	public Boss(int x, int y) {
		super(x-70, y,100,100);
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().getImage("boss.PNG");
		g.drawImage(img, x, y, width, height, null); 
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public void hit(){
		HP_Boss--;
		if(HP_Boss<0){
		    HP_Boss = 0;
			}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public int GETHP_Boss(){
	
		return HP_Boss;
		}
	
	

	public void bossdie(){
		alive = false ;
	}
}
