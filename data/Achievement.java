package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Achievement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成就系统的数据
	 */
	private boolean achieve1;//'小鬼当家'：一局比赛中骚扰超过6次
	private boolean achieve2;//'终极诱惑'：一局比赛中骚扰超过12次
	private boolean achieve3;//'呀呀学步'：一局积分超过600
	private boolean achieve4;//'建筑大亨'：一局积分超过1500
	private boolean achieve5;//'牛刀小试'：获得一场比赛的胜利
	private boolean achieve6;//'攻无不克'：获得十场比赛的胜利
	private List<HistoryRecord> history=new ArrayList<HistoryRecord>();
	
	public Achievement(){
		load();
	}
	
	
	public List<HistoryRecord> getHistory() {
		return history;
	}


	public void load(){
		if (!new File("data/achieve.dat").exists()){
			save();
			return;
		}
		Achievement achievement = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/achieve.dat"));
			achievement = (Achievement) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.achieve1=achievement.achieve1;
		this.achieve2=achievement.achieve2;
		this.achieve3=achievement.achieve3;
		this.achieve4=achievement.achieve4;
		this.achieve5=achievement.achieve5;
		this.achieve6=achievement.achieve6;
		this.history=achievement.history;
	}
	
	public void save(){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("data/achieve.dat"));
			oos.writeObject(this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void refresh(){
		if(!achieve1){
			if(history.get(history.size()-1).getAttackNumber()>=6){
				achieve1=true;
			}
		}
		if(!achieve2 && history.get(history.size()-1).getAttackNumber()>=12){
			achieve2=true;
		}
		if(!achieve3 &&history.get(history.size()-1).getScore()>=600){
			achieve3=true;
		}
		if(!achieve4 &&history.get(history.size()-1).getScore()>=1500){
			achieve4=true;
		}
		if(!achieve5 &&history.get(history.size()-1).isWin()){
			achieve5=true;
		}
		if(!achieve6 &&history.get(history.size()-1).isWin()){
			int wintimes=0;
			for (int i = 1; i <history.size(); i++) {
				if(history.get(history.size()-i).isWin()){
					wintimes+=1;
				}
			}
			if(wintimes>=10){
				achieve6=true;
			}
		}
		save();
	}
	
	public boolean isAchieve1() {
		return achieve1;
	}

	public void setAchieve1(boolean achieve1) {
		this.achieve1 = achieve1;
		save();
	}

	public boolean isAchieve2() {
		return achieve2;
	}

	public void setAchieve2(boolean achieve2) {
		this.achieve2 = achieve2;
		save();
	}

	public boolean isAchieve3() {
		return achieve3;
	}

	public void setAchieve3(boolean achieve3) {
		this.achieve3 = achieve3;
		save();
	}

	public boolean isAchieve4() {
		return achieve4;
	}

	public void setAchieve4(boolean achieve4) {
		this.achieve4 = achieve4;
		save();
	}

	public boolean isAchieve5() {
		return achieve5;
	}

	public void setAchieve5(boolean achieve5) {
		this.achieve5 = achieve5;
		save();
	}

	public boolean isAchieve6() {
		return achieve6;
	}

	public void setAchieve6(boolean achieve6) {
		this.achieve6 = achieve6;
		save();
	}

	
}
