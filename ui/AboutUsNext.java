package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class AboutUsNext extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	
	private JMainFrame jMainFrame;
	Point[] points=new Point[]{new Point(0,0),new Point(400,0)};
	private int now = 0;
	private int next = 0;
	private boolean isRight = true;
	
	
	public AboutUsNext(JMainFrame jframe){
		this.jMainFrame = jframe;
		
		this.setLayout(null);
		this.setBounds(322,400,380,220);
		this.setVisible(true);
		jMainFrame.getContentPane().add(this);
		this.repaint();
	}

	
	public void paintComponent(Graphics g){

		g.drawImage(jMainFrame.everyImage.IMG_ABOUT_MEMBER[now].getImage(), points[0].x, points[1].y,null);
		g.drawImage(jMainFrame.everyImage.IMG_ABOUT_MEMBER[next].getImage(), points[1].x, points[1].y,null);
	
	}
	
	public void setDirection(boolean i){
		isRight = i;
	}
	
	public void run(){
		if(isRight){
			next++;
			points[1].x = 400;
			while(points[1].x>0){
				points[1].x-=10;
				points[0].x-=10;
				this.repaint();
				jMainFrame.getContentPane().repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			points[0].x = 0;		
			now = next;
		}else{
			next--;
			points[1].x = -400;
			while(points[1].x<0){
				points[1].x+=10;
				points[0].x+=10;
				this.repaint();
				jMainFrame.getContentPane().repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			points[0].x = 0;
			now = next;
		}
	}
	
	

}