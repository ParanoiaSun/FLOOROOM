package ui;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;



public class JPanelWin extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的开发者介绍界面
	 * 是首要简单任务
	 */
	
	private JMainFrame jMainFrame;
	private JButton back;
	private Point point;
	public JTextArea winText;
	private int winner = 0;
	private int imageindex;


	
	public JPanelWin(JMainFrame jframe){
		this.jMainFrame = jframe;
		jMainFrame.everyImage.win();
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		jframe.setContentPane(this);
		jMainFrame.setDragable(this);
		new Thread(this).start();
		this.initial();
	}
	
	public void setWinner(int i){
		this.winner = i;
	}
	
	public void paintComponent(Graphics g){

		g.drawImage(jMainFrame.everyImage.win.get(imageindex),0,0,JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT,null);
		g.drawImage(jMainFrame.everyImage.IMG_WIN_BG,0,0,JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT,null);
		g.drawImage(jMainFrame.everyImage.IMG_WIN_NUM[winner].getImage(),478,51,null);
		g.drawImage(jMainFrame.everyImage.IMG_WIN_NUM[winner].getImage(),478,51,null);
	
	}
	
	@Override
	public void run() {
		new Thread(new winThread()).start();
	}
	
	public void initial(){
		point = new Point(753,592);
		back = new JButton();
		back.setBounds(point.x, point.y,jMainFrame.everyImage.IMG_WIN_BUTTON.getImage().getWidth(null),jMainFrame.everyImage.IMG_WIN_BUTTON.getImage().getHeight(null));
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setIcon(jMainFrame.everyImage.IMG_WIN_BUTTON);
		this.add(back);
		ButtonListenerw listener=new ButtonListenerw();
		back.addMouseListener(listener);
		back.addActionListener(listener);
		
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
		winText.setBounds(740,370, 300, 200);		
		winText.setFocusable(false);
		
	}
	
	
	
	
	class ButtonListenerw extends MouseAdapter implements ActionListener{

		
		@Override
		public void mouseEntered(MouseEvent e){
			back.setLocation(point.x+2, point.y-2);
			/*if(jFrameGame.isVoice()){
				new AudioPlayer("");
			}*/
		}
		@Override
		public void mouseExited(MouseEvent e){
			back.setLocation(point.x, point.y);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			JPanelLogin panel=new JPanelLogin(jMainFrame);
		}
	}

	class winThread implements Runnable{

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
