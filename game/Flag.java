package game;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Flag extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon[][] IMG_FLAG = new ImageIcon[][]{{new ImageIcon("graphics/game/flag1.png"),new ImageIcon("graphics/game/flag2.png"),new ImageIcon("graphics/game/flag3.png")},
		{new ImageIcon("graphics/game/flag4.png"),new ImageIcon("graphics/game/flag5.png"),new ImageIcon("graphics/game/flag6.png")}};

	private Point location;
	private int family;
	private int rank;
	public Flag(int x,int y){
		location = new Point(x,y);
		setBounds(getRealPoint().x,getRealPoint().y,IMG_FLAG[0][0].getIconWidth(),IMG_FLAG[0][0].getIconHeight());
		
		if(x == 0){
			family = 1;
    		switch(y){
        	case 2:
        		rank = 0;
        		break;
        	case 7:
        		rank = 1;
        		break;
        	case 12:
        		rank = 2;
        		break;
        	default:
        		break;
        	}
    	}else if(x==14){
    		family = 0;
    		switch(y){
        	case 2:
        		rank = 2;
        		break;
        	case 7:
        		rank = 1;
        		break;
        	case 12:
        		rank = 0;
        		break;
        	default:
        		break;
        	}
    	}
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(IMG_FLAG[family][rank].getImage(), 0, 0, null);
	}
	
	public Point getRealPoint(){
		Point real = new Point(428+34*location.x-26*location.y,170+location.x*10+location.y*23);
		return real;
	}
}
