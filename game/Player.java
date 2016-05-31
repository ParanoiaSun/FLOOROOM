package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import game.Block;

public class Player extends JPanel{
	
	private static final long serialVersionUID = 1L;
	//String name;
	public int family;//0�ż�ͥ�����½�����,0�ż���СŮ��
    public int rank;//�ְ�Ϊ0������Ϊ1������Ϊ2
    
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
    protected int activity = 7;//����
    protected Point homeLoc;//012Ϊ0�ż�ͥ��Ӫ��
    protected boolean hidden = false;
    protected boolean awake = true;
    int direction ;
    protected RoundControl control;
    protected int hitTime = 0;
    protected int preference;
    
    
    Player(int family, int rank){
    	this.family = family;
    	this.rank = rank;
    	direction = family+2;
    }
    
    public void setPerfer(int i){
    	preference = i;
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
    
    void setControl(RoundControl control){
    	this.control = control;
    }
    
    void reactive(){
    	activity = 7;
    }
    
    
    public void paintComponent(Graphics g){
    	setLocation(getRealPoint());//�ƶ��������
    	

		if(hidden){
			//����ͼ��
			switch (rank){
			case 0:
				g.drawImage(IMG_FATHER[preference][direction+4].getImage(), 0, 0, null);
				break;
			case 1:
				g.drawImage(IMG_MOTHER[preference][direction+4].getImage(), 0, 0, null);
				break;
			case 2:
				g.drawImage(IMG_CHILD[preference][direction+4].getImage(), 0, 0, null);
				break;
			}
		}else{
			switch (rank){
			case 0:
				g.drawImage(IMG_FATHER[preference][direction].getImage(), 0, 0, null);
				break;
			case 1:
				g.drawImage(IMG_MOTHER[preference][direction].getImage(), 0, 0, null);
				break;
			case 2:
				g.drawImage(IMG_CHILD[preference][direction].getImage(), 0, 0, null);
				break;
			}			
		}
	
	}
    
    protected void setHome(int x,int y){
		location = new Point(x,y);
		homeLoc = new Point(location);
    	setBounds(getRealPoint().x,getRealPoint().y,47,74);
    	this.setVisible(true);
    }
    
    
    public void disturb(int direction){//ɧ��
		this.direction = direction;
		
		switch(direction){
    	case 0:  //��������
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
    	    
    	         
    	    
    		
    	case 1:  //��������
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
    		
    	case 2: // ��������
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
    		
    	case 3:  //��������
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
    
    
    
    public int getActivity(){   //��������ֵ
    	return activity;
    }
    
    void hit(){   //character(i,j).hit();
    	hitTime++;
    	hidden = false;
    	location.x = homeLoc.x;
    	location.y = homeLoc.y;
    	//�����лؼ�
    }
    

	public void occupy(int direction){//direction 0 1 2 3�ֱ������������  ��ͬ��ɫ��ռ�췽ʽ��ͬ  �ְ�Ϊ0������Ϊ1������Ϊ2
		this.direction = direction;
    	switch(direction){
    	case 0:  //��������
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
    	    
    	         
    	    
    		
    	case 1:  //��������
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
    		
    	case 2: // ��������
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
    		
    	case 3:  //��������
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
    	activity -= 4;     //ռ������4������
    	
    }

	
	public void setExample(int direction,boolean a){//direction 0 1 2 3�ֱ������������  ��ͬ��ɫ��ռ�췽ʽ��ͬ  �ְ�Ϊ0������Ϊ1������Ϊ2
		this.direction = direction;
    	switch(direction){
    	case 0:  //��������
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
    	    
    	         
    	    
    		
    	case 1:  //��������
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
    		
    	case 2: // ��������
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
    		
    	case 3:  //��������
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
	protected OddBlock Block(int i, int j) {
		if(i>=0&&i<15&&j>=0&&j<15&&control.blocks[i][j].isExist)
			return control.blocks[i][j];
		else{
			return control.blocks[homeLoc.x][homeLoc.y];
		}
	}

    
    void stop(){
    	awake = false;
    }//ֹͣʱ�������״̬
    
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
    
    public void changeState(){  //��������������������
    	hidden = !hidden;
    	activity -= 1;
    }

    
    public void move(int direction){
    	this.direction = direction;
    	switch(direction){//direction 0 1 2 3�ֱ������������
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
