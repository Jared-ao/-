package MainGame;

import java.util.Random;

/*�ڶ����͵ĵл�*/
public class Airplane2 extends FlyingObject implements Enemy{
	public int getScore() {
		return 10;
	}
	private int xSpeed = 3;//x����
	private int ySpeed = 8;//y����
	
	public Airplane2() {
		image = MainGame.airplane2;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //�������
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
	}
    
	/*��дstep()����*/
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
    
	/*��дoutOfBounds���жϵл��Ƿ�Խ��*/
	public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}
}