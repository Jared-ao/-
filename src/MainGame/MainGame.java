package MainGame;

import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Function.ScoreRecorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;       //��ʱ����
import java.util.TimerTask;   //��ʱ����
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import log.Obj_Frame;
//������
public class MainGame extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 550;//���ڿ��С
	public static final int HEIGHT = 689;//���ڸߴ�С
	
	
	public static BufferedImage Background;//��̬���ڱ���
	public static BufferedImage Start;     
	public static BufferedImage pause;     
	public static BufferedImage gameover;  
	public static BufferedImage airplane;  
	public static BufferedImage airplane2; 
	public static BufferedImage BossAirPlane0; 
	public static BufferedImage BossAirPlane1;
	public static BufferedImage diamond;   
	public static BufferedImage heart; 
	public static BufferedImage bullet;    //�ӵ�
	public static BufferedImage hero1;     //���
	public static BufferedImage hero2;     
	public static BufferedImage Base0;
	public static BufferedImage Base1;    
	public static BufferedImage sea0;
	public static BufferedImage sea1;
	public static BufferedImage sea2;
	
	/*״̬*/
	public static final int START = 0;      //��ʼ
	public static final int RUNNING = 1;    //����
	public static final int PAUSE = 2;      //��ͣ
	public static final int GAME_OVER =  3; //����
	private int state = START;              //Ĭ�ϵ�ǰ״̬
	
	public int getState() {                 //��װ
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
    /*����*/
	private Hero hero = new Hero();//��Ҷ���
	private Sea sea = new Sea();
	private Base base = new Base();//���ض���
	private FlyingObject[] flyings = {};//���ˣ��ɹ������󣩶���
	private Bullet[] bullets = {};//�ӵ�����

	
	static {//��ʼ����̬��ԴͼƬ
		try {//�쳣����
			Background = ImageIO.read(MainGame.class.getResource("background.png"));
			Start = ImageIO.read(MainGame.class.getResource("Start.png"));
			pause = ImageIO.read(MainGame.class.getResource("Pause.png"));
			gameover = ImageIO.read(MainGame.class.getResource("gameover.png"));
			airplane = ImageIO.read(MainGame.class.getResource("airplane.png"));
			airplane2 = ImageIO.read(MainGame.class.getResource("airplane2.png"));
			BossAirPlane0 = ImageIO.read(MainGame.class.getResource("BossAirPlane.png"));
			BossAirPlane1 = ImageIO.read(MainGame.class.getResource("BossAirPlane1.png"));
			diamond = ImageIO.read(MainGame.class.getResource("diamond.png"));
			heart = ImageIO.read(MainGame.class.getResource("heart.png"));
			bullet = ImageIO.read(MainGame.class.getResource("bullet.png"));
			hero1 = ImageIO.read(MainGame.class.getResource("hero1.png"));
			hero2 = ImageIO.read(MainGame.class.getResource("hero2.png"));
			Base0 = ImageIO.read(MainGame.class.getResource("Base0.png"));
			Base1 = ImageIO.read(MainGame.class.getResource("Base1.png"));
			sea0 = ImageIO.read(MainGame.class.getResource("sea0.png"));
			sea1 = ImageIO.read(MainGame.class.getResource("sea1.png"));
			sea2 = ImageIO.read(MainGame.class.getResource("sea2.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*���ֵ��˵ĸ���*/
	public FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(100); //0��99֮��������
		if(type <6){
			return new Diamond();
		}
		else if(type>=30&&type<=34){
			return new Heart();
		}
		else if(type>=10&&type<30) {
			return new Airplane2();
		}
		else if(type>=95&&type<=99) {
			return new BossAirPlane();
		}
		else {
			return new Airplane();
		}
	}
	
	/*�����볡*/
	int flyEnteredIndex = 0;
	public void enterdAction() {
		flyEnteredIndex++;
		if(flyEnteredIndex%40==0) {
			FlyingObject one = nextOne();
			flyings = Arrays.copyOf(flyings, flyings.length+1);
			flyings[flyings.length-1]=one;//�����˷���flyings�������һ��
		}
	}
	
	/*���ƶ�����:���˺ͽ���*/
	public void stepAction() {    //10������һ��
		hero.step();              //�����һ��
		for(int i = 0;i<flyings.length;i++) {
			flyings[i].step();    //�л���һ��
		}
		for(int i=0;i<bullets.length;i++) {
			bullets[i].step();    //�ӵ���һ��
		}
		base.step();
		sea.step();
	}
	
	/*�������,�ӵ��볡*/
	int shootIndex = 0;
	public void shootAction() {
		shootIndex++;
		if(shootIndex%30==0) {
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
		}
	}
	
	/*ɾ��Խ�������*/
	public void outOfBoundsAction() {
		int index = 0; //1.��Խ����˸���
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i<flyings.length;i++){
			FlyingObject f = flyings[i]; //��ȡ����
			if(!f.outOfBounds()) {  //����Խ��
				flyingLives[index] = f; 
				index++;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);
	}
	
	/*�ӵ����е���*/
	public void bangAction() {
		for(int i=0;i<bullets.length;i++) {
			Bullet b = bullets[i]; //��ȡÿһ���ӵ�
			bang(b);
		}
	}
	int score = 0; //�÷�
	public void bang(Bullet b){
		int index = -1;
		for(int i=0;i<flyings.length;i++) {
			FlyingObject f = flyings[i];
			if(f.shootBy(b)) {
				index = i;  //��¼�����еĵ���
				break;
			}
		}
		if(index != -1) {
			FlyingObject one = flyings[index];
			if(one instanceof Enemy) {
				Enemy e = (Enemy)one;
				score += e.getScore();
			}
			if(one instanceof Award) {
				Award a = (Award)one;
				int type = a.getType();
				switch(type) {
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				case Award.BASE_LIFE:
					base.addLife();
					break;
				}
			}
			/*�����򵽵ĵ������������һ��Ԫ�ؽ���*/
			FlyingObject t = flyings[index];   
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1] = t;
			/*����(ȥ�����һ�����˶���)*/
			flyings = Arrays.copyOf(flyings,flyings.length-1);
		}
	}
	
	/*�����Ϸ�Ƿ����*/
	public void checkGameOverAction() {
		if(isGameOver()) {
			ScoreRecorder.addNewScore(score);
			setState(GAME_OVER);
		}
	}
	public boolean isGameOver() {
		for(int i=0;i<flyings.length;i++) {
			FlyingObject f = flyings[i];  // ��ȡ����
			if(hero.hit(f)) {     // ������ײ�ϵл�����ʯ
				hero.substractLife();//ײ�����������ֵ��1
				hero.clearDoubleFire();//Ӣ�ۻ���ջ���ֵ
				
				/*����ײ�������������һ��Ԫ�ؽ���*/
				FlyingObject t = flyings[i];   
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				/*����(ȥ�����һ�����˶���)*/
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
			if(base.hit(f)) {     // �������ײ�Ϸ���������
				base.substractLife();//ײ��������������ֵ��1
				
				/*����ײ�������������һ��Ԫ�ؽ���*/
				FlyingObject t = flyings[i];   
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				/*����(ȥ�����һ�����˶���)*/
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
		return hero.getLife()<=0||base.getLife()<=0;//�������ֵС�ڵ���0����Ϸ����
	}
	/*��������*/
	public void action() {
		/*�����������¼�����*/
		MouseAdapter l = new MouseAdapter() {
			/*��д����ƶ��¼�*/
			public void mouseMoved(MouseEvent e) {
				if(getState() == RUNNING) {
					int x = e.getX();
				    int y = e.getY();
				    hero.moveTo(x, y);
				}
			}
			/*��д������¼�*/
			public void mouseClicked(MouseEvent e) {
				switch(getState()) {
				case START:
					setState(RUNNING);
					break;
				case GAME_OVER:
					score = 0;                     //��������;
					hero = new Hero();
					base = new Base();
					sea = new Sea();
					flyings=new FlyingObject[0];
					bullets = new Bullet[0];
					setState(START);
					setState(START);
					break;
				}
				
			}
			/*��д�������¼�*/
			public void mouseExited(MouseEvent e) {
				if(getState() == RUNNING) {
					setState(PAUSE);
				}
			}
			
			public void mouseEntered(MouseEvent e) {
				if(getState() == PAUSE) {
					setState(RUNNING);
				}
			}
			
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		
		Timer timer = new Timer();//������ʱ��
		int intervel = 6;    //ʱ����������Ϊ��λ��
		timer.schedule(new TimerTask() {
			public void run() {     //��ʱ����
				if(getState() == RUNNING) {
					enterdAction(); //�����볡	
					stepAction();   //�ɻ���һ��
					shootAction();  //�ӵ��볡()
					outOfBoundsAction(); //ɾ��Խ��ĵ���
				    bangAction();   //���е���(�л�+����)
				    checkGameOverAction();//�����Ϸ�Ƿ����
				}
				repaint();//�ػ�������paint()����,
			}
		}, intervel,intervel);
	}
	
	/*��дpaint()*/
	public void paint(Graphics g) { 
		g.drawImage(Background,0,0,null);//������ͼ
		paintHero(g);
		paintBase(g);
		paintSea(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScoreAndLife(g);
		paintState(g);
	}
	
	/*����Ҷ���*/
	public void paintHero(Graphics g) {
		
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	/*���������*/
	public void paintSea(Graphics g) {
		g.drawImage(sea.image,sea.x,sea.y,null);
	}
	/*�����ض���*/
	public void paintBase(Graphics g) {
		g.drawImage(base.image,base.x,base.y,null);
	}
	/*���ɻ��ж���*/
	public void paintFlyingObjects(Graphics g) {
		for(int i=0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			g.drawImage(f.image,f.x,f.y,null); //��Ŀ��
		}
	}
	/*���ӵ�����*/
	public void paintBullets(Graphics g) {
		for(int i=0;i<bullets.length;i++) {
			Bullet b = bullets[i];
			g.drawImage(b.image,b.x,b.y,null); 
		}	
	}
	/*����Ϸ�еķ���������ֵ*/
	public void paintScoreAndLife(Graphics g) {
		if(getState() == RUNNING) {	
			g.setColor(new Color(255,128,64));
		    g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
		    g.drawString("SCORE:"+score,10,25);
		    g.drawString("HERO LIFE:"+hero.getLife(),10,50);
		    g.drawString("Base LIFE:"+base.getLife(),10,75);
		}
	}
	
	
	/*��״̬*/
	public void paintState(Graphics g) {
		switch(getState()) {
		case START:
			g.drawImage(Start,75,0,null);
			break;
		case PAUSE:
			g.drawImage(pause,187,287,null);
			break;
		case GAME_OVER:
			g.drawImage(gameover,0,0,null);
			g.setColor(new Color(255,128,64));
		    g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,72));
		    g.drawString(""+score,235,320);
		    
		    /*��ʾ�������,�Լ�ǰ����*/
		    int G_Scores[] = ScoreRecorder.getScores();// ��ȡ��ǰǰ�����ɼ�
		    g.setColor(new Color(0,0,0));
		    g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		    g.drawString(" "+G_Scores[2],345,463);
		    g.drawString(" "+G_Scores[1],345,497);
		    g.drawString(" "+G_Scores[0],345,532);
			break;
		}
	}

	
	
}
