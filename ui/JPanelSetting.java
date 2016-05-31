package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import audio.AudioPlayer1;

public class JPanelSetting extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的设置界面
	 * 是首要任务
	 * 
	 */
	
	private JMainFrame jMainFrame;
	private JButton[] buttons;
	private JPanelSettingNext jpsn;
	private Point[] points;
	private int imageindex;
	
	
	public JPanelSetting(JMainFrame jframe){
		this.jMainFrame = jframe;	
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH,JMainFrame.JFRAME_HIGHT);
		this.setVisible(true);
		jMainFrame.setDragable(this);
		jMainFrame.setContentPane(this);
		new Thread(this).start();
	}
	
	public void paintComponent(Graphics g){
		if(imageindex==29){
			g.drawImage(jMainFrame.everyImage.window.get(29), 0, 0,this.getWidth(),this.getHeight(), null);
		}else{
			g.drawImage(jMainFrame.everyImage.window.get(imageindex), 0, 0,this.getWidth(),this.getHeight(), null);
			
		}
	
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 29; i++) {
			imageindex++;
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
		this.initialButtons();
		this.repaint();
		
	}
	
	private void initialButtons() {
		this.buttons=new JButton[]{new JButton(),new JButton(),new JButton()};
		points=new Point[]{new Point(460,655),new Point(520,655),new Point(670,650)};
		for (int i = 0; i < points.length; i++) {
			buttons[i].setIcon(jMainFrame.everyImage.IMG_ST_BUTTONS[i]);
			buttons[i].setBounds(points[i].x, points[i].y, jMainFrame.everyImage.IMG_ST_BUTTONS[i].getIconWidth(),jMainFrame.everyImage.IMG_ST_BUTTONS[i].getIconHeight());
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			this.add(buttons[i]);
			buttons[i].setRequestFocusEnabled(false);
			ButtonListenerjs listener=new ButtonListenerjs(i);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
		} 
		
		jpsn = new JPanelSettingNext(jMainFrame);
	}
	
	
	
	
	class ButtonListenerjs extends MouseAdapter implements ActionListener{
		int i;
		private ButtonListenerjs(int i){
			this.i=i;
		}
		
		public void mouseEntered(MouseEvent e){
			AudioPlayer1 a=new AudioPlayer1("sound/entered.wav");
			a.play();
			buttons[i].setLocation(points[i].x+2,points[i].y-2);
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
			switch (i){
			case 0:
				b.play();
				break;
			case 1:
				b.play();
				break;
			case 2:
				b.play();
				@SuppressWarnings("unused")
				JPanelLogin jPanelLogin = new JPanelLogin(jMainFrame);
				break;
			}

		}
			
	}





}