package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;


import java.awt.Toolkit;
import java.awt.Image;
public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<Sprite> sprites1 = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
		
	}

	public void updateGameUI(GameReporter reporter){
	
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		//
		big.setColor(Color.PINK);
		big.fillOval ( 340, 60, 32, 32 );	
		big.setColor(Color.BLACK);
		big.fillOval(343,63,26,26);
				
				
		big.setColor(Color.PINK);
		big.fillOval ( 340, 100, 32, 32 );	
		big.setColor(Color.BLACK);
		big.fillOval(343,103,26,26);
				
		big.setColor(Color.PINK);
		big.fillOval ( 340, 140, 32, 32 );	
		big.setColor(Color.BLACK);
		big.fillOval(343,143,26,26);
			//	
				if(reporter.getItem() == 1){ Itemgent1(); }
				
				if(reporter.getItem() == 2){ Itemgent2(); Itemgent1();}
				
				if(reporter.getItem() == 3){ Itemgent3(); Itemgent2(); Itemgent1();}
				
				if(reporter.getUseItem() == 1 && reporter.getItem() == 0)  {
					Itemgent1();
				}
				
				if(reporter.getUseItem() == 1 && reporter.getItem() == 1)  {
					Itemgent4();
				}

				if(reporter.getUseItem() == 1 && reporter.getItem() == 2)  {
						Itemgent1();
				}
				if(reporter.getUseItem() == 1 && reporter.getItem() == 3) {
					Itemgent1();Itemgent2(); 
				}
		//
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		for(Sprite s1 : sprites1){
			s1.draw(big);
			
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
		int R = (int)(Math.random()*75);
		int G = (int)(Math.random()*75);
		int B= (int)(Math.random()*75);
		//int y = (int)(Math.random()*20);
	
	
		Color color = new Color(R, G, B);
		//Color color1 = new Color(0,0,0);
		//System.out.println(color + "  " + y);
		if(color == color.GREEN  || color == color.BLUE){
	      color = color.BLACK;
			
		 }
		big.setBackground(color);
		}
		
	public void Itemgent1(){
				big.setColor(Color.RED);
				big.fillOval(343,63,26,26);
				//big.setBackground(Color.BLACK);

		}
	public void Itemgent2(){
				big.setColor(Color.RED);
				big.fillOval(343,103,26,26);
				//big.setBackground(Color.BLACK);

		}
	public void Itemgent3(){
				big.setColor(Color.RED);
				big.fillOval(343,143,26,26);
				//big.setBackground(Color.BLACK);

		}
	public void Itemgent4(){
					big.setColor(Color.BLACK);
					big.fillOval(343,63,26,26);

		}
		

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
	
}
