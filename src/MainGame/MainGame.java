package MainGame;

import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Function.ScoreRecorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;       //定时器类
import java.util.TimerTask;   //定时任务
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import log.Obj_Frame;
//主程序
public class MainGame extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 550;//窗口宽大小
	public static final int HEIGHT = 689;//窗口高大小
	
	
	public static BufferedImage Background;//静态窗口背景
	public static BufferedImage Start;     
	public static BufferedImage pause;     
	public static BufferedImage gameover;  
	public static BufferedImage airplane;  
	public static BufferedImage airplane2; 
	public static BufferedImage BossAirPlane0; 
	public static BufferedImage BossAirPlane1;
	public static BufferedImage diamond;   
	public static BufferedImage heart; 
	public static BufferedImage bullet;    //子弹
	public static BufferedImage hero1;     //玩家
	public static BufferedImage hero2;     
	public static BufferedImage Base0;
	public static BufferedImage Base1;    
	public static BufferedImage sea0;
	public static BufferedImage sea1;
	public static BufferedImage sea2;
	
	/*状态*/
	public static final int START = 0;      //开始
	public static final int RUNNING = 1;    //运行
	public static final int PAUSE = 2;      //暂停
	public static final int GAME_OVER =  3; //结束
	private int state = START;              //默认当前状态
	
	public int getState() {                 //封装
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
    /*对象*/
	private Hero hero = new Hero();//玩家对象
	private Sea sea = new Sea();
	private Base base = new Base();//基地对象
	private FlyingObject[] flyings = {};//敌人（可攻击对象）对象
	private Bullet[] bullets = {};//子弹对象

	
	static {//初始化静态资源图片
		try {//异常处理
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
	
	/*出现敌人的概率*/
	public FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(100); //0到99之间的随机数
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
	
	/*敌人入场*/
	int flyEnteredIndex = 0;
	public void enterdAction() {
		flyEnteredIndex++;
		if(flyEnteredIndex%40==0) {
			FlyingObject one = nextOne();
			flyings = Arrays.copyOf(flyings, flyings.length+1);
			flyings[flyings.length-1]=one;//将敌人放入flyings数组最后一个
		}
	}
	
	/*可移动对象:敌人和奖励*/
	public void stepAction() {    //10毫秒走一次
		hero.step();              //玩家走一步
		for(int i = 0;i<flyings.length;i++) {
			flyings[i].step();    //敌机走一步
		}
		for(int i=0;i<bullets.length;i++) {
			bullets[i].step();    //子弹走一步
		}
		base.step();
		sea.step();
	}
	
	/*射击动作,子弹入场*/
	int shootIndex = 0;
	public void shootAction() {
		shootIndex++;
		if(shootIndex%30==0) {
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
		}
	}
	
	/*删除越界的物体*/
	public void outOfBoundsAction() {
		int index = 0; //1.不越界敌人个数
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i<flyings.length;i++){
			FlyingObject f = flyings[i]; //获取敌人
			if(!f.outOfBounds()) {  //若不越界
				flyingLives[index] = f; 
				index++;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);
	}
	
	/*子弹击中敌人*/
	public void bangAction() {
		for(int i=0;i<bullets.length;i++) {
			Bullet b = bullets[i]; //获取每一个子弹
			bang(b);
		}
	}
	int score = 0; //得分
	public void bang(Bullet b){
		int index = -1;
		for(int i=0;i<flyings.length;i++) {
			FlyingObject f = flyings[i];
			if(f.shootBy(b)) {
				index = i;  //记录被击中的敌人
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
			/*将被打到的敌人与数组最后一个元素交换*/
			FlyingObject t = flyings[index];   
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1] = t;
			/*缩容(去掉最后一个敌人对象)*/
			flyings = Arrays.copyOf(flyings,flyings.length-1);
		}
	}
	
	/*检查游戏是否结束*/
	public void checkGameOverAction() {
		if(isGameOver()) {
			ScoreRecorder.addNewScore(score);
			setState(GAME_OVER);
		}
	}
	public boolean isGameOver() {
		for(int i=0;i<flyings.length;i++) {
			FlyingObject f = flyings[i];  // 获取敌人
			if(hero.hit(f)) {     // 如果玩家撞上敌机或钻石
				hero.substractLife();//撞上则玩家生命值减1
				hero.clearDoubleFire();//英雄机清空火力值
				
				/*将被撞敌人与数组最后一个元素交换*/
				FlyingObject t = flyings[i];   
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				/*缩容(去掉最后一个敌人对象)*/
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
			if(base.hit(f)) {     // 如果基地撞上飞行物或玩家
				base.substractLife();//撞上则基地玩家生命值减1
				
				/*将被撞敌人与数组最后一个元素交换*/
				FlyingObject t = flyings[i];   
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				/*缩容(去掉最后一个敌人对象)*/
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
		return hero.getLife()<=0||base.getLife()<=0;//玩家生命值小于等于0则游戏结束
	}
	/*启动程序*/
	public void action() {
		/*创建侦听器事件对象*/
		MouseAdapter l = new MouseAdapter() {
			/*重写鼠标移动事件*/
			public void mouseMoved(MouseEvent e) {
				if(getState() == RUNNING) {
					int x = e.getX();
				    int y = e.getY();
				    hero.moveTo(x, y);
				}
			}
			/*重写鼠标点击事件*/
			public void mouseClicked(MouseEvent e) {
				switch(getState()) {
				case START:
					setState(RUNNING);
					break;
				case GAME_OVER:
					score = 0;                     //分数归零;
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
			/*重写鼠标溢出事件*/
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
		
		Timer timer = new Timer();//创建定时器
		int intervel = 6;    //时间间隔（毫秒为单位）
		timer.schedule(new TimerTask() {
			public void run() {     //定时做事
				if(getState() == RUNNING) {
					enterdAction(); //敌人入场	
					stepAction();   //飞机走一步
					shootAction();  //子弹入场()
					outOfBoundsAction(); //删除越界的敌人
				    bangAction();   //击中敌人(敌机+奖励)
				    checkGameOverAction();//检查游戏是否结束
				}
				repaint();//重画，调用paint()方法,
			}
		}, intervel,intervel);
	}
	
	/*重写paint()*/
	public void paint(Graphics g) { 
		g.drawImage(Background,0,0,null);//画背景图
		paintHero(g);
		paintBase(g);
		paintSea(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScoreAndLife(g);
		paintState(g);
	}
	
	/*画玩家对象*/
	public void paintHero(Graphics g) {
		
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	/*画海洋对象*/
	public void paintSea(Graphics g) {
		g.drawImage(sea.image,sea.x,sea.y,null);
	}
	/*画基地对象*/
	public void paintBase(Graphics g) {
		g.drawImage(base.image,base.x,base.y,null);
	}
	/*画可击中对象*/
	public void paintFlyingObjects(Graphics g) {
		for(int i=0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			g.drawImage(f.image,f.x,f.y,null); //画目标
		}
	}
	/*画子弹对象*/
	public void paintBullets(Graphics g) {
		for(int i=0;i<bullets.length;i++) {
			Bullet b = bullets[i];
			g.drawImage(b.image,b.x,b.y,null); 
		}	
	}
	/*画游戏中的分数和生命值*/
	public void paintScoreAndLife(Graphics g) {
		if(getState() == RUNNING) {	
			g.setColor(new Color(255,128,64));
		    g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
		    g.drawString("SCORE:"+score,10,25);
		    g.drawString("HERO LIFE:"+hero.getLife(),10,50);
		    g.drawString("Base LIFE:"+base.getLife(),10,75);
		}
	}
	
	
	/*画状态*/
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
		    
		    /*显示结果分数,以及前三名*/
		    int G_Scores[] = ScoreRecorder.getScores();// 获取当前前三名成绩
		    g.setColor(new Color(0,0,0));
		    g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		    g.drawString(" "+G_Scores[2],345,463);
		    g.drawString(" "+G_Scores[1],345,497);
		    g.drawString(" "+G_Scores[0],345,532);
			break;
		}
	}

	
	
}
