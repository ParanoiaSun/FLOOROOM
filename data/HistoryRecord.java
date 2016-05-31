package data;

import java.io.Serializable;

public class HistoryRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 历史比赛记录
	 */
	private int score;
	private int AttackNumber;
	private boolean win;
	
	
	public HistoryRecord(int score, int AttackNumber, boolean win) {
		super();
		this.score = score;
		this.AttackNumber = AttackNumber;
		this.win = win;
	}


	public int getScore() {
		return score;
	}


	public int getAttackNumber() {
		return AttackNumber;
	}


	public boolean isWin() {
		return win;
	}



}
