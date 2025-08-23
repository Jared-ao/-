package MainGame;

import java.awt.image.BufferedImage;
import java.util.Random;

/*敌机*/
public class BossAirPlane extends FlyingObject implements Enemy{
	public int getScore() {
		return 50;
	}
	private int speed = 9;//步数
	private BufferedImage[] images;//图片数组
	private int index;//图片切换
	
	public BossAirPlane() {
		image = MainGame.BossAirPlane0;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //随机对象
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        images = new BufferedImage [] {MainGame.BossAirPlane0,MainGame.BossAirPlane1};
        y = -this.height;   
	}
    
	/*重写step()方法*/
	public void step() {
		image = images[index++/10%images.length];
		y += speed;       //敌机y坐标向下
	}
    
	/*重写outOfBounds()判断敌机是否越界*/
	public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}
}
