package game;


import data.HistoryRecord;
import ui.Round;
import ui.SingleLose;
import ui.SingleWin;
import ui.JMainFrame;
import data.Achievement;

public class RoundControl implements Runnable{
	public int family = 0;
	int rank = 0;
	public Player personNow; 
	public OddBlock[][] blocks = new OddBlock[15][15];  
	public Player[] people = new Player[3]; 
	SingleFlag[][] flags = new SingleFlag[2][3];
	public Ai[] ais = new Ai[3];
	private Round game;
	private int turn = 1;//记录回合数
	private int blockLeft = 0;
	private int blockRight = 0;
	private static int wholeTurn = 48;
	private String[] model = new String[]{new String("000000000000000;000000000000000;000001111100000;000001111100000;000001111100000;001111111111100;001111111111100;001111111111100;001111111111100;001111111111100;000001111100000;000001111100000;000001111100000;000000000000000;000000000000000"),
			//1-1
			new String("000000000000000;000000010000000;000000111000000;000001111100000;000011111110000;000111111111000;001111111111100;011111111111110;001111111111100;000111111111000;000011111110000;000001111100000;000000111000000;000000010000000;000000000000000"),
			//1-2
			new String("000000000000000;011111111111110;011111111111110;011111111111110;011111111111110;011110000011110;011110000011110;011110000011110;011110000011110;011110000011110;011111111111110;011111111111110;011111111111110;011111111111110;000000000000000"),
			//1-3
			new String("000001111100000;000001111100000;000001111100000;000001111100000;000001111100000;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;000001111100000;000001111100000;000001111100000;000001111100000;000001111100000;"),
			//2-1
			new String("000000000000000;000001111000000;000011111100000;000111111110000;001111111111000;001111111111100;000111111111110;000011111111111;000111111111110;001111111111100;001111111111000;000111111110000;000011111100000;000001111000000;000000000000000"),
			//2-2
			new String("000000010000000;000000111000000;000001111100000;000011111110000;000111111111000;001111111111100;011111111111110;111111111111111;011111111111110;001111111111100;000111111111000;000011111110000;000001111100000;000000111000000;000000010000000"),
			//2-3
			new String("000011111100000;000111111100000;001111111100000;011111111100000;111111111100000;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;000001111111111;000001111111110;000001111111100;000001111111000;000001111110000"),
			//3-1
			new String("000011111110000;000111111111000;001111111111100;011111111111110;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;011111111111110;001111111111100;000111111111000;000011111110000"),
			//3-2
			new String("111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111;111111111111111")
			//3-3
			};
	
	
	private int round;
	
	
	public RoundControl(Round game,int i){
		this.game = game;
		initial(i);
		round = i;
	}
	
