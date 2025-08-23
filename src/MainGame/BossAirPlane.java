package MainGame;

import java.awt.image.BufferedImage;
import java.util.Random;

/*�л�*/
public class BossAirPlane extends FlyingObject implements Enemy{
	public int getScore() {
		return 50;
	}
	private int speed = 9;//����
	private BufferedImage[] images;//ͼƬ����
	private int index;//ͼƬ�л�
	
	public BossAirPlane() {
		image = MainGame.BossAirPlane0;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //�������
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        images = new BufferedImage [] {MainGame.BossAirPlane0,MainGame.BossAirPlane1};
        y = -this.height;   
	}
    
	/*��дstep()����*/
	public void step() {
		image = images[index++/10%images.length];
		y += speed;       //�л�y��������
	}
    
	/*��дoutOfBounds()�жϵл��Ƿ�Խ��*/
	public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}
}
