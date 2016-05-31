package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPanelStart extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 本类用于游戏开头动画的制作
	 */
	
	private JMainFrame jMainFrame;
	private int imageIndex;
	private int imageIndex2;
	float ii = 0.0f;

	
	public JPanelStart(JMainFrame jframe){
		this.jMainFrame = jframe;
		jMainFrame.everyImage.start();
		jMainFrame.everyImage.showname();
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		this.setVisible(true);
		jMainFrame.setDragable(this);
		jMainFrame.setContentPane(this);
		new Thread(this).start();
		/*this.repaint();*/
		/*this.initial();*/
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		 /*AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.42f);
   		g2d.setComposite(alphaComposite);
	  * */
		/*g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  ii));*/
		if(imageIndex==93){
			g2.drawImage(jMainFrame.everyImage.start.get(93), 0, 0,this.getWidth(),this.getHeight(), null);
			g2.drawImage(jMainFrame.everyImage.groupname.get(imageIndex2), 0, 70,this.getWidth(),this.getHeight(), null);
		}else{
		g2.drawImage(jMainFrame.everyImage.start.get(imageIndex), 0, 0,this.getWidth(),this.getHeight(), null);
		}
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 93; i++) {
			/*if(jump){break;}*/
			imageIndex++;
			/*if(ii<0.99f){
			ii+=0.002f;
			}*/
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
		
		for (int i = 0; i < 19; i++) {
			imageIndex2++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
		try{
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jMainFrame.setContentPane(new JPanelLogin(jMainFrame));
		
	}
	
	public void initial(){
		
	}
	
	
	
	
	class ButtonListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			

		}
			
	}


}
