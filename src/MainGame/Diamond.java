package MainGame;
import java.util.Random;

public class Diamond extends FlyingObject implements Award{
	private int xSpeed = 1;//x����
	private int ySpeed = 2;//y����
	private int awardType;//��������ֵ��0��1��
	
	public Diamond(){
		image = MainGame.diamond;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //�������
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
        awardType = rand.nextInt(2);//0��1 �����
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
    /*��ʯԽ��*/
    public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}

}
