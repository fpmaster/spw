package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Toolkit;
import java.awt.Image;
public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 600; //distance of bullet that start to fade
	public static final int Y_TO_DIE = 0; //end of panel, when bullet out of panel it's die
	
	private int step = 10; //speed of bullet
	private boolean alive = true;
	
	public Bullet(int x, int y) {
		super(x , y-10, 35, 35);
	}

	@Override
	public void draw(Graphics2D g) {
		if(y > Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		Image img = Toolkit.getDefaultToolkit().getImage("bul2.PNG");
		g.drawImage(img, x, y, width, height, null); 
		
	}

	public void proceed(){
		y -= step;
		if(y < Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
}
