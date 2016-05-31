package game;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Block extends JPanel{
	
	private static final long serialVersionUID = 1L;
	//每一个格子都是一个按钮
	private boolean occupied = false;
	boolean isExample = false;
	public int family = 2;
	private int rank = 0;
	private boolean isHome;
	private Point location;//(410+x*34-y*26,196+x*10+y*23)
	private Control control;
	
	private ImageIcon[][] IMG_BLOCK = new ImageIcon[][]{{new ImageIcon("graphics/game/color1-1.png"),new ImageIcon("graphics/game/color1-2.png"),new ImageIcon("graphics/game/color1-3.png")},
		{new ImageIcon("graphics/game/color2-1.png"),new ImageIcon("graphics/game/color2-2.png"),new ImageIcon("graphics/game/color2-3.png")},{new ImageIcon("graphics/game/color3-1.png")}};
	private ImageIcon IMG_WAITBLOCK = new ImageIcon("graphics/game/blockwait.png");
	
	private ImageIcon IMG_HOME = new ImageIcon("graphics/game/home.png");
	
	
	
	public Block(int x, int y){
		location = new Point(x,y);
		setBounds(getRealPoint().x,getRealPoint().y,IMG_BLOCK[0][0].getIconWidth(),IMG_BLOCK[0][0].getIconHeight());
		if((x==0||x==14)&&(y==2||y==7||y==12)){
			this.isHome = true;
		}else{
			isHome = false;
		}
		
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
        		family = 2;
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
        		family = 2;
        		break;
        	}
    	}//确定初始位
		this.setVisible(true);
	}
	
	void setExample(boolean a){
		if(!isHome){
			isExample = a;
		}
	}
	
	
	public void paintComponent(Graphics g){
		if(isHome){
			g.drawImage(IMG_HOME.getImage(), 0, 0, null);
		}else if(isExample){
			g.drawImage(IMG_WAITBLOCK.getImage(), 0, 0, null);
		}else{
			g.drawImage(IMG_BLOCK[family][rank].getImage(), 0, 0, null);
		}
	}
	
	public Point getRealPoint(){
		Point real = new Point(410+34*location.x-26*location.y,196+location.x*10+location.y*23);
		return real;
	}
	
	void setControl(Control control){
    	this.control = control;
    }
	
	
	public void hitPerson(int family){
		for(int i = 0;i<2;i++){
			for(int j=0;j<3;j++){
				Person person = control.people[i][j];
				if(!person.isHide()&&person.family!=family&&person.location.x==location.x&&person.location.y==location.y){
					person.stop();
					person.hit();
				}
			}
		}
	}//人站在上面，但是不一定已经占领
	
	public boolean hasPerson(){//隐身的人不被发现
		for(int i = 0;i<2;i++){
			for(int j=0;j<3;j++){
				Person person = control.people[i][j];
				if(!person.isHide()&&person.location.x==location.x&&person.location.y==location.y){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasPerson(int family){//隐身的人不被发现
		for(int i = 0;i<2;i++){
			for(int j=0;j<3;j++){
				Person person = control.people[i][j];
				if(person.family!=family&&!person.isHide()&&person.location.x==location.x&&person.location.y==location.y){
					return true;
				}
			}
		}
		return false;
	}
	
	public void setHome(int family){
		isHome = true;
		occupied = true;
		this.family = family;
	}

	public boolean isOccupied(){
		return occupied;
	}
	
	public int notOccupied(int family){
		if(occupied&&this.family == family){
			return 0;
		}else{
			return 1;
		}
	}
	
	public void setOccupied(int family, int rank){
		if(!isHome){
		    occupied = true;
			this.family = family;
			this.rank = rank;
			for(int i = 0;i<2;i++){
				for(int j=0;j<3;j++){
					Person person = control.people[i][j];
					if(person.family!=family&&person.location.x==location.x&&person.location.y==location.y){
						person.hit();
					}
				}
			}
		}
	}//被占领，但是不一定有人
	
	


}