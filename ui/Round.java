package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.*;

import game.Control;
import game.Person;
import game.Player;
import game.RoundControl;


public class Round extends JPanel{
	private static final long serialVersionUID = 1L;
	/*本类用于游戏的主界面
	 * 
	 */
	
	public JMainFrame jMainFrame;
	private JButton[] buttons = new JButton[11];
	private RoundControl control = null;
	private int commandNow;
	public JTextArea countText;
	private SingleStop jps;
	private String[] tips;
	private Image ImageBuffer = null;  
	private Graphics GraImage = null;  
	
	
	public Round(JMainFrame jframe,int x){
		this.jMainFrame = jframe;
		tips = new String[]{"占领","骚扰","移动","隐现身","上","下","左","右","返回","暂停","跳过该局"};
		
		
		
		this.setLayout(null);
		this.setBounds(0, 0, JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT);
		this.setVisible(false);
		jMainFrame.setDragable(this);
		jMainFrame.setContentPane(this);
		jps = new SingleStop(jMainFrame,x);
		jMainFrame.playMusic("sound/game music.wav");
		jps.initialButtons();
		initialText();
		
		for (int i = 0; i < 4; i++) {
			buttons[i] = new JButton();
			buttons[i].setToolTipText(tips[i]);
			buttons[i].setBorderPainted(false);
			this.add(buttons[i]);
			buttons[i].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[i]);
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			ButtonListener listener=new ButtonListener(i);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
			buttons[i].setRequestFocusEnabled(false);
			buttons[i].setVisible(false);
		} 
		control = new RoundControl(this,x);
		addSkip();
		
