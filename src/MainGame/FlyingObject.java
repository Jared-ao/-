package MainGame;
import java.awt.image.BufferedImage;
/*��������*/
public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;

	/*��������һ��*/
	public abstract void step();
	/*�ж��Ƿ���Խ��,�򷵻�true��ʾԽ��*/
	public abstract boolean outOfBounds();
	
	/*�����ﱻ�ӵ����*/
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
