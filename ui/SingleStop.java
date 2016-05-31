package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import audio.AudioPlayer1;


public class SingleStop extends JPanel{
	
	private static final long serialVersionUID = 1L;
	/*当按下暂停按钮，出现继续、重来、回主页面三个按钮
	 * 
	 */
	
	private JMainFrame jMainFrame;
	private JButton[] buttons = new JButton[4];
	private Point[] points = new Point[]{new Point(244,332),new Point(373,336),new Point(504,335),new Point(634,343)};
	private int round;
	
	
	public SingleStop(JMainFrame jframe,int i){
		this.jMainFrame = jframe;
		round = i;
		this.setLayout(null);
		this.setVisible(false);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		jMainFrame.setDragable(this);
		jMainFrame.add(this);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(jMainFrame.everyImage.IMG_STOP_BG, 0, 0, null);
		
	
	}
	
	public void initialButtons(){
		//4个按钮分别为音量，重开，主页面，继续
		for (int i = 0; i < 4; i++) {
			buttons[i] = new JButton();
			buttons[i].setBorderPainted(false);			
			buttons[i].setIcon(jMainFrame.everyImage.IMG_STOP_BUTTONS[i]);
			buttons[i].setBounds(points[i].x, points[i].y, jMainFrame.everyImage.IMG_STOP_BUTTONS[i].getIconWidth(), jMainFrame.everyImage.IMG_STOP_BUTTONS[i].getIconHeight());
			buttons[i].setContentAreaFilled(false);
			this.add(buttons[i]);
			ButtonListenss listener=new ButtonListenss(i);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
			buttons[i].setRequestFocusEnabled(false);
			buttons[i].setVisible(false);
		} 
	}
	
	public void appearButtons(){
		for (int i = 0; i < 4; i++) {
			buttons[i].setVisible(true);
		}
	}
	
	
	
	class ButtonListenss extends MouseAdapter implements ActionListener{
		int i;
		private ButtonListenss(int i){
			this.i=i;
		}
		
		@Override
		public void mouseEntered(MouseEvent e){
			AudioPlayer1 a=new AudioPlayer1("sound/entered.wav");
			a.play();
			buttons[i].setLocation(points[i].x+2, points[i].y-2);
			//setIcon(jFrameGame.allImage.IMG_LOGIN_NEXT1_BUTTONS_ENTERED[i]);
			/*if(jFrameGame.isVoice()){
				new AudioPlayer("");
			}*/
		}
		@Override
		public void mouseExited(MouseEvent e){
			buttons[i].setLocation(points[i].x, points[i].y);
			//buttons[i].setIcon(jMainFrame.everyImage.IMG_LOGIN_BUTTONS[i]);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer1 b=new AudioPlayer1("sound/clicked.wav");
			switch (i) {
			case 0:
				b.play();
				if(jMainFrame.music){
					jMainFrame.stopMusic();
					jMainFrame.music = false;
					buttons[i].setIcon(jMainFrame.everyImage.IMG_STOP_BUTTONS[4]);
				}else{
					jMainFrame.music = true;
					jMainFrame.playMusic("sound/game music.wav");
					buttons[i].setIcon(jMainFrame.everyImage.IMG_STOP_BUTTONS[i]);
				}
				break;//音量
			case 1:
				b.play();
				@SuppressWarnings("unused")
				Round game = new Round(jMainFrame,round);
				break;//重开
			case 2:
				b.play();
				@SuppressWarnings("unused")
				JPanelLogin panel=new JPanelLogin(jMainFrame);
				break;//主菜单
			case 3:
				b.play();
				setVisible(false);
				break;//继续
			default:
				break;
			}
		}
	}

}