		this.setVisible(true);
		//this.repaint();
	}
	
	public void initialText(){
		countText = new JTextArea(3,10);
		countText.setLineWrap(true);
		countText.setWrapStyleWord(true);
		countText.setBorder(null);
		countText.setOpaque(false);
		Font textFont = new Font("serif",Font.BOLD,30);
		countText.setFont(textFont);
		add(countText);
		countText.setBounds(0, 0, 400, 120);		
		countText.setFocusable(false);
		
	}
	
	public void initialButtons(){
		//9个按钮分别为占领，骚扰，移动，隐现身，上，下，左，右，返回
		for (int i = 4; i < 11; i++) {
			buttons[i] = new JButton();
			buttons[i].setToolTipText(tips[i]);
			buttons[i].setBorderPainted(false);
			this.add(buttons[i]);
			buttons[i].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[i]);
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			ButtonListener listener=new ButtonListener(i);
			buttons[i].addMouseListener(listener);
			buttons[i].addActionListener(listener);
			buttons[i].setRequestFocusEnabled(false);
			buttons[i].setVisible(false);
		} 
		buttons[9].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[10]);
		buttons[9].setBounds(934,10,73,72);
		buttons[9].setVisible(true);
		buttons[10].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[11]);
	}
	
	public void addSkip(){	
		Point[] skipPoint = new Point[]{new Point(689,717),new Point(247,717)};
		buttons[10].setBounds(skipPoint[control.personNow.family].x,skipPoint[control.personNow.family].y,87,39);
		buttons[10].setVisible(true);
	}
	
	public void basicAdd(Player personNow){//添加基础选项，x和y为人物所在 格子坐标
		Point location = personNow.getRealPoint();//真实坐标
		Point[] points=new Point[]{new Point(location.x+20,location.y-50),new Point(location.x-33,location.y-35),new Point(location.x-55,location.y+10),new Point(location.x-33,location.y+58)};
		if(personNow.isHide()){
			buttons[3].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[9]);
			buttons[3].setBounds(points[0].x,points[0].y,40,40);
			buttons[3].setVisible(true);
			if(personNow.getActivity()>1){
				buttons[2].setBounds(points[1].x,points[1].y,40,40);
				buttons[2].setVisible(true);
			}
			if(control.blocks[personNow.location.x][personNow.location.y].hasPerson()){
				buttons[3].setVisible(false);
			}
		}else if(personNow.getActivity()<4){
			buttons[3].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[3]);
			for(int i = 3;i>3-personNow.getActivity();i--){
				buttons[i].setBounds(points[i].x,points[i].y,40,40);
				buttons[i].setVisible(true);
			}
			
		}else{
			buttons[3].setIcon(jMainFrame.everyImage.IMG_GAME_BUTTONS[3]);
			for(int i = 0;i<4;i++){
				buttons[i].setBounds(points[i].x,points[i].y,40,40);
				buttons[i].setVisible(true);
			}
		}
		if(personNow.inDanger()){
			buttons[3].setVisible(false);
		}
		
		this.repaint();
	}
	
	public void removeButtons(){//去掉屏幕上已经显示的按钮
		for(int i = 0;i<9;i++){
			buttons[i].setVisible(false);
		}
		
	}
	
	public void removeSkip(){
		buttons[10].setVisible(false);
	}
	
	public void directAdd(Player personNow,int i){//添加上下左右按钮,i用于表示执行的具体命令
		Point location = personNow.getRealPoint();//真实坐标
		Point[] points=new Point[]{new Point(location.x+24,location.y+26),new Point(location.x-24,location.y+69),new Point(location.x-36,location.y+39),new Point(location.x+30,location.y+60),new Point(location.x+50,location.y-40)};
		for(i=4;i<9;i++){
			buttons[i].setBounds(points[i-4].x,points[i-4].y,40,40);
			buttons[i].setVisible(true);
		}
		if(commandNow==2){
			if(control.personNow.location.x==0||!control.blocks[control.personNow.location.x-1][control.personNow.location.y].isExist||(control.blocks[control.personNow.location.x-1][control.personNow.location.y].hasPerson()&&!control.personNow.isHide())){
				buttons[6].setVisible(false);
			}if(control.personNow.location.x==14||!control.blocks[control.personNow.location.x+1][control.personNow.location.y].isExist||(control.blocks[control.personNow.location.x+1][control.personNow.location.y].hasPerson()&&!control.personNow.isHide())){
				buttons[7].setVisible(false);
			}if(control.personNow.location.y==0||!control.blocks[control.personNow.location.x][control.personNow.location.y-1].isExist||(control.blocks[control.personNow.location.x][control.personNow.location.y-1].hasPerson()&&!control.personNow.isHide())){
				buttons[4].setVisible(false);
			}if(control.personNow.location.y==14||!control.blocks[control.personNow.location.x][control.personNow.location.y+1].isExist||(control.blocks[control.personNow.location.x][control.personNow.location.y+1].hasPerson()&&!control.personNow.isHide())){
				buttons[5].setVisible(false);
			}
		}else{
			if(control.personNow.location.x==0||!control.blocks[control.personNow.location.x-1][control.personNow.location.y].isExist){
				buttons[6].setVisible(false);
			}else if(control.personNow.location.x==14||!control.blocks[control.personNow.location.x+1][control.personNow.location.y].isExist){
				buttons[7].setVisible(false);
			}else if(control.personNow.location.y==0||!control.blocks[control.personNow.location.x][control.personNow.location.y-1].isExist){
				buttons[4].setVisible(false);
			}else if(control.personNow.location.y==14||!control.blocks[control.personNow.location.x][control.personNow.location.y+1].isExist){
				buttons[5].setVisible(false);
			}
		}
		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(jMainFrame.everyImage.IMG_GAME_BG, 0, 0,JMainFrame.JFRAME_WIDTH, JMainFrame.JFRAME_HIGHT,null);
		
		if(control!=null){
			g.drawImage(jMainFrame.everyImage.IMG_ACTIVITY_RIGHT[control.personNow.getActivity()-1].getImage(), JMainFrame.JFRAME_WIDTH-240, 568,240,200, null);
			g.drawImage(jMainFrame.everyImage.IMG_CHARACTER_HEAD[jMainFrame.getFamily()][control.personNow.rank].getImage(),1024-jMainFrame.everyImage.IMG_CHARACTER_HEAD[0][control.personNow.rank].getIconWidth(), 768-jMainFrame.everyImage.IMG_CHARACTER_HEAD[0][control.personNow.rank].getIconHeight(), null);
		}
		
	}
	
	
	@Override 
	public void update(Graphics g){     //覆盖update方法，截取默认的调用过程  
	    ImageBuffer = createImage(this.getWidth(), this.getHeight());   //创建图形缓冲区  
	    GraImage = ImageBuffer.getGraphics();       //获取图形缓冲区的图形上下文  
	    paint(GraImage);        //用paint方法中编写的绘图过程对图形缓冲区绘图  
	    GraImage.dispose();     //释放图形上下文资源  
	    g.drawImage(ImageBuffer, 0, 0, this);   //将图形缓冲区绘制到屏幕上  
	}  

	
	class ButtonListener extends MouseAdapter implements ActionListener{
		int i;
		private ButtonListener(int i){
			this.i=i;
		}
		
		@Override
		public void mouseEntered(MouseEvent e){
			if(i<4||i>7){
				buttons[i].setLocation(buttons[i].getLocation().x+2, buttons[i].getLocation().y-2);
			}else if(commandNow!=2&&i<9){
				control.personNow.setExample(i-4, true);
				repaint();
			}
			/*if(jFrameGame.isVoice()){
				new AudioPlayer("");
			}*/
		}
		@Override
		public void mouseExited(MouseEvent e){
			if(i<4||i>7){
				buttons[i].setLocation(buttons[i].getLocation().x-2, buttons[i].getLocation().y+2);
			}else if(commandNow!=2&&i<9){
				control.personNow.setExample(i-4, false);
				repaint();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (i) {
			case 0://占领
				removeButtons();
				commandNow = i;
				directAdd(control.personNow,i);
				
				break;
			case 1://骚扰
				removeButtons();
				commandNow = i;
				directAdd(control.personNow,i);
				break;
			case 2://移动
				removeButtons();
				commandNow = i;
				directAdd(control.personNow,i);
				break;
			case 3://隐身或现身
				removeButtons();
				control.personNow.changeState();
				control.refresh();
				break;
			case 4://上
				if(commandNow==0){
					control.personNow.occupy(i-4);
					control.refresh();
				}else if(commandNow==1){
					control.personNow.disturb(i-4);
					control.refresh();
				}else if(commandNow==2){
					control.personNow.move(i-4);
					control.refresh(0);
				}
				repaint();
				break;
			case 5://下
				if(commandNow==0){
					control.personNow.occupy(i-4);
					control.refresh();
				}else if(commandNow==1){
					control.personNow.disturb(i-4);
					control.refresh();
				}else if(commandNow==2){
					control.personNow.move(i-4);
					control.refresh(0);
				}
				repaint();
				break;
			case 6://左
				if(commandNow==0){
					control.personNow.occupy(i-4);
					control.refresh();
				}else if(commandNow==1){
					control.personNow.disturb(i-4);
					control.refresh();
				}else if(commandNow==2){
					control.personNow.move(i-4);
					control.refresh(0);
				}
				repaint();
				break;
			case 7://右
				if(commandNow==0){
					control.personNow.occupy(i-4);
					control.refresh();
				}else if(commandNow==1){
					control.personNow.disturb(i-4);
					control.refresh();
				}else if(commandNow==2){
					control.personNow.move(i-4);
					control.refresh(0);
				}
				repaint();
				break;
			case 8://返回
				removeButtons();
				control.refresh();
				break;
			case 9:
				jps.setVisible(true);
				jps.appearButtons();
				repaint();
				break;
			case 10:
				removeButtons();
				control.nextAi();
			default:
				break;
			}
		}
	}

}