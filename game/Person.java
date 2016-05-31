package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import game.Block;

public class Person extends JPanel{
	
	private static final long serialVersionUID = 1L;
	//String name;
	public int family;//0号家庭于右下角先走,0号家是小女孩
    public int rank;//爸爸为0，妈妈为1，宝宝为2
    
    protected ImageIcon[][] IMG_FATHER = new ImageIcon[][]{{new ImageIcon("graphics/character/10.png"),new ImageIcon("graphics/character/11.png"),new ImageIcon("graphics/character/12.png"),new ImageIcon("graphics/character/13.png"),
    	new ImageIcon("graphics/character/15.png"),new ImageIcon("graphics/character/16.png"),new ImageIcon("graphics/character/17.png"),new ImageIcon("graphics/character/18.png")},
    	{new ImageIcon("graphics/character/40.png"),new ImageIcon("graphics/character/41.png"),new ImageIcon("graphics/character/42.png"),new ImageIcon("graphics/character/43.png"),
    		new ImageIcon("graphics/character/45.png"),new ImageIcon("graphics/character/46.png"),new ImageIcon("graphics/character/47.png"),new ImageIcon("graphics/character/48.png")}};
    	
    protected ImageIcon[][] IMG_MOTHER = new ImageIcon[][]{{new ImageIcon("graphics/character/20.png"),new ImageIcon("graphics/character/21.png"),new ImageIcon("graphics/character/22.png"),new ImageIcon("graphics/character/23.png"),
		new ImageIcon("graphics/character/25.png"),new ImageIcon("graphics/character/26.png"),new ImageIcon("graphics/character/27.png"),new ImageIcon("graphics/character/28.png")},
		{new ImageIcon("graphics/character/50.png"),new ImageIcon("graphics/character/51.png"),new ImageIcon("graphics/character/52.png"),new ImageIcon("graphics/character/53.png"),
			new ImageIcon("graphics/character/55.png"),new ImageIcon("graphics/character/56.png"),new ImageIcon("graphics/character/57.png"),new ImageIcon("graphics/character/58.png")}};
		
	protected ImageIcon[][] IMG_CHILD = new ImageIcon[][]{{new ImageIcon("graphics/character/30.png"),new ImageIcon("graphics/character/31.png"),new ImageIcon("graphics/character/32.png"),new ImageIcon("graphics/character/33.png"),
		new ImageIcon("graphics/character/35.png"),new ImageIcon("graphics/character/36.png"),new ImageIcon("graphics/character/37.png"),new ImageIcon("graphics/character/38.png")},
		{new ImageIcon("graphics/character/60.png"),new ImageIcon("graphics/character/61.png"),new ImageIcon("graphics/character/62.png"),new ImageIcon("graphics/character/63.png"),
			new ImageIcon("graphics/character/65.png"),new ImageIcon("graphics/character/66.png"),new ImageIcon("graphics/character/67.png"),new ImageIcon("graphics/character/68.png")}};
    
	
    public Point location = new Point(0,0);//(420+x*34-y*23,125+x*10+y*20)
    protected int activity = 7;//体力
    protected Point homeLoc;//012为0号家庭大本营，
    protected boolean hidden = false;
    protected boolean awake = true;
    int direction ;
    protected Control control;
    protected int hitTime = 0;
    
    
    Person(int family, int rank){
    	this.family = family;
    	this.rank = rank;
    	direction = family+2;
    	this.initial();
    	setBounds(getRealPoint().x,getRealPoint().y,47,74);
    	this.setVisible(true);
    }
    
    public Point getRealPoint(){
		Point real = new Point(420+34*location.x-26*location.y,145+location.x*10+location.y*23);
		return real;
	}
    
    public int getHitTime(){
    	return hitTime;
    }
    
    public boolean isHide(){
    	return hidden;
    }
    
    void setControl(Control control){
    	this.control = control;
    }
    
