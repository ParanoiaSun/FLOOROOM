package game;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SingleFlag extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon[][] IMG_FLAG = new ImageIcon[][]{{new ImageIcon("graphics/game/flag1.png"),new ImageIcon("graphics/game/flag2.png"),new ImageIcon("graphics/game/flag3.png")},
		{new ImageIcon("graphics/game/flag4.png"),new ImageIcon("graphics/game/flag5.png"),new ImageIcon("graphics/game/flag6.png")}};

	private Point location;
	private int family;
	private int rank;
	public SingleFlag(int x,int y){
		location = new Point(x,y);
		setBounds(getRealPoint().x,getRealPoint().y,IMG_FLAG[0][0].getIconWidth(),IMG_FLAG[0][0].getIconHeight());

	}
	
	public void setPerfer(int perfer,int rank){
		family = perfer;
		this.rank = rank;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(IMG_FLAG[family][rank].getImage(), 0, 0, null);
	}
	
	public Point getRealPoint(){
		Point real = new Point(428+34*location.x-26*location.y,170+location.x*10+location.y*23);
		return real;
	}
}

