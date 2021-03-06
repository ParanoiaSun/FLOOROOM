package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import audio.AudioPlayer1;

public class JPanelLogin extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的登陆界面
	 * 开始游戏，成就，关于我们，退出游戏
	 */
	
	private JMainFrame jMainFrame;
	@SuppressWarnings("unused")
	private JPanelLoginNext jPanelLoginNext;
	private JButton[] buttons;//包括右上角的三个按钮，分别为静音，帮助，退出
	private Point titlePoint = new Point(46,-341);
	private boolean close = false;

	
	public JPanelLogin(JMainFrame jframe){
		this.jMainFrame = jframe;
		
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		this.setVisible(true);
		jMainFrame.setDragable(this);
		jMainFrame.setContentPane(this);
		this.startAdd();
		this.repaint();
		this.initialButtons();
		jMainFrame.playMusic("sound/lazy song.wav");
	}
	
	public void run() {
		//46,120,940,341
		if(close){
			while(this.titlePoint.y>-341){
				this.titlePoint.y -= 8;
				this.repaint();
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}
			close = false;
		}else{
			while(this.titlePoint.y<120){
				this.titlePoint.y += 8;
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
	
	public void startAdd(){
		new Thread(this).start();
		jPanelLoginNext = new JPanelLoginNext(jMainFrame);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(jMainFrame.everyImage.IMG_LOGIN_BG,0, 0,null);
		g.drawImage(jMainFrame.everyImage.IMG_LOGIN_TITLE,titlePoint.x, titlePoint.y,null);
		//g.drawImage(jMainFrame.everyImage.IMG_LOGIN_TITLE, titlePoint.x, titlePoint.y,940,341,null);
	
	}
	
	public void initialButtons(){
		this.buttons=new JButton[]{new JButton(),new JButton(),new JButton()};
		Point[] points=new Point[]{new Point(830,14),new Point(895,14),new Point(950,16)};
		for (int i = 0; i < points.length; i++) {
			buttons[i].setBounds(points[i].x, points[i].y,jMainFrame.everyImage.IMG_LOGIN_BUTTONS[i].getImage().getWidth(null),jMainFrame.everyImage.IMG_LOGIN_BUTTONS[i].getImage().getHeight(null));
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setIcon(jMainFrame.everyImage.IMG_LOGIN_BUTTONS[i]);
			this.add(buttons[i]);
			ButtonListenerl listener=new ButtonListenerl(i);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
		} 
		
	}
	
	
	public void start(){
		jPanelLoginNext = new JPanelLoginNext(jMainFrame);
		
	}	
	
	class ButtonListenerl extends MouseAdapter implements ActionListener{
		int i;
		private ButtonListenerl(int i){
			this.i=i;
		}
		
		@Override
		public void mouseEntered(MouseEvent e){
			AudioPlayer1 a=new AudioPlayer1("sound/entered.wav");
			a.play();
			Point[] points=new Point[]{new Point(832,12),new Point(897,12),new Point(952,14)};
			buttons[i].setBounds(points[i].x, points[i].y,61,55);
			repaint();
			//setIcon(jFrameGame.allImage.IMG_LOGIN_NEXT1_BUTTONS_ENTERED[i]);
			/*if(jFrameGame.isVoice()){
				new AudioPlayer("");
			}*/
		}
		@Override
		public void mouseExited(MouseEvent e){
			Point[] points=new Point[]{new Point(830,14),new Point(895,14),new Point(950,16)};
			buttons[i].setBounds(points[i].x, points[i].y,61,55);
			repaint();
			//buttons[i].setIcon(jMainFrame.everyImage.IMG_LOGIN_BUTTONS[i]);
		}

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer1 b=new AudioPlayer1("sound/clicked.wav");
			switch (i) {
			case 0:
				b.play();
				if(jMainFrame.musicPlaying){
					jMainFrame.stopMusic();
					jMainFrame.music = false;
					buttons[i].setIcon(jMainFrame.everyImage.IMG_LOGIN_BUTTONS[3]);
				}else{
					jMainFrame.music = true;
					jMainFrame.playMusic("sound/lazy song.wav");
					buttons[i].setIcon(jMainFrame.everyImage.IMG_LOGIN_BUTTONS[i]);
				}
				break;//音量键
			case 1:
				b.play();
				JPanelHelp jPanelHelp = new JPanelHelp(jMainFrame);
				break;//帮助键
			case 2:
				b.play();
				System.exit(0);
				break;//关闭键
			default:
				break;
			}
		}
	}

}