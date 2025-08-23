package MainGame;
import java.awt.image.BufferedImage;

/*玩家保护基地*/
public class Base{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	private int speed = 1;//步数
	private int life;//生命值
	private BufferedImage[] images;//图片数组
	private int index;//图片切换
	
	public Base() {
		
		image = MainGame.Base0;
		width = image.getWidth();
		height = image.getHeight();
		x = MainGame.Background.getWidth()/2-MainGame.Base0.getWidth()/2;   //初始时为固定值
		y = MainGame.Background.getHeight()-MainGame.Base0.getHeight();
		life = 5;  //默认生命值为3
		images = new BufferedImage [] {MainGame.Base0,MainGame.Base1};
		index = 0;//图片切换索引
		
	}
	
	/*每10毫秒走一次,基地水平移动*/
	public  void step() {
		image = images[index++/10%images.length];
		x+=speed;
		if(x>=MainGame.WIDTH-this.width) {
			speed = -1;
		}
		if(x<=0) {
			speed = 1;
		}
	}
	
	/*基地不会越界*/
	public boolean outOfBounds() {
		return false;
	}

	public void addLife() {
		life++; //加生命+1
	}
	public void substractLife() {
		life--;//减生命-1
	}
	public int getLife() {
		return life;
	}
	
	/*基地撞上敌人*/
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

