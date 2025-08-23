package MainGame;
import java.util.Random;

public class Diamond extends FlyingObject implements Award{
	private int xSpeed = 1;//x步数
	private int ySpeed = 2;//y步数
	private int awardType;//奖励类型值（0或1）
	
	public Diamond(){
		image = MainGame.diamond;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //随机对象
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
        awardType = rand.nextInt(2);//0到1 随机数
	}
	
	public int getType() {
		return awardType;
	}
	
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
    /*钻石越界*/
    public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}

}
