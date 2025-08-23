package MainGame;
import java.util.Random;

/*�л�*/
public class Airplane extends FlyingObject implements Enemy{
	public int getScore() {
		return 5;
	}
	private int speed = 3;//����
	
	public Airplane() {
		image = MainGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //�������
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
	}
    
	/*��дstep()����*/
	public void step() {
		y += speed;       //�л�y��������
	}
    
	/*��дoutOfBounds()�жϵл��Ƿ�Խ��*/
	public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}
}
