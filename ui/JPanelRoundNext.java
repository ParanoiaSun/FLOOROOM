package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import audio.AudioPlayer1;


public class JPanelRoundNext extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的关卡选择界面
	 * 不是首要任务
	 */
	
	private JMainFrame jMainFrame;
	private Point treePoint = new Point(0,770);
	private Point[] points=new Point[]{new Point(8,-300),new Point(332,-300),new Point(665,-300),new Point(893,673)};
	private JButton[] buttons = new JButton[]{new JButton(),new JButton(),new JButton(),new JButton()};
	private boolean close = false;
	private int command = 3;
	private int bigRound;
	private Round roundOne;
	private int cloud_x;

	
	public JPanelRoundNext(JMainFrame jframe,int i){
		this.jMainFrame = jframe;
		bigRound = i;
		jMainFrame.setContentPane(this);
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		this.setVisible(true);
		jMainFrame.setDragable(this);
		initial();
		new Thread(this).start();;
	}
	
	public void paintComponent(Graphics g){

		g.drawImage(jMainFrame.everyImage.IMG_ROUND_BG,0,0,null);
		g.drawImage(jMainFrame.everyImage.IMG_ROUND_BG_CLOUD,cloud_x,0,null);
		g.drawImage(jMainFrame.everyImage.IMG_ROUND_BG_CLOUD,cloud_x+1024,0,null);
		g.drawImage(jMainFrame.everyImage.IMG_ROUND_WORD,382,34,null);
		g.drawImage(jMainFrame.everyImage.IMG_ROUND_TREE, treePoint.x, treePoint.y, null);
	
	}
	
	public void run() {
		new Thread(new cloudThread()).start();
		//279,,,,,264
		if(close){
			
			while(points[2].y>-300){
				points[2].y-=8;
				points[0].y = points[2].y;
				points[1].y = points[2].y;
				
				for(int i = 0;i<3;i++){
					buttons[i].setLocation(points[i]);
				}
				this.repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			
			
			while(this.treePoint.y<770){
				this.treePoint.y += 8;
				this.repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
		
			switch (command) {
			case 0:
				roundOne = new Round(jMainFrame,bigRound+1);
				break;
			case 1:
				roundOne = new Round(jMainFrame,bigRound+2);
				break;
			case 2:
				roundOne = new Round(jMainFrame,bigRound+3);
				break;
			case 3://返回
				@SuppressWarnings("unused")
				JPanelRound jpanelRound = new JPanelRound(jMainFrame);
				break;
			default:
				break;
			}
			
			close = false;
		}else{
			while(this.treePoint.y>280){
				this.treePoint.y -= 8;
				this.repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			
			while(points[2].y<254){
				points[2].y+=8;
				points[0].y = points[2].y;
				points[1].y = points[2].y;
				
				for(int i = 0;i<3;i++){
					buttons[i].setLocation(points[i]);
				}
				this.repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			
			
			close = true;
		}
	}
	
	public void initial(){
		for (int i = 0; i < points.length; i++) {
			buttons[i].setIcon(jMainFrame.everyImage.IMG_RNEXT_BUTTONS[i]);
			buttons[i].setBounds(points[i].x, points[i].y,buttons[i].getIcon().getIconWidth(), buttons[i].getIcon().getIconHeight());
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			this.add(buttons[i]);
			ButtonListenerrn listener=new ButtonListenerrn(i,this);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
		} 
		repaint();
		
	}
	
	
	
	
	class ButtonListenerrn extends MouseAdapter implements ActionListener{
		int i;
		JPanelRoundNext jPanelroundnext;
		private ButtonListenerrn(int i, JPanelRoundNext jPanelroundnext){
			this.i=i;
			this.jPanelroundnext = jPanelroundnext;
		}
		
		@Override
		public void mouseEntered(MouseEvent e){
			AudioPlayer1 a=new AudioPlayer1("sound/entered.wav");
			a.play();
			buttons[i].setLocation(points[i].x+3,points[i].y-3);
			/*if(jFrameGame.isVoice()){
				new AudioPlayer("");
			}*/
		}
		@Override
		public void mouseExited(MouseEvent e){
			
			buttons[i].setLocation(points[i].x,points[i].y);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer1 b=new AudioPlayer1("sound/clicked.wav");
			b.play();
			new Thread(jPanelroundnext).start();
			command = i;
			
		}
	}
	class cloudThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(cloud_x>-1024){
					cloud_x--;
				}else{
					cloud_x=0;
				}
				repaint();
			}
		}
		
		
	}
}
