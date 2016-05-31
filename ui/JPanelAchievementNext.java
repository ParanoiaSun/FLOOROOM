package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import audio.AudioPlayer1;
import ui.JPanelAchievement.ButtonListenerau;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelAchievementNext extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	
	private JMainFrame jMainFrame;
	Point[] points=new Point[]{new Point(0,0),new Point(400,0),new Point(200,0),new Point(600,0)};
	private int now = 0;
	private int next = 0;
	private boolean isRight = true;
	private ImageIcon[] achieve = new ImageIcon[12];
	/*private JLabel[] Labels;*/
	private boolean[] getAchievement = {false,false,false,false,false,false,};

	
	public JPanelAchievementNext(JMainFrame jframe){
		this.jMainFrame = jframe;		
		this.setLayout(null);
		this.setBounds(322,400,380,220);
		this.setVisible(true);
		jMainFrame.getContentPane().add(this);
		this.repaint();
		for (int i = 0; i < 6; i++) {
			achieve[i]=jMainFrame.everyImage.IMG_ACHIEVEMENT[i];
		}
		for (int i = 6; i < 12; i++) {
			achieve[i]=jMainFrame.everyImage.IMG_ACHIEVEMENT2[i-6];
			} 
	}

	
	public void paintComponent(Graphics g){
		if (jMainFrame.achievement.isAchieve1()) {
			getAchievement[0]=true;
		}
		if (jMainFrame.achievement.isAchieve2()) {
			getAchievement[1]=true;
		}
		if (jMainFrame.achievement.isAchieve3()) {
			getAchievement[2]=true;
		}
		if (jMainFrame.achievement.isAchieve4()) {
			getAchievement[3]=true;
		}
		if (jMainFrame.achievement.isAchieve5()) {
			getAchievement[4]=true;
		}
		if (jMainFrame.achievement.isAchieve6()) {
			getAchievement[5]=true;
			}
		if(getAchievement[now]){ g.drawImage(achieve[now+6].getImage(), points[0].x, points[1].y,null); }else{ g.drawImage(achieve[now].getImage(), points[0].x, points[1].y,null); }
		if(getAchievement[now+1]){ g.drawImage(achieve[now+7].getImage(),  points[2].x, points[3].y,null); }else{ g.drawImage(achieve[now+1].getImage(),  points[2].x, points[3].y,null); }
		if(getAchievement[next]){ g.drawImage(achieve[next+6].getImage(), points[1].x, points[1].y,null); }else{ g.drawImage(achieve[next].getImage(), points[1].x, points[1].y,null); }
		if(getAchievement[now]){ g.drawImage(achieve[next+7].getImage(), points[3].x, points[3].y,null); }else{ g.drawImage(achieve[next+1].getImage(), points[3].x, points[3].y,null); }
		/*g.drawImage(jMainFrame.everyImage.IMG_ACHIEVEMENT[now].getImage(), points[0].x, points[1].y,null);
		g.drawImage(jMainFrame.everyImage.IMG_ACHIEVEMENT[now+1].getImage(), points[2].x, points[3].y,null);
		g.drawImage(jMainFrame.everyImage.IMG_ACHIEVEMENT[next].getImage(), points[1].x, points[1].y,null);
		g.drawImage(jMainFrame.everyImage.IMG_ACHIEVEMENT[next+1].getImage(), points[3].x, points[3].y,null);*/
	
	}
	
	
	
	/*private void initialLabels() {
		this.Labels=new JLabel[12];   this.getAchievement=new boolean[6];
		for (int i = 0; i < 6; i++) {
			Labels[i]=new JLabel(jMainFrame.everyImage.IMG_ACHIEVEMENT[i]);*/
			/*Labels[i].setBounds(points[i].x, points[i].y, Labels[i].getIcon().getIconWidth(), Labels[i].getIcon().getIconHeight());
			this.add(Labels[i]);*/
			/*labels[i].addMouseListener(new LabelListener(i,this));*/
		/*} 
		for (int i = 6; i < 12; i++) {
			Labels[i]=new JLabel(jMainFrame.everyImage.IMG_ACHIEVEMENT2[i-6]);
			/*Labels[i].setBounds(points[i].x, points[i].y, Labels[i].getIcon().getIconWidth(), Labels[i].getIcon().getIconHeight());
			this.add(Labels[i]);*/
			/*labels[i].addMouseListener(new LabelListener(i,this));*/
		/*} 
		if (jMainFrame.achievement.isAchieve1()) {
			getAchievement[0]=true;
		}
		if (jMainFrame.achievement.isAchieve2()) {
			getAchievement[1]=true;
		}
		if (jMainFrame.achievement.isAchieve3()) {
			getAchievement[2]=true;
		}
		if (jMainFrame.achievement.isAchieve4()) {
			getAchievement[3]=true;
		}
		if (jMainFrame.achievement.isAchieve5()) {
			getAchievement[4]=true;
		}
		if (jMainFrame.achievement.isAchieve6()) {
			getAchievement[5]=true;
			}
		Labels[now+6].setBounds(points[0].x, points[1].y, Labels[now+6].getIcon().getIconWidth(), Labels[now].getIcon().getIconHeight());
		Labels[now+7].setBounds(points[2].x, points[3].y, Labels[now+7].getIcon().getIconWidth(), Labels[now+1].getIcon().getIconHeight());
		Labels[next+6].setBounds(points[1].x, points[1].y, Labels[next+6].getIcon().getIconWidth(), Labels[next].getIcon().getIconHeight());
		Labels[next+7].setBounds(points[3].x, points[3].y, Labels[next+7].getIcon().getIconWidth(), Labels[next+1].getIcon().getIconHeight());
		Labels[now].setBounds(points[0].x, points[1].y, Labels[now].getIcon().getIconWidth(), Labels[now].getIcon().getIconHeight());
		Labels[now+1].setBounds(points[2].x, points[3].y, Labels[now+1].getIcon().getIconWidth(), Labels[now+1].getIcon().getIconHeight());
		Labels[next].setBounds(points[1].x, points[1].y, Labels[next].getIcon().getIconWidth(), Labels[next].getIcon().getIconHeight());
		Labels[next+1].setBounds(points[3].x, points[3].y, Labels[next+1].getIcon().getIconWidth(), Labels[next+1].getIcon().getIconHeight());
		if(getAchievement[now]){  this.add(Labels[now+6]); }else{ this.add(Labels[now]); }
		if(getAchievement[now+1]){  this.add(Labels[now+7]); }else{ this.add(Labels[now+1]); }
		if(getAchievement[next]){  this.add(Labels[next+6]); }else{ this.add(Labels[now]); }
		if(getAchievement[now]){  this.add(Labels[next+7]); }else{ this.add(Labels[next+1]); }
	}*/
	
	public void setDirection(boolean i){
		isRight = i;
	}
	
	public void run(){
		if(isRight){
			next=next+2;
			points[1].x = 400;
			while(points[1].x>0){
				points[1].x-=10;
				points[0].x-=10;
				points[2].x-=10;
				points[3].x-=10;
				this.repaint();
				jMainFrame.getContentPane().repaint();
				/*this.initialLabels();*/
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			points[0].x = 0;
			points[2].x = 200;
			now = next;
		}else{
			next=next-2;
			points[1].x = -400;
			points[3].x = -600;
			while(points[1].x<0){
				points[1].x+=10;
				points[0].x+=10;
				points[2].x+=10;
				points[3].x+=10;
				this.repaint();
				jMainFrame.getContentPane().repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			points[0].x = 0;
			points[2].x = 200;
			now = next;
		}
	}
	
	

}