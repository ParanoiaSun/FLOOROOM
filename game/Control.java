package game;



import data.HistoryRecord;
import ui.JPanelGame;
import ui.JPanelWin;

public class Control {
	int family = 0,rank = 0;
	public Person personNow; 
	public Person personLast;
	public Block[][] blocks = new Block[15][15];  
	public Person[][] people = new Person[2][3]; 
	Flag[][] flags = new Flag[2][3];
	private JPanelGame game;
	private int turn = 1;//记录回合数
	private int blockLeft = 0;
	private int blockRight = 0;
	private static int wholeTurn = 48;
	
	public Control(JPanelGame game){
		this.game = game;
		initial();
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
	
	
	void initial(){  
		
		for(int i = 0;i<2;i++){
			for(int j = 0;j<3;j++){
				flags[i][j] = new Flag(i*14,j*5+2);
				game.add(flags[i][j]);
				flags[i][j].repaint();
			}
		}
		
		for(int i = 0;i<2;i++){
			for(int j = 0;j<3;j++){
				people[i][j] = new Person(i,j);
				game.add(people[i][j]);
				people[i][j].setControl(this);
				people[i][j].repaint();
			}
		}
		game.initialButtons();
		
		for(int i = 0;i<15;i++){
			for(int j = 0;j<15;j++){
				blocks[i][j] = new Block(i,j);
				game.add(blocks[i][j]);
				blocks[i][j].setControl(this);
				blocks[i][j].repaint();
			}
		}
		refresh();
		personLast = new Person(0,-1);
		countCal();
	}
	
	public void refresh(){
		game.removeButtons();
		personNow = people[family][rank];
		if(isFinish()){
			finishGame();
		}else if(isActive()){
			game.basicAdd(personNow);
			personNow.setExample(personNow.direction, false);
		}else{
			personNow.setExample(personNow.direction, false);
			nextPer();
		}
		
	}
	
	public void refresh(int i){
		game.removeButtons();
		personNow = people[family][rank];
		if(isFinish()){
			finishGame();
		}else if(personNow.getActivity()>=2){
			game.directAdd(personNow,2);
		}else if(isActive()){
			personNow.setExample(personNow.direction, false);
			game.basicAdd(personNow);
		}else{
			nextPer();
		}
	}
	/*public void recordAchievement(int i,int score) {
		//0为玩家获胜	1为电脑获胜
		boolean win = false;
		if(i==0){
			win=true;
		}
		game.jMainFrame.achievement.getHistory().add(new HistoryRecord(score,20,win));
		game.jMainFrame.achievement.refresh();
	}*/
	
	public void finishGame(){
		int score[] = new int[2];
		
		for(int i = 0;i<2;i++){
			for(int j = 0;j<3;j++){
				score[i] = 30*people[1-i][j].getHitTime()+score[0];
			}
		}
		
		score[0] = score[0]+10*blockRight;
		score[1] = score[1]+10*blockLeft;
		System.out.println(score[0]+"  "+score[1]);
		
		JPanelWin jPanelWin = new JPanelWin(game.jMainFrame);
		jPanelWin.winText.setText("SCORE:\n"+"Player 1: "+score[0]+"\n"+"Player 2: "+score[1]);
		if(score[1]>score[0]){
			jPanelWin.setWinner(1);
			/*this.recordAchievement(1,score[0]);*/
		}else{
			/*this.recordAchievement(0,score[1]);*/
		}
		jPanelWin.repaint();
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
	
	public void nextPer(){
		turn++;
		countCal();
		personNow.reactive();
		if(personLast.family==family){
			family = 1-family;		
		}else if(rank==2){
			rank = 0;
		}else{
			rank++;
		}
		personLast = personNow;
		personNow = people[family][rank];
		if(!personNow.isAwake()){
			personNow.setAwake(true);
			nextPer();
		}else{
			refresh();
			game.addSkip();
		}
		
	}
	
	public void repaintAll(){
		for(int i = 0;i<2;i++){
			for(int j = 0;j<3;j++){
				people[i][j].repaint();
			}
		}
		
		for(int i = 0;i<15;i++){
			for(int j = 0;j<15;j++){
				blocks[i][j].repaint();
			}
		}
		
		game.repaint();
	}
	
    
	
	
	

}