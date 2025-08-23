package MainGame;
import java.awt.image.BufferedImage;

/*玩家*/
public class Hero extends FlyingObject{
	private int life;//生命值
	private int doubleFire;//火力值
	private BufferedImage[] images;//图片数组
	private int index;//图片切换
	
	public Hero() {
		image = MainGame.hero1;
		width = image.getWidth();
		height = image.getHeight();
		x = 225;   //初始时为固定值
		y = 400;
		life = 3;  //默认生命值为3
		doubleFire = 0;//默认火力值为单倍火力
		images = new BufferedImage [] {MainGame.hero1,MainGame.hero2};
		index = 0;
	}
	
	/*每10毫秒走一次*/
	public  void step() {
		image = images[index++/10%images.length];
//		index++;
//		int a = index/10;
//		int b = a%2;
//		image = images[b];
	}
	/*子弹发射状态*/
	public Bullet[] shoot() {
		int xStep = this.width/4;
		int yStep = 20;
		if(doubleFire>0) {
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x+1*xStep-2,this.y-yStep);
			bs[1] = new Bullet(this.x+3*xStep,this.y-yStep);
			doubleFire -= 2;
			return bs;
		}else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xStep-2,this.y-yStep);
			return bs;
		}
		
	}

	/*玩家随鼠标移动*/
	public void moveTo(int x,int y) {
		this.x = x-this.width/2;
		this.y = y-this.height/2;
	}
	
	/*玩家不越界*/
	public boolean outOfBounds() {
		return false;
	}
	
	//加生命+1
	public void addLife() {
		life++; 
	}
	//减生命-1
	public void substractLife() {
		life--;
	}
	/*封装*/
	public int getLife() {
		return life;
	}
	/*增加火力*/
	public void addDoubleFire() {
		doubleFire+=40; //加火力值
	}
    /*清除活力*/
	public void clearDoubleFire() {
		doubleFire = 0;
	}
	/*玩家撞上敌人*/
	public boolean hit(FlyingObject obj) {
		int x1 = obj.x-this.width/2;
		int x2 = obj.x+obj.width+this.width/2;
		int y1 = obj.y-this.height/2;
		int y2 = obj.y+obj.height/2;
		int x = this.x+this.width/2;
		int y = this.y+this.height/2;
		
		return x>=x1 && x<=x2 && y>=y1 && y<=y2;
		
	}
}
