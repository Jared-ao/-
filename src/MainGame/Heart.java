package MainGame;

import java.util.Random;

public class Heart extends FlyingObject implements Award{
	private int xSpeed = 2;//x����
	private int ySpeed = 3;//y����
	private int awardType;
	
	public Heart(){
		image = MainGame.heart;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //�������
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
        awardType = 3;//�������͵����֣����ӻ�������ֵ
	}
	
	/*��������*/
	public int getType() {
		return awardType;
	}
	
	/*Heartλ��*/
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
    /*����Խ��*/
    public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}

}

