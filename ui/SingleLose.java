package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import audio.AudioPlayer1;

public class SingleLose extends JPanel{
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的开发者介绍界面
	 * 是首要简单任务
	 */
	
	private JMainFrame jMainFrame;
	private JButton[] buttons = new JButton[]{new JButton(),new JButton()};
	private Point[] points = new Point[]{new Point(664,503),new Point(762,565)};
	public JTextArea winText;

	private int round;

	
	public SingleLose(JMainFrame jframe,int i){
		round = i;
		this.jMainFrame = jframe;
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		jframe.setContentPane(this);
		jMainFrame.setDragable(this);
		this.initial();
	}
	
	public void paintComponent(Graphics g){

		g.drawImage(jMainFrame.everyImage.IMG_SLOSE_BG,0,0,JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT,null);
	
	}
	
	public void initial(){
		
		for (int i = 0; i < points.length; i++) {
			buttons[i].setBounds(points[i].x, points[i].y,jMainFrame.everyImage.IMG_SLOSE_BUTTONS[i].getImage().getWidth(null),jMainFrame.everyImage.IMG_SLOSE_BUTTONS[i].getImage().getHeight(null));
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setIcon(jMainFrame.everyImage.IMG_SLOSE_BUTTONS[i]);
			this.add(buttons[i]);
			ButtonListenersl listener=new ButtonListenersl(i);
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
		winText.setBounds(670,300, 300, 200);		
		winText.setFocusable(false);
		
	}
	
	
	
	
	class ButtonListenersl extends MouseAdapter implements ActionListener{

		int i;
		
		ButtonListenersl(int i){
			this.i = i;
		}
		@Override
		public void mouseEntered(MouseEvent e){
			AudioPlayer1 a=new AudioPlayer1("sound/entered.wav");
			a.play();
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
				@SuppressWarnings("unused")
				Round roundNext = new Round(jMainFrame,round);
			break;
			case 1:
				b.play();
				@SuppressWarnings("unused")
				JPanelLogin panel=new JPanelLogin(jMainFrame);
			}
		}
	}

}
