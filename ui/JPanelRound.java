package ui;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

import ui.JPanelSingleOrDouble.ButtonListenersod;
import ui.JPanelSingleOrDouble.closingThread;

public class JPanelRound extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的关卡选择界面
	 * 不是首要任务
	 */
	
	private JMainFrame jMainFrame;
	@SuppressWarnings("unused")
	private Point treePoint = new Point(0,770);
	private Point[] points=new Point[]{new Point(79,-300),new Point(560,-300),new Point(271,-300),new Point(893,673)};
	private JButton[] buttons = new JButton[]{new JButton(),new JButton(),new JButton(),new JButton()};
	private boolean close = false;
	private int command = 3;
	private JPanelRoundNext jprn;
	private int cloud_x;

	
	public JPanelRound(JMainFrame jframe){
		this.jMainFrame = jframe;
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
		/*IMG_ROUND_BG_CLOUD*/
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
				points[0].y = points[2].y-264;
				points[1].y = points[2].y-264;
				
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
				jprn = new JPanelRoundNext(jMainFrame,command*3);//0
				break;
			case 1:
				jprn = new JPanelRoundNext(jMainFrame,command*3);//3
				break;
			case 2:
				jprn = new JPanelRoundNext(jMainFrame,command*3);//6
				break;
			case 3://返回
				@SuppressWarnings("unused")
				JPanelLogin jPanelLogin = new JPanelLogin(jMainFrame);
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
			
			while(points[2].y<408){
				points[2].y+=8;
				points[0].y = points[2].y-264;
				points[1].y = points[2].y-264;
				
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
			buttons[i].setIcon(jMainFrame.everyImage.IMG_ROUND_BUTTONS[i]);
			buttons[i].setBounds(points[i].x, points[i].y,buttons[i].getIcon().getIconWidth(), buttons[i].getIcon().getIconHeight());
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			this.add(buttons[i]);
			ButtonListenerr listener=new ButtonListenerr(i,this);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
		} 
		repaint();
		
	}
	
	
	
	
	class ButtonListenerr extends MouseAdapter implements ActionListener{
		int i;
		JPanelRound jPanelround;
		private ButtonListenerr(int i, JPanelRound jPanelround){
			this.i=i;
			this.jPanelround = jPanelround;
		}
		
		@Override
		public void mouseEntered(MouseEvent e){
			
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
			new Thread(jPanelround).start();
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
