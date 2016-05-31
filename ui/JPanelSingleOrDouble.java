package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import audio.AudioPlayer1;

public class JPanelSingleOrDouble extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 611;
	public static final int HIGHT = 561;
	/*本类用于游戏选择单双人游戏
	 * frame必须用add方法
	 * 选关： 48 0
单人：197 201
双人：526 201
返回：763 401

	 */
	
	private JMainFrame jMainFrame;
	private JButton[] buttons;
	private Point[] points;

	
	public JPanelSingleOrDouble(JMainFrame jframe){
		this.jMainFrame = jframe;		
		this.setLayout(null);
		this.setVisible(true);
		this.initialButtons();
		jMainFrame.getContentPane().add(this);
		new Thread(this).start();
		this.repaint();
	}

	public void run() {
		int i = 504;//48,0,923,504
		while(i>=0){
			this.setBounds(48,0-i,923,504);
			i-=20;
			jMainFrame.getContentPane().repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
	}
	
	
	public void paintComponent(Graphics g){
		g.drawImage(jMainFrame.everyImage.IMG_CHOOSE_BG,0,0,null);

		//g.drawImage(jMainFrame.everyImage.IMG_LOGIN_NEXT1, 0, 0, null);
	
	}
	
	
	public void removeMe() {
		
		jMainFrame.getContentPane().remove(this);
	}
	
	private void initialButtons() {
		this.buttons=new JButton[]{new JButton("Single"),new JButton("Double"),new JButton("Back")};
		points=new Point[]{new Point(197,201),new Point(526,201),new Point(763,401)};
		for (int i = 0; i < points.length; i++) {
			buttons[i].setBounds(points[i].x, points[i].y, 226,227);//buttons[i].getIcon().getIconWidth(), buttons[i].getIcon().getIconHeight()
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setIcon(jMainFrame.everyImage.IMG_CHOOSE_BUTTONS[i]);
			this.add(buttons[i]);
			ButtonListenersod listener=new ButtonListenersod(i,this);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
		} 
		buttons[2].setBounds(points[2].x, points[2].y, 88,64);
	}
	
	
	
	class ButtonListenersod extends MouseAdapter implements ActionListener{
		int i;
		JPanelSingleOrDouble jPanelsod;
		private ButtonListenersod(int i, JPanelSingleOrDouble jPanelsod){
			this.i=i;
			this.jPanelsod = jPanelsod;
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
			switch (i) {
			case 0://单人
				b.play();
				JPanelRound jpanelRound = new JPanelRound(jMainFrame);
				break;
			case 1://双人
				b.play();
				removeMe();
				JPanelGame jPanelGame = new JPanelGame(jMainFrame);
				break;
			case 2://返回
				b.play();
				new Thread(new closingThread()).start();
				
				break;
			default:
				break;
			}
		}
	}
	
	class closingThread implements Runnable{

		@Override
		public void run() {
			
			int i=0;//48,0,923,504
			while(i<=504){
				setBounds(48,0-i,923,504);
				i+=20;
				jMainFrame.getContentPane().repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			removeMe();
			JPanelLogin jPanelLogin = (JPanelLogin)jMainFrame.getContentPane();
			jPanelLogin.startAdd();
		}
		
	}

}
