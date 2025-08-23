package MainGame;

import java.util.Random;

public class Heart extends FlyingObject implements Award{
	private int xSpeed = 2;//x步数
	private int ySpeed = 3;//y步数
	private int awardType;
	
	public Heart(){
		image = MainGame.heart;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //随机对象
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
        awardType = 3;//奖励类型第三种，增加基地生命值
	}
	
	/*奖励类型*/
	public int getType() {
		return awardType;
	}
	
	/*Heart位移*/
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
    /*红桃越界*/
    public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}

}

