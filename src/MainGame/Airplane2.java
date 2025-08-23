package MainGame;

import java.util.Random;

/*第二类型的敌机*/
public class Airplane2 extends FlyingObject implements Enemy{
	public int getScore() {
		return 10;
	}
	private int xSpeed = 3;//x步数
	private int ySpeed = 8;//y步数
	
	public Airplane2() {
		image = MainGame.airplane2;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //随机对象
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
	}
    
	/*重写step()方法*/
	public void step() {
		x+=xSpeed;
		y+=ySpeed;
		if(x>=MainGame.WIDTH-this.width) {
			xSpeed = -1;
		}
		if(x<=0) {
			xSpeed = 1;
		}
	}
    
	/*重写outOfBounds，判断敌机是否越界*/
	public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}
}