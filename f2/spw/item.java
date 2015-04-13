package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;


import java.awt.Toolkit;
import java.awt.Image;
public class item extends Enemy{

	private int step = 5;
	private boolean alive = true;
	//private boolean intersec = false; //check if this enemy hit with bullet
	
	public item(int x, int y) {
		super(x, y);
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		//g.setColor(Color.RED);
		//g.fillOval(x, y, width, height);
		
		Image img = Toolkit.getDefaultToolkit().getImage("hp.GIF");
		g.drawImage(img, x, y, 50, 50, null); 
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
}