    void reactive(){
    	activity = 7;
    }
    
    
    public void paintComponent(Graphics g){
    	setLocation(getRealPoint());//移动后的坐标
    	

		if(hidden){
			//隐身图案
			switch (rank){
			case 0:
				g.drawImage(IMG_FATHER[family][direction+4].getImage(), 0, 0, null);
				break;
			case 1:
				g.drawImage(IMG_MOTHER[family][direction+4].getImage(), 0, 0, null);
				break;
			case 2:
				g.drawImage(IMG_CHILD[family][direction+4].getImage(), 0, 0, null);
				break;
			}
		}else{
			switch (rank){
			case 0:
				g.drawImage(IMG_FATHER[family][direction].getImage(), 0, 0, null);
				break;
			case 1:
				g.drawImage(IMG_MOTHER[family][direction].getImage(), 0, 0, null);
				break;
			case 2:
				g.drawImage(IMG_CHILD[family][direction].getImage(), 0, 0, null);
				break;
			}			
		}
	
	}
    
    
    protected void initial(){
    		
    	if(family == 0){
    		location.x = 14;
    		switch(rank){
        	case 0:
        		location.y = 12;
        		break;
        	case 1:
        		location.y = 7;
        		break;
        	case 2:
        		location.y = 2;
        		break;
        	default:
        		break;
        	}
    	}else{
    		location.x = 0;
    		switch(rank){
        	case 0:
        		location.y = 2;
        		break;
        	case 1:
        		location.y = 7;
        		break;
        	case 2:
        		location.y = 12;
        		break;
        	default:
        		break;
        	}
    	}//确定初始位
    	
    	homeLoc = new Point(location);
    }
    
    public void disturb(int direction){//骚扰
		this.direction = direction;
		
		switch(direction){
    	case 0:  //方向向上
    	    if(rank==0){
    	    	Block(location.x-1,location.y-1).hitPerson(family);
    	    	Block(location.x-1,location.y).hitPerson(family);
    	    	Block(location.x-1,location.y+1).hitPerson(family);
    	    	Block(location.x,location.y-1).hitPerson(family);
    	    	Block(location.x+1,location.y).hitPerson(family);
    	    	Block(location.x+1,location.y-1).hitPerson(family);
    	    	Block(location.x+1,location.y+1).hitPerson(family);
    	    }else
    	    if(rank==1){
    	    	Block(location.x,location.y-2).hitPerson(family);
    	    	Block(location.x,location.y-1).hitPerson(family);
    	    	Block(location.x-1,location.y-1).hitPerson(family);
    	    	Block(location.x-1,location.y).hitPerson(family);
    	    	Block(location.x-2,location.y).hitPerson(family);
    	    }else
    	    if(rank==2){
    	    	for(int count=1;count<=4;count++){
    	    		Block(location.x,location.y-count).hitPerson(family);
    	    	}
    	    }
    	    	
    	break;    
    	    
    	         
    	    
    		
    	case 1:  //方向向下
    	    if(rank==0){
    	    	Block(location.x-1,location.y-1).hitPerson(family);
    	    	Block(location.x-1,location.y).hitPerson(family);
    	    	Block(location.x-1,location.y+1).hitPerson(family);
    	    	Block(location.x,location.y+1).hitPerson(family);
    	    	Block(location.x+1,location.y).hitPerson(family);
    	    	Block(location.x+1,location.y-1).hitPerson(family);
    	    	Block(location.x+1,location.y+1).hitPerson(family);
    	    }else
    	    if(rank==1){
    	    	Block(location.x,location.y+2).hitPerson(family);
    	    	Block(location.x,location.y+1).hitPerson(family);
    	    	Block(location.x+1,location.y+1).hitPerson(family);
    	    	Block(location.x+1,location.y).hitPerson(family);
    	    	Block(location.x+2,location.y).hitPerson(family);
    	    }else
    	    if(rank==2){
    	    	for(int count=1;count<=4;count++){
    	    		Block(location.x,location.y+count).hitPerson(family);
    	    	}
    	    }
    	break;
    		
    	case 2: // 方向向左
    		 if(rank==0){
    			Block(location.x-1,location.y-1).hitPerson(family);
     	    	Block(location.x-1,location.y).hitPerson(family);
     	    	Block(location.x-1,location.y+1).hitPerson(family);
     	    	Block(location.x,location.y-1).hitPerson(family);
     	    	Block(location.x,location.y+1).hitPerson(family);
     	    	Block(location.x+1,location.y-1).hitPerson(family);
     	    	Block(location.x+1,location.y+1).hitPerson(family);
     	    }else
     	    if(rank==1){
     	    	Block(location.x,location.y+2).hitPerson(family);
    	    	Block(location.x,location.y+1).hitPerson(family);
    	    	Block(location.x-1,location.y+1).hitPerson(family);
    	    	Block(location.x-1,location.y).hitPerson(family);
    	    	Block(location.x-2,location.y).hitPerson(family);
     	    }else
     	    if(rank==2){	
     	    	for(int count=1;count<=4;count++){
    	    		Block(location.x-count,location.y).hitPerson(family);
    	    	}
     	    }
     	break;
    		
    	case 3:  //方向向右
    		 if(rank==0){
    			Block(location.x-1,location.y-1).hitPerson(family);
      	    	Block(location.x-1,location.y+1).hitPerson(family);
      	    	Block(location.x,location.y-1).hitPerson(family);
      	    	Block(location.x,location.y+1).hitPerson(family);
      	    	Block(location.x+1,location.y).hitPerson(family);
      	    	Block(location.x+1,location.y-1).hitPerson(family);
      	    	Block(location.x+1,location.y+1).hitPerson(family);
     	    	
     	    }else
     	    if(rank==1){
     	    	Block(location.x,location.y-2).hitPerson(family);
    	    	Block(location.x,location.y-1).hitPerson(family);
    	    	Block(location.x+1,location.y-1).hitPerson(family);
    	    	Block(location.x+1,location.y).hitPerson(family);
    	    	Block(location.x+2,location.y).hitPerson(family);
     	    	
     	    }else
     	    if(rank==2){
     	    	for(int count=1;count<=4;count++){
    	    		Block(location.x+count,location.y).hitPerson(family);
    	    	}
     	    }
     	break;
    		
    	}
    	activity -= 3;
    	
    }
    
    
    
