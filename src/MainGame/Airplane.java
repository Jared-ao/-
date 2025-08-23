package MainGame;
import java.util.Random;

/*敌机*/
public class Airplane extends FlyingObject implements Enemy{
	public int getScore() {
		return 5;
	}
	private int speed = 3;//步数
	
	public Airplane() {
		image = MainGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();   //随机对象
        x = rand.nextInt(MainGame.WIDTH-this.width+1);
        y = -this.height;   
	}
    
	/*重写step()方法*/
	public void step() {
		y += speed;       //敌机y坐标向下
	}
    
	/*重写outOfBounds()判断敌机是否越界*/
	public boolean outOfBounds() {
		return this.y>=MainGame.HEIGHT;
	}
}
