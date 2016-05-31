package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import game.Block;

public class Ai extends Player{
	
	//String name;
	
    private static final long serialVersionUID = 1L;

    
    
    Ai(int family, int rank){
    	super(family,rank);
    	
    }
    
    @Override
    public void paintComponent(Graphics g){
    	setLocation(getRealPoint());//移动后的坐标
    	

		if(hidden){
			
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
    
    @Override
    public void move(int i){
    	
    	switch(i){//direction 0 1 2 3分别代表上下左右
    	case 0:
    		if(control.blocks[location.x][location.y-1].isExist){
    			this.direction = i;	
    			location.y -= 1;
    			activity -=2;
    			return;
    		}

    		break;
    	case 1:
    		if(control.blocks[location.x][location.y+1].isExist){
    			this.direction = i;	
    			location.y += 1;
    			activity -=2;
    			return;
    			
    		}

    		break;
    	case 2:
    		if(control.blocks[location.x-1][location.y].isExist){
    			this.direction = i;	
    			location.x -=1;
    			activity -=2;
    			return;
    		}
    		break;
    	case 3:
    		if(control.blocks[location.x+1][location.y].isExist){
    			this.direction = i;	
    			location.x +=1;
    			activity -=2;
    			return;
    		}	
    		break;
    		
    	}
    	move((int)(Math.random()*4));
    	
    }

   
    public void execute(){
    	int i=(int)(Math.random()*4);
    	if(hidden){
    		if(activity==1){
        		finish();
        	}else if(inDanger()){
        		move((int)Math.random()*8%4);
        	}else if((i = hasEnemy())!=4){
        		move(1-2*(i%2)+i);//往敌人反方向跑
        	}else {
        		changeState();
        	}
    	}else{
    		i = hasEnemy();
    		if(i!=4){//有敌人
    			if(activity>3){
    				occupy(i);
    			}else if(activity==3){
    				disturb(i);
    				finish();
    			}else if(activity==2){
    				move(1-2*(i%2)+i);
    				finish();
    			}else if(activity==1){
    				changeState();
    				finish();
    			}		
    		}else if((i=toOccupy())!=4){//能占领的位置比较多
    			if(activity>3){
    				occupy(i);
    			}else if(activity>0){
    				changeState();
    				finish();
    			}
    		}else if(activity>1){//去往能占领比较多的地方，或者去往敌人的方向
    			i=toMove();
    			move(i);
    		}else{
    			changeState();
    			finish();
    		}
    	}
    	
    }
    
    void finish(){
    	activity = 0;
    }
    
    int toMove(){
    	int[] count = new int[]{0,0,0,0};
    	int direction = 0;
    	switch (rank){
    	case 0://爸爸主要任务就是占领
    		if(Block(location.x,location.y-1).isExist){
    			count[0] = Block(location.x-1,location.y-2).notOccupied(family)+
    		    		Block(location.x,location.y-2).notOccupied(family)+
    		    		Block(location.x+1,location.y-2).notOccupied(family)+
    		    		Block(location.x-1,location.y-1).notOccupied(family)+
    		    		Block(location.x,location.y-1).notOccupied(family)+
    		    		Block(location.x+1,location.y-1).notOccupied(family)+
    		    		Block(location.x-1,location.y).notOccupied(family)+
    		    		Block(location.x+1,location.y).notOccupied(family);
    		}
    		
    		if(Block(location.x,location.y+1).isExist){
    			count[1] = Block(location.x-1,location.y-1).notOccupied(family)+
    		    		Block(location.x,location.y-1).notOccupied(family)+
    	    	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	    	    	Block(location.x-1,location.y-2).notOccupied(family)+
    	    	    	Block(location.x,location.y-2).notOccupied(family)+
    	    	    	Block(location.x+1,location.y-2).notOccupied(family)+
    	    	    	Block(location.x+1,location.y).notOccupied(family)+
    	    	    	Block(location.x-1,location.y).notOccupied(family);
    		}
    		
    		if(Block(location.x-1,location.y).isExist){
    			count[2] = Block(location.x-1,location.y-1).notOccupied(family)+
    		 	    	Block(location.x-1,location.y).notOccupied(family)+
    		 	    	Block(location.x-1,location.y+1).notOccupied(family)+
    		 	    	Block(location.x,location.y-1).notOccupied(family)+
    		 	    	Block(location.x,location.y+1).notOccupied(family)+
    		 	    	Block(location.x-2,location.y-1).notOccupied(family)+
    		 	    	Block(location.x-2,location.y).notOccupied(family)+
    		 	    	Block(location.x-2,location.y+1).notOccupied(family);
    		}
    		
    		if(Block(location.x+1,location.y).isExist){
    			count[3] = Block(location.x+2,location.y-1).notOccupied(family)+
    	      	    	Block(location.x+2,location.y+1).notOccupied(family)+
    	      	    	Block(location.x+2,location.y).notOccupied(family)+
    	      	    	Block(location.x,location.y-1).notOccupied(family)+
    	      	    	Block(location.x,location.y+1).notOccupied(family)+
    	      	    	Block(location.x+1,location.y).notOccupied(family)+
    	      	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	      	    	Block(location.x+1,location.y+1).notOccupied(family);
    		}
    		
    		for(int i =0;i<4;i++){
    	    	if(count[direction]<count[i]){
    	    		direction = i;
    	    	}
    	    }
    		return direction;
    		
    	case 2://孩子找茬
    		if(Block(location.x,location.y-1).isExist){
    			for(int i = 1;i<=4;i++){
    				if(Block(location.x+i,location.y-1).hasPerson(family)||
    					Block(location.x-i,location.y-1).hasPerson(family)||
    					Block(location.x,location.y-1+i).hasPerson(family)||
    					Block(location.x,location.y-1-i).hasPerson(family)){
    					return 0;
    				}
    			}
    		}
    		
    		if(Block(location.x,location.y+1).isExist){
    			for(int i = 1;i<=4;i++){
    				if(Block(location.x+i,location.y+1).hasPerson(family)||
    					Block(location.x-i,location.y+1).hasPerson(family)||
    					Block(location.x,location.y+1+i).hasPerson(family)||
    					Block(location.x,location.y+1-i).hasPerson(family)){
    					return 1;
    				}
    			}
    		}
    		
    		if(Block(location.x-1,location.y).isExist){
    			for(int i = 1;i<=4;i++){
    				if(Block(location.x-1+i,location.y).hasPerson(family)||
    					Block(location.x-1-i,location.y).hasPerson(family)||
    					Block(location.x-1,location.y+i).hasPerson(family)||
    					Block(location.x-1,location.y-i).hasPerson(family)){
    					return 2;
    				}
    			}
    		}
    		
    		if(Block(location.x+1,location.y).isExist){
    			for(int i = 1;i<=4;i++){
    				if(Block(location.x-1+i,location.y).hasPerson(family)||
    					Block(location.x-1-i,location.y).hasPerson(family)||
    					Block(location.x-1,location.y+i).hasPerson(family)||
    					Block(location.x-1,location.y-i).hasPerson(family)){
    					return 3;
    				}
    			}
    		}
	    
    	case 1://妈妈主要占领
    		if(Block(location.x,location.y-1).isExist){
    			count[0] = Block(location.x-1,location.y-2).notOccupied(family)+
    		    		Block(location.x,location.y-2).notOccupied(family)+
    		    		Block(location.x+1,location.y-2).notOccupied(family)+
    		    		Block(location.x-1,location.y-1).notOccupied(family)+
    		    		Block(location.x,location.y-1).notOccupied(family)+
    		    		Block(location.x+1,location.y-1).notOccupied(family)+
    		    		Block(location.x-1,location.y).notOccupied(family)+
    		    		Block(location.x+1,location.y).notOccupied(family);
    		}
    		
    		if(Block(location.x,location.y+1).isExist){
    			count[1] = Block(location.x-1,location.y-1).notOccupied(family)+
    		    		Block(location.x,location.y-1).notOccupied(family)+
    	    	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	    	    	Block(location.x-1,location.y-2).notOccupied(family)+
    	    	    	Block(location.x,location.y-2).notOccupied(family)+
    	    	    	Block(location.x+1,location.y-2).notOccupied(family)+
    	    	    	Block(location.x+1,location.y).notOccupied(family)+
    	    	    	Block(location.x-1,location.y).notOccupied(family);
    		}
    		
    		if(Block(location.x-1,location.y).isExist){
    			count[2] = Block(location.x-1,location.y-1).notOccupied(family)+
    		 	    	Block(location.x-1,location.y).notOccupied(family)+
    		 	    	Block(location.x-1,location.y+1).notOccupied(family)+
    		 	    	Block(location.x,location.y-1).notOccupied(family)+
    		 	    	Block(location.x,location.y+1).notOccupied(family)+
    		 	    	Block(location.x-2,location.y-1).notOccupied(family)+
    		 	    	Block(location.x-2,location.y).notOccupied(family)+
    		 	    	Block(location.x-2,location.y+1).notOccupied(family);
    		}
    		
    		if(Block(location.x+1,location.y).isExist){
    			count[3] = Block(location.x+2,location.y-1).notOccupied(family)+
    	      	    	Block(location.x+2,location.y+1).notOccupied(family)+
    	      	    	Block(location.x+2,location.y).notOccupied(family)+
    	      	    	Block(location.x,location.y-1).notOccupied(family)+
    	      	    	Block(location.x,location.y+1).notOccupied(family)+
    	      	    	Block(location.x+1,location.y).notOccupied(family)+
    	      	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	      	    	Block(location.x+1,location.y+1).notOccupied(family);
    		}
    		
    		for(int i =0;i<4;i++){
    	    	if(count[direction]<count[i]){
    	    		direction = i;
    	    	}
    	    }
    		return direction;	
    	
    	}
    	return direction;
    	
    	
    }
    
    int toOccupy(){
    	int count[] = new int[4];
    	int direction = 0;
    	switch(rank){
    	case 0:  //father
    		
    	    count[0] = Block(location.x-1,location.y-1).notOccupied(family)+
    	    	Block(location.x-1,location.y).notOccupied(family)+
    	    	Block(location.x-1,location.y+1).notOccupied(family)+
    	    	Block(location.x,location.y-1).notOccupied(family)+
    	    	Block(location.x+1,location.y).notOccupied(family)+
    	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	    	Block(location.x+1,location.y+1).notOccupied(family);
    	    
    	    count[1] = Block(location.x-1,location.y-1).notOccupied(family)+
        	    	Block(location.x-1,location.y).notOccupied(family)+
        	    	Block(location.x-1,location.y+1).notOccupied(family)+
        	    	Block(location.x,location.y+1).notOccupied(family)+
        	    	Block(location.x+1,location.y).notOccupied(family)+
        	    	Block(location.x+1,location.y-1).notOccupied(family)+
        	    	Block(location.x+1,location.y+1).notOccupied(family);

    	    count[2] = Block(location.x-1,location.y-1).notOccupied(family)+
    	 	    	Block(location.x-1,location.y).notOccupied(family)+
    	 	    	Block(location.x-1,location.y+1).notOccupied(family)+
    	 	    	Block(location.x,location.y-1).notOccupied(family)+
    	 	    	Block(location.x,location.y+1).notOccupied(family)+
    	 	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	 	    	Block(location.x+1,location.y+1).notOccupied(family);

    	    count[3] = Block(location.x-1,location.y-1).notOccupied(family)+
          	    	Block(location.x-1,location.y+1).notOccupied(family)+
          	    	Block(location.x,location.y-1).notOccupied(family)+
          	    	Block(location.x,location.y+1).notOccupied(family)+
          	    	Block(location.x+1,location.y).notOccupied(family)+
          	    	Block(location.x+1,location.y-1).notOccupied(family)+
          	    	Block(location.x+1,location.y+1).notOccupied(family);
    	    for(int i =0;i<4;i++){
    	    	if(count[direction]<count[i]){
    	    		direction = i;
    	    	}
    	    }
    	    
    	    if(count[direction]>3){
    	    	return direction;
    	    }
    	    break;
    	    	           	       		
    	case 1:  //mother
    		
    	    count[0] = Block(location.x,location.y-2).notOccupied(family)+
	    	Block(location.x,location.y-1).notOccupied(family)+
	    	Block(location.x-1,location.y-1).notOccupied(family)+
	    	Block(location.x-1,location.y).notOccupied(family)+
	    	Block(location.x-2,location.y).notOccupied(family);

    	    
    	    count[1] = Block(location.x,location.y+2).notOccupied(family)+
    	    	Block(location.x,location.y+1).notOccupied(family)+
    	    	Block(location.x+1,location.y+1).notOccupied(family)+
    	    	Block(location.x+1,location.y).notOccupied(family)+
    	    	Block(location.x+2,location.y).notOccupied(family);

    	    
    	    count[2] = Block(location.x,location.y+2).notOccupied(family)+
	    	Block(location.x,location.y+1).notOccupied(family)+
	    	Block(location.x-1,location.y+1).notOccupied(family)+
	    	Block(location.x-1,location.y).notOccupied(family)+
	    	Block(location.x-2,location.y).notOccupied(family);


    	    count[3] = Block(location.x,location.y-2).notOccupied(family)+
    	    	Block(location.x,location.y-1).notOccupied(family)+
    	    	Block(location.x+1,location.y-1).notOccupied(family)+
    	    	Block(location.x+1,location.y).notOccupied(family)+
    	    	Block(location.x+2,location.y).notOccupied(family);
    	    for(int i =0;i<4;i++){
    	    	if(count[direction]<count[i]){
    	    		direction = i;
    	    	}
    	    }
    	    
    	    if(count[direction]>2){
    	    	return direction;
    	    }
    	    break;

    		
    	case 2: // children
    		count[0] = Block(location.x,location.y-1).notOccupied(family)+
    				 Block(location.x,location.y-2).notOccupied(family)+
    				 Block(location.x,location.y-3).notOccupied(family)+
    				 Block(location.x,location.y-4).notOccupied(family);

    		count[1] = Block(location.x,location.y+1).notOccupied(family)+
   				 Block(location.x,location.y+2).notOccupied(family)+
   				 Block(location.x,location.y+3).notOccupied(family)+
   				 Block(location.x,location.y+4).notOccupied(family);

   			count[2] = Block(location.x-1,location.y).notOccupied(family)+
   				 Block(location.x-2,location.y).notOccupied(family)+
   				 Block(location.x-3,location.y).notOccupied(family)+
   				 Block(location.x-4,location.y).notOccupied(family);

   			count[3] = Block(location.x+1,location.y).notOccupied(family)+
	   				 Block(location.x+2,location.y).notOccupied(family)+
	   				 Block(location.x+3,location.y).notOccupied(family)+
	   				 Block(location.x+4,location.y).notOccupied(family);
   			for(int i =0;i<4;i++){
    	    	if(count[direction]<count[i]){
    	    		direction = i;
    	    	}
    	    }
    	    
    	    if(count[direction]>2){
    	    	return direction;
    	    }
    	    break;

    	}
    	return 4;
    }
    
    int hasEnemy(){//direction 0 1 2 3分别代表上下左右  不同角色的占领方式不同  爸爸为0，妈妈为1，宝宝为2
    	switch(rank){
    	case 0:  //father
    	    if(Block(location.x-1,location.y-1).hasPerson(family)||
    	    	Block(location.x,location.y-1).hasPerson(family)||
    	    	Block(location.x+1,location.y-1).hasPerson(family)){
    	    	return 0;
    	    }else
    	    if( Block(location.x-1,location.y+1).hasPerson(family)||
    	    	Block(location.x,location.y+1).hasPerson(family)||
    	    	Block(location.x+1,location.y+1).hasPerson(family)){
    	    	return 1;
    	    }else
    	    if(Block(location.x-1,location.y-1).hasPerson(family)||
 	    	Block(location.x-1,location.y).hasPerson(family)||
 	    	Block(location.x-1,location.y+1).hasPerson(family)){
    	    	return 2;
    	    }else if(
      	    	Block(location.x+1,location.y).hasPerson(family)||
      	    	Block(location.x+1,location.y-1).hasPerson(family)||
      	    	Block(location.x+1,location.y+1).hasPerson(family)){
    	    	return 3;
    	    }else{
    	    	return 4;
    	    }    
    	    	           	       		
    	case 1:  //mother
    	    if(Block(location.x,location.y-2).hasPerson(family)||
	    	Block(location.x,location.y-1).hasPerson(family)||
	    	Block(location.x-1,location.y-1).hasPerson(family)){
    	    	return 0;
    	    }else if(Block(location.x,location.y+2).hasPerson(family)||
    	    	Block(location.x,location.y+1).hasPerson(family)||
    	    	Block(location.x+1,location.y+1).hasPerson(family)){
    	    	return 1;
    	    }else if(Block(location.x-1,location.y+1).hasPerson(family)||
	    	Block(location.x-1,location.y).hasPerson(family)||
	    	Block(location.x-2,location.y).hasPerson(family)){
    	    	return 2;
    	    }else if(Block(location.x+1,location.y-1).hasPerson(family)||
    	    	Block(location.x+1,location.y).hasPerson(family)||
    	    	Block(location.x+2,location.y).hasPerson(family)){
    	    	return 3;
    	    }else{
    	    	return 4;
    	    }
    		
    	case 2: // children
    		 if(Block(location.x,location.y-1).hasPerson(family)||
    				 Block(location.x,location.y-2).hasPerson(family)||
    				 Block(location.x,location.y-3).hasPerson(family)||
    				 Block(location.x,location.y-4).hasPerson(family)){
    			 return 0;
 	    	}else if(Block(location.x,location.y+1).hasPerson(family)||
   				 Block(location.x,location.y+2).hasPerson(family)||
   				 Block(location.x,location.y+3).hasPerson(family)||
   				 Block(location.x,location.y+4).hasPerson(family)){
   			 return 1;
	    	}else if(Block(location.x-1,location.y).hasPerson(family)||
   				 Block(location.x-2,location.y).hasPerson(family)||
   				 Block(location.x-3,location.y).hasPerson(family)||
   				 Block(location.x-4,location.y).hasPerson(family)){
   			 return 2;
	    	}else if(Block(location.x+1,location.y).hasPerson(family)||
	   				 Block(location.x+2,location.y).hasPerson(family)||
	   				 Block(location.x+3,location.y).hasPerson(family)||
	   				 Block(location.x+4,location.y).hasPerson(family)){
	   			 return 3;
		    }else {
		    	return 4;
		    }
    	}
    	return 4;
    }
 
}