    public int getActivity(){   //返回体力值
    	return activity;
    }
    
    void hit(){   //character(i,j).hit();
    	hitTime++;
    	hidden = false;
    	location.x = homeLoc.x;
    	location.y = homeLoc.y;
    	//被打中回家
    }
    

	public void occupy(int direction){//direction 0 1 2 3分别代表上下左右  不同角色的占领方式不同  爸爸为0，妈妈为1，宝宝为2
		this.direction = direction;
    	switch(direction){
    	case 0:  //方向向上
    	    if(rank==0){
    	    	Block(location.x-1,location.y-1).setOccupied(family,rank);
    	    	Block(location.x-1,location.y).setOccupied(family,rank);
    	    	Block(location.x-1,location.y+1).setOccupied(family,rank);
    	    	Block(location.x,location.y-1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y).setOccupied(family,rank);
    	    	Block(location.x+1,location.y-1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y+1).setOccupied(family,rank);
    	    }else
    	    if(rank==1){
    	    	Block(location.x,location.y-2).setOccupied(family,rank);
    	    	Block(location.x,location.y-1).setOccupied(family,rank);
    	    	Block(location.x-1,location.y-1).setOccupied(family,rank);
    	    	Block(location.x-1,location.y).setOccupied(family,rank);
    	    	Block(location.x-2,location.y).setOccupied(family,rank);
    	    }else
    	    if(rank==2){
    	    	for(int count=1;count<=4;count++){
    	    		Block(location.x,location.y-count).setOccupied(family,rank);
    	    	}
    	    }
    	    	
    	break;    
    	    
    	         
    	    
    		
    	case 1:  //方向向下
    	    if(rank==0){
    	    	Block(location.x-1,location.y-1).setOccupied(family,rank);
    	    	Block(location.x-1,location.y).setOccupied(family,rank);
    	    	Block(location.x-1,location.y+1).setOccupied(family,rank);
    	    	Block(location.x,location.y+1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y).setOccupied(family,rank);
    	    	Block(location.x+1,location.y-1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y+1).setOccupied(family,rank);
    	    }else
    	    if(rank==1){
    	    	Block(location.x,location.y+2).setOccupied(family,rank);
    	    	Block(location.x,location.y+1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y+1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y).setOccupied(family,rank);
    	    	Block(location.x+2,location.y).setOccupied(family,rank);
    	    }else
    	    if(rank==2){
    	    	for(int count=1;count<=4;count++){
    	    		Block(location.x,location.y+count).setOccupied(family,rank);
    	    	}
    	    }
    	break;
    		
    	case 2: // 方向向左
    		 if(rank==0){
    			Block(location.x-1,location.y-1).setOccupied(family,rank);
     	    	Block(location.x-1,location.y).setOccupied(family,rank);
     	    	Block(location.x-1,location.y+1).setOccupied(family,rank);
     	    	Block(location.x,location.y-1).setOccupied(family,rank);
     	    	Block(location.x,location.y+1).setOccupied(family,rank);
     	    	Block(location.x+1,location.y-1).setOccupied(family,rank);
     	    	Block(location.x+1,location.y+1).setOccupied(family,rank);
     	    }else
     	    if(rank==1){
     	    	Block(location.x,location.y+2).setOccupied(family,rank);
    	    	Block(location.x,location.y+1).setOccupied(family,rank);
    	    	Block(location.x-1,location.y+1).setOccupied(family,rank);
    	    	Block(location.x-1,location.y).setOccupied(family,rank);
    	    	Block(location.x-2,location.y).setOccupied(family,rank);
     	    }else
     	    if(rank==2){	
     	    	for(int count=1;count<=4;count++){
    	    		Block(location.x-count,location.y).setOccupied(family,rank);
    	    	}
     	    }
     	break;
    		
    	case 3:  //方向向右
    		 if(rank==0){
    			Block(location.x-1,location.y-1).setOccupied(family,rank);
      	    	Block(location.x-1,location.y+1).setOccupied(family,rank);
      	    	Block(location.x,location.y-1).setOccupied(family,rank);
      	    	Block(location.x,location.y+1).setOccupied(family,rank);
      	    	Block(location.x+1,location.y).setOccupied(family,rank);
      	    	Block(location.x+1,location.y-1).setOccupied(family,rank);
      	    	Block(location.x+1,location.y+1).setOccupied(family,rank);
     	    	
     	    }else
     	    if(rank==1){
     	    	Block(location.x,location.y-2).setOccupied(family,rank);
    	    	Block(location.x,location.y-1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y-1).setOccupied(family,rank);
    	    	Block(location.x+1,location.y).setOccupied(family,rank);
    	    	Block(location.x+2,location.y).setOccupied(family,rank);
     	    	
     	    }else
     	    if(rank==2){
     	    	for(int count=1;count<=4;count++){
    	    		Block(location.x+count,location.y).setOccupied(family,rank);
    	    	}
     	    }
     	break;
    		
    	}
    	activity -= 4;     //占领消耗4点体力
    	
    }

	
	public void setExample(int direction,boolean a){//direction 0 1 2 3分别代表上下左右  不同角色的占领方式不同  爸爸为0，妈妈为1，宝宝为2
		this.direction = direction;
    	switch(direction){
    	case 0:  //方向向上
    	    if(rank==0){
    	    	Block(location.x-1,location.y-1).setExample(a);
    	    	Block(location.x-1,location.y).setExample(a);
    	    	Block(location.x-1,location.y+1).setExample(a);
    	    	Block(location.x,location.y-1).setExample(a);
    	    	Block(location.x+1,location.y).setExample(a);
    	    	Block(location.x+1,location.y-1).setExample(a);
    	    	Block(location.x+1,location.y+1).setExample(a);
    	    }else
    	    if(rank==1){
    	    	Block(location.x,location.y-2).setExample(a);
    	    	Block(location.x,location.y-1).setExample(a);
    	    	Block(location.x-1,location.y-1).setExample(a);
    	    	Block(location.x-1,location.y).setExample(a);
    	    	Block(location.x-2,location.y).setExample(a);
    	    }else
    	    if(rank==2){
    	    	for(int count=1;count<=4;count++){
    	    		Block(location.x,location.y-count).setExample(a);
    	    	}
    	    }
    	    	
    	break;    
    	    
    	         
    	    
    		
    	case 1:  //方向向下
    	    if(rank==0){
    	    	Block(location.x-1,location.y-1).setExample(a);
    	    	Block(location.x-1,location.y).setExample(a);
    	    	Block(location.x-1,location.y+1).setExample(a);
    	    	Block(location.x,location.y+1).setExample(a);
    	    	Block(location.x+1,location.y).setExample(a);
    	    	Block(location.x+1,location.y-1).setExample(a);
    	    	Block(location.x+1,location.y+1).setExample(a);
    	    }else
    	    if(rank==1){
    	    	Block(location.x,location.y+2).setExample(a);
    	    	Block(location.x,location.y+1).setExample(a);
    	    	Block(location.x+1,location.y+1).setExample(a);
    	    	Block(location.x+1,location.y).setExample(a);
    	    	Block(location.x+2,location.y).setExample(a);
    	    }else
    	    if(rank==2){
    	    	for(int count=1;count<=4;count++){
    	    		Block(location.x,location.y+count).setExample(a);
    	    	}
    	    }
    	break;
    		
    	case 2: // 方向向左
    		 if(rank==0){
    			Block(location.x-1,location.y-1).setExample(a);
     	    	Block(location.x-1,location.y).setExample(a);
     	    	Block(location.x-1,location.y+1).setExample(a);
     	    	Block(location.x,location.y-1).setExample(a);
     	    	Block(location.x,location.y+1).setExample(a);
     	    	Block(location.x+1,location.y-1).setExample(a);
     	    	Block(location.x+1,location.y+1).setExample(a);
     	    }else
     	    if(rank==1){
     	    	Block(location.x,location.y+2).setExample(a);
    	    	Block(location.x,location.y+1).setExample(a);
    	    	Block(location.x-1,location.y+1).setExample(a);
    	    	Block(location.x-1,location.y).setExample(a);
    	    	Block(location.x-2,location.y).setExample(a);
     	    }else
     	    if(rank==2){	
     	    	for(int count=1;count<=4;count++){
    	    		Block(location.x-count,location.y).setExample(a);
    	    	}
     	    }
     	break;
    		
    	case 3:  //方向向右
    		 if(rank==0){
    			Block(location.x-1,location.y-1).setExample(a);
      	    	Block(location.x-1,location.y+1).setExample(a);
      	    	Block(location.x,location.y-1).setExample(a);
      	    	Block(location.x,location.y+1).setExample(a);
      	    	Block(location.x+1,location.y).setExample(a);
      	    	Block(location.x+1,location.y-1).setExample(a);
      	    	Block(location.x+1,location.y+1).setExample(a);
     	    	
     	    }else
     	    if(rank==1){
     	    	Block(location.x,location.y-2).setExample(a);
    	    	Block(location.x,location.y-1).setExample(a);
    	    	Block(location.x+1,location.y-1).setExample(a);
    	    	Block(location.x+1,location.y).setExample(a);
    	    	Block(location.x+2,location.y).setExample(a);
     	    	
     	    }else
     	    if(rank==2){
     	    	for(int count=1;count<=4;count++){
    	    		Block(location.x+count,location.y).setExample(a);
    	    	}
     	    }
     	break;
    		
    	}
    	
    }


      //undone
	protected Block Block(int i, int j) {
		if(i>=0&&i<15&&j>=0&&j<15)
			return control.blocks[i][j];
		else{
			return control.blocks[homeLoc.x][homeLoc.y];
		}
	}

    
    void stop(){
    	awake = false;
    }//停止时进入假死状态
    
    boolean isAwake(){
    	return awake;
    }
    
    public boolean inDanger(){
    	if(control.blocks[location.x][location.y].family==1-family){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    void setAwake(boolean a){
    	awake = a;
    }
    
    public void changeState(){  //隐身变成现身，现身变成隐身
    	hidden = !hidden;
    	activity -= 1;
    	this.repaint();
    }

    
    public void move(int direction){
    	this.direction = direction;
    	switch(direction){//direction 0 1 2 3分别代表上下左右
    	case 0:
    		location.y -= 1;
			activity -=2;
    		break;
    	case 1:
    		location.y += 1;
			activity -=2;
    		
    		break;
    	case 2:
    		location.x -=1;
			activity -=2;
    		break;
    	case 3:
    		location.x +=1;
			activity -=2;
    		break;
    		
    	}
    }
    

}
