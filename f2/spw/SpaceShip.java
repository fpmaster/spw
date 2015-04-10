package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;


import java.awt.Toolkit;
import java.awt.Image;
public class SpaceShip extends Sprite{

	int step = 8;
	
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, 120, 100);
		
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().getImage("thunder.GIF");
		g.drawImage(img, x, y, width, height, null); 
		
	}

	public void move(int direction){
		x += (step * direction);
		
		if(x < -20)
			x = -20;
		if(x > 400 - width)
			x = 400 - width;
		
	}
	public void move_Y(int direction1){
		y += (step * direction1);
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}


}
