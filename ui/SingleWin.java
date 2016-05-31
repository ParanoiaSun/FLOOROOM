package ui;

import java.awt.*;


import java.awt.event.*;
import javax.swing.*;
import audio.AudioPlayer1;


public class SingleWin extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的开发者介绍界面
	 * 是首要简单任务
	 */
	
	private JMainFrame jMainFrame;
	private JButton[] buttons = new JButton[]{new JButton(),new JButton()};
	private Point[] points = new Point[]{new Point(683,550),new Point(768,598)};
	public JTextArea winText;

	private int round;
	private int imageindex;

	
	public SingleWin(JMainFrame jframe,int i){
		round = i;
		this.jMainFrame = jframe;
		jMainFrame.everyImage.win();
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		jframe.setContentPane(this);
		jMainFrame.setDragable(this);
		new Thread(this).start();
		this.initial();
	}
	
	public void paintComponent(Graphics g){
		
		g.drawImage(jMainFrame.everyImage.win.get(imageindex),0,0,JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT,null);
		g.drawImage(jMainFrame.everyImage.IMG_SWIN_BG,0,0,JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT,null);
	}
	
	@Override
	public void run() {
		new Thread(new winThread2()).start();
	}
	
	public void initial(){
		
		for (int i = 0; i < points.length; i++) {
			buttons[i].setBounds(points[i].x, points[i].y,jMainFrame.everyImage.IMG_SWIN_BUTTONS[i].getImage().getWidth(null),jMainFrame.everyImage.IMG_SWIN_BUTTONS[i].getImage().getHeight(null));
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setIcon(jMainFrame.everyImage.IMG_SWIN_BUTTONS[i]);
			this.add(buttons[i]);
			ButtonListenersw listener=new ButtonListenersw(i);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
		} 
		
		initialText();
		
	}
	
	public void initialText(){
		winText = new JTextArea(3,10);
		winText.setLineWrap(true);
		winText.setWrapStyleWord(true);
		winText.setBorder(null);
		winText.setOpaque(false);
		Font textFont = new Font("serif",Font.BOLD,40);
		winText.setFont(textFont);
		add(winText);
		winText.setBounds(700,340, 300, 200);		
		winText.setFocusable(false);
		
	}
	
	
	
	
	class ButtonListenersw extends MouseAdapter implements ActionListener{

		int i;
		
		ButtonListenersw(int i){
			this.i = i;
		}
		@Override
		public void mouseEntered(MouseEvent e){
			buttons[i].setLocation(points[i].x+2, points[i].y-2);
			/*if(jFrameGame.isVoice()){
				new AudioPlayer("");
			}*/
		}
		@Override
		public void mouseExited(MouseEvent e){
			buttons[i].setLocation(points[i].x, points[i].y);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer1 b=new AudioPlayer1("sound/clicked.wav");
			switch (i){
			case 0:
				b.play();
				if(round!=3){
					round++;
					Round roundNext = new Round(jMainFrame,round);
				}else{
					@SuppressWarnings("unused")
					JPanelLogin panel=new JPanelLogin(jMainFrame);
				}
			break;
			case 1:
				b.play();
				@SuppressWarnings("unused")
				JPanelLogin panel=new JPanelLogin(jMainFrame);
			}
		}
	}
	
	class winThread2 implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(imageindex<5){
					imageindex++;
				}else{
					imageindex=0;
				}
				repaint();
			}
		}
		
		
	}

}