	public void countCal(){
		blockRight = 0;
		blockLeft = 0;
		for(int i = 0;i<15;i++){
			for(int j = 0;j<15;j++){
				if(blocks[i][j].family==0){
					blockRight++;
				}else if(blocks[i][j].family==1){
					blockLeft++;
				}
			}
		}
		
		game.countText.setText("Total "+wholeTurn+" turns, now "+turn+" turns.\n"+"Left family "+blockLeft+" blocks.\n"+"Right family "+blockRight+" blocks.");
	}
	
	
	public void initial(int x){  
		String[] line;
		int perferFamily = game.jMainFrame.getFamily();
		int perferBlock = game.jMainFrame.getBlockStyle();
		switch(x){
		case 1:
			flags[0][0] = new SingleFlag(12,7);
			flags[0][0].setPerfer(game.jMainFrame.getFamily(), 0);
			game.add(flags[0][0]);
			
			flags[1][0] = new SingleFlag(2,7);
			flags[1][0].setPerfer(1-game.jMainFrame.getFamily(), 0);
			game.add(flags[1][0]);
			
			
			people = new Player[]{new Player(0,0)};
			personNow = people[0];
			people[0].setControl(this);
			people[0].setPerfer(perferFamily);
			people[0].setHome(12, 7);
			game.add(people[0]);
		
			ais = new Ai[]{new Ai(1,0)};
			ais[0].setControl(this);
			ais[0].setPerfer(1-perferFamily);
			ais[0].setHome(2, 7);
			game.add(ais[0]);
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[12][7].setHome(0);
			blocks[2][7].setHome(1);


			break;
			
		case 2:
			flags[0][0] = new SingleFlag(13,7);
			flags[0][0].setPerfer(game.jMainFrame.getFamily(), 0);
			game.add(flags[0][0]);
			
			flags[1][0] = new SingleFlag(1,7);
			flags[1][0].setPerfer(1-game.jMainFrame.getFamily(), 0);
			game.add(flags[1][0]);
			
			
			people = new Player[]{new Player(0,0)};
			personNow = people[0];
			people[0].setControl(this);
			people[0].setPerfer(perferFamily);
			people[0].setHome(13, 7);
			game.add(people[0]);
		
			ais = new Ai[]{new Ai(1,0)};
			ais[0].setControl(this);
			ais[0].setPerfer(1-perferFamily);
			ais[0].setHome(1, 7);
			game.add(ais[0]);
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[13][7].setHome(0);
			blocks[1][7].setHome(1);


			break;
			
		case 3:
			flags[0][0] = new SingleFlag(13,1);
			flags[0][0].setPerfer(game.jMainFrame.getFamily(), 0);
			game.add(flags[0][0]);
			
			flags[1][0] = new SingleFlag(1,13);
			flags[1][0].setPerfer(1-game.jMainFrame.getFamily(), 0);
			game.add(flags[1][0]);
			
			
			people = new Player[]{new Player(0,0)};
			personNow = people[0];
			people[0].setControl(this);
			people[0].setPerfer(perferFamily);
			people[0].setHome(13, 1);
			game.add(people[0]);
		
			ais = new Ai[]{new Ai(1,0)};
			ais[0].setControl(this);
			ais[0].setPerfer(1-perferFamily);
			ais[0].setHome(1, 13);
			game.add(ais[0]);
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[13][1].setHome(0);
			blocks[1][13].setHome(1);


			break;
			
		case 4:
			people = new Player[]{new Player(0,0),new Player(0,1)};
			ais = new Ai[]{new Ai(1,0),new Ai(1,1)};
			personNow = people[0];
			
			people[0].setHome(9, 0);
			ais[0].setHome(5, 14);
			people[1].setHome(14, 5);
			ais[1].setHome(0, 9);
			
			for(int i = 0;i<2;i++){
				flags[0][i] = new SingleFlag(9+5*i,0+5*i);
				flags[0][i].setPerfer(game.jMainFrame.getFamily(), i);
				
				flags[1][i] = new SingleFlag(5-5*i,14-5*i);
				flags[1][i].setPerfer(1-game.jMainFrame.getFamily(), i);
				
				game.add(flags[0][i]);
				game.add(flags[1][i]);
				people[i].setControl(this);
				people[i].setPerfer(perferFamily);
				ais[i].setControl(this);
				ais[i].setPerfer(1-perferFamily);
				game.add(people[i]);
				game.add(ais[i]);
			}	
			
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[9][0].setHome(0);
			blocks[5][14].setHome(1);
			blocks[14][5].setHome(0);
			blocks[0][9].setHome(1);

			break;
			
		case 5:
			people = new Player[]{new Player(0,0),new Player(0,1)};
			ais = new Ai[]{new Ai(1,0),new Ai(1,1)};
			personNow = people[0];
			
			people[0].setHome(13, 5);
			ais[0].setHome(1, 8);
			people[1].setHome(13, 8);
			ais[1].setHome(1, 5);
			
			for(int i = 0;i<2;i++){
				flags[0][i] = new SingleFlag(13,5+3*i);
				flags[0][i].setPerfer(game.jMainFrame.getFamily(), i);
				
				flags[1][i] = new SingleFlag(1,8-3*i);
				flags[1][i].setPerfer(1-game.jMainFrame.getFamily(), i);
				
				game.add(flags[0][i]);
				game.add(flags[1][i]);
				people[i].setControl(this);
				people[i].setPerfer(perferFamily);
				ais[i].setControl(this);
				ais[i].setPerfer(1-perferFamily);
				game.add(people[i]);
				game.add(ais[i]);
			}	
			
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[13][5].setHome(0);
			blocks[1][8].setHome(1);
			blocks[13][8].setHome(0);
			blocks[1][5].setHome(1);

			break;
			
		case 6:
			people = new Player[]{new Player(0,0),new Player(0,1)};
			ais = new Ai[]{new Ai(1,0),new Ai(1,1)};
			personNow = people[0];
			
			people[0].setHome(7, 0);
			ais[0].setHome(7, 14);
			people[1].setHome(14, 7);
			ais[1].setHome(0, 7);
			
			for(int i = 0;i<2;i++){
				flags[0][i] = new SingleFlag(7+7*i,0+7*i);
				flags[0][i].setPerfer(game.jMainFrame.getFamily(), i);
				
				flags[1][i] = new SingleFlag(7-7*i,14-7*i);
				flags[1][i].setPerfer(1-game.jMainFrame.getFamily(), i);
				
				game.add(flags[0][i]);
				game.add(flags[1][i]);
				people[i].setControl(this);
				people[i].setPerfer(perferFamily);
				ais[i].setControl(this);
				ais[i].setPerfer(1-perferFamily);
				game.add(people[i]);
				game.add(ais[i]);
			}	
			
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[7][0].setHome(0);
			blocks[7][14].setHome(1);
			blocks[14][7].setHome(0);
			blocks[0][7].setHome(1);

			break;
			
		case 7:
			people = new Player[]{new Player(0,0),new Player(0,1),new Player(0,2)};
			ais = new Ai[]{new Ai(1,0),new Ai(1,1),new Ai(1,2)};
			flags = new SingleFlag[][]{{new SingleFlag(9,0),new SingleFlag(9,5),new SingleFlag(14,5)},{new SingleFlag(0,9),new SingleFlag(5,9),new SingleFlag(5,14)}};
			personNow = people[0];
			
			people[0].setHome(9, 0);
			ais[0].setHome(0, 9);
			people[1].setHome(9, 5);
			ais[1].setHome(5, 9);
			people[2].setHome(14, 5);
			ais[2].setHome(5, 14);
			
			for(int i = 0;i<3;i++){
				flags[0][i].setPerfer(game.jMainFrame.getFamily(), i);
				
				flags[1][i].setPerfer(1-game.jMainFrame.getFamily(), i);
				game.add(flags[0][i]);
				game.add(flags[1][i]);
				people[i].setControl(this);
				people[i].setPerfer(perferFamily);
				ais[i].setControl(this);
				ais[i].setPerfer(1-perferFamily);
				game.add(people[i]);
				game.add(ais[i]);
			}
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[9][0].setHome(0);
			blocks[9][5].setHome(0);
			blocks[14][5].setHome(0);
			blocks[0][9].setHome(1);
			blocks[5][9].setHome(1);
			blocks[5][14].setHome(1);
			
			
			break;
			
		case 8:
			people = new Player[]{new Player(0,0),new Player(0,1),new Player(0,2)};
			ais = new Ai[]{new Ai(1,0),new Ai(1,1),new Ai(1,2)};
			flags = new SingleFlag[][]{{new SingleFlag(14,7),new SingleFlag(12,2),new SingleFlag(12,12)},{new SingleFlag(0,7),new SingleFlag(2,12),new SingleFlag(2,2)}};
			personNow = people[0];
			
			people[0].setHome(14, 7);
			ais[0].setHome(0, 7);
			people[1].setHome(12, 2);
			ais[1].setHome(2, 12);
			people[2].setHome(12, 12);
			ais[2].setHome(2, 2);
			
			for(int i = 0;i<3;i++){
				flags[0][i].setPerfer(game.jMainFrame.getFamily(), i);
				flags[1][i].setPerfer(1-game.jMainFrame.getFamily(), i);
				game.add(flags[0][i]);
				game.add(flags[1][i]);
				people[i].setControl(this);
				people[i].setPerfer(perferFamily);
				ais[i].setControl(this);
				ais[i].setPerfer(1-perferFamily);
				game.add(people[i]);
				game.add(ais[i]);
			}
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[14][7].setHome(0);
			blocks[12][2].setHome(0);
			blocks[12][12].setHome(0);
			blocks[0][7].setHome(1);
			blocks[2][12].setHome(1);
			blocks[2][2].setHome(1);
			
			
			break;
			
		case 9:
			people = new Player[]{new Player(0,0),new Player(0,1),new Player(0,2)};
			ais = new Ai[]{new Ai(1,0),new Ai(1,1),new Ai(1,2)};
			flags = new SingleFlag[][]{{new SingleFlag(14,12),new SingleFlag(14,7),new SingleFlag(14,2)},{new SingleFlag(0,2),new SingleFlag(0,7),new SingleFlag(0,12)}};
			personNow = people[0];
			
			people[0].setHome(14, 12);
			ais[0].setHome(0, 2);
			people[1].setHome(14, 7);
			ais[1].setHome(0, 7);
			people[2].setHome(14, 2);
			ais[2].setHome(0, 12);
			
			for(int i = 0;i<3;i++){
				flags[0][i].setPerfer(game.jMainFrame.getFamily(), i);
				
				flags[1][i].setPerfer(1-game.jMainFrame.getFamily(), i);
				game.add(flags[0][i]);
				game.add(flags[1][i]);
				people[i].setControl(this);
				people[i].setPerfer(perferFamily);
				ais[i].setControl(this);
				ais[i].setPerfer(1-perferFamily);
				game.add(people[i]);
				game.add(ais[i]);
			}
			
			game.initialButtons();
		
			line = model[x-1].split(";");
			for(int i = 0;i<15;i++){
				char[] colume = line[i].toCharArray();
				for(int j = 0;j<15;j++){
					blocks[i][j] = new OddBlock(i,j);
					game.add(blocks[i][j]);
					blocks[i][j].setControl(this);
					blocks[i][j].setPerfer(perferBlock);
					blocks[i][j].setExist(colume[j]);
					blocks[i][j].repaint();
				}
			}
			
			blocks[14][12].setHome(0);
			blocks[14][7].setHome(0);
			blocks[14][2].setHome(0);
			blocks[0][2].setHome(1);
			blocks[0][7].setHome(1);
			blocks[0][12].setHome(1);
			
			
			break;
			
		}
		refresh();
		countCal();

	}
	
