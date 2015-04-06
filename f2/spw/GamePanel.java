package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		
	
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}
	
	
	public void HPgent(int a,int b){
			big.setColor(Color.WHITE);	
			big.fillRect ( 7, 30, 370, 20 );
			big.setColor ( Color.BLACK);
			big.fillRect ( 10, 35, 363, 10 );
		
			big.setColor ( Color.RED );
				//big.fillRect ( 10, 35, 363, 10 );
			big.fillRect (a,35,b,10 );

		}
	
	public void RandomColor(){
		int R = (int)(Math.random()*256);
		int G = (int)(Math.random()*256);
		int B= (int)(Math.random()*256);
		int y = (int)(Math.random()*20);
	
	
		Color color = new Color(R, G, B);
		//Color color1 = new Color(0,0,0);
		System.out.println(color + "  " + y);
		if(color == color.GREEN  || color == color.BLUE){
	      color = color.BLACK;
			
		 }
		big.setBackground(color);
		}
		
	
		

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
	
}
