package MainGame;
import java.awt.image.BufferedImage;
/*飞行物类*/
public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;

	/*飞行物走一步*/
	public abstract void step();
	/*判断是否若越界,则返回true表示越界*/
	public abstract boolean outOfBounds();
	
	/*飞行物被子弹射击*/
	public boolean shootBy(Bullet bullet) {
		int x1 = this.x;
		int x2 = this.x+this.width;
		int y1 = this.y;
		int y2 = this.y+this.height;
		int x = bullet.x;
		int y = bullet.y;
		return x>x1 && x<x2 && y>y1 && y<y2;
	}
}