	public void refresh(){
		game.removeButtons();
		if(isFinish()){
			finishGame();
		}else if(isActive()){
			game.basicAdd(personNow);
			personNow.setExample(personNow.direction, false);
		}else{
			personNow.setExample(personNow.direction, false);
			nextAi();
		}
		
	}
	
	public void refresh(int i){
		game.removeButtons();
		if(isFinish()){
			finishGame();
		}else if(personNow.getActivity()>=2){
			game.directAdd(personNow,2);
		}else if(isActive()){
			personNow.setExample(personNow.direction, false);
			game.basicAdd(personNow);
		}else{
			nextAi();
		}
	}
	
	public void recordAchievement(int i,int score) {
		//0为玩家获胜	1为电脑获胜
		boolean win = false;
		if(i==0){
			win=true;
		}
		game.jMainFrame.achievement.getHistory().add(new HistoryRecord(score,5,win));
		game.jMainFrame.achievement.refresh();
	}
	
	public void finishGame(){
		int score[] = new int[2];
		
		for(int i = 0;i<people.length;i++){
			score[0] = 30*ais[i].getHitTime()+score[0];
			score[1] = 30*people[i].getHitTime()+score[0];
		}
		
		score[0] = score[0]+10*blockRight;
		score[1] = score[1]+10*blockLeft;
		
		
		if(score[1]<=score[0]){
			SingleWin jPanelWin = new SingleWin(game.jMainFrame,round);
			jPanelWin.winText.setText("SCORE:\n"+"Player: "+score[0]+"\n"+"Computer: "+score[1]);
			jPanelWin.repaint();
			this.recordAchievement(0, score[0]);
		}else{
			SingleLose jPanelLose  = new SingleLose (game.jMainFrame,round);
			jPanelLose.winText.setText("SCORE:\n"+"Player: "+score[0]+"\n"+"Computer: "+score[1]);
			jPanelLose.repaint();
			this.recordAchievement(1, score[0]);
		}
		
	}
	
	public boolean isActive(){    	
		int activity = personNow.getActivity();
		if(activity<=0){
			return false;
		}
		
		return true;
		
	}
	
	public boolean isFinish(){
		if(turn>=wholeTurn){//限定回合数
			return true;
		}
		for(int i = 0;i<15;i++){
			for(int j = 0;i<15;i++){
				if(!blocks[i][j].isOccupied()){
					return false;
				}
			}
		}
		return true;
	}
	
	public void nextAi(){
		turn++;
		countCal();
		game.removeButtons();
		game.removeSkip();
		personNow.reactive();
		new Thread(this).start();
		
	}
	
	public void run(){
		
		if(ais[personNow.rank].isAwake()){
			while(ais[personNow.rank].getActivity()>0){
				ais[personNow.rank].execute();
				game.repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			ais[personNow.rank].reactive();
		}else{
			ais[personNow.rank].setAwake(true);
		}
		nextPer();
		
	}

	
	public void nextPer(){
		turn++;
		countCal();
		personNow = people[(personNow.rank+1)%people.length];
		if(!personNow.isAwake()){
			personNow.setAwake(true);
			nextAi();
		}else{
			refresh();
			game.addSkip();
		}
		
	}
}
