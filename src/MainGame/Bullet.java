package MainGame;


/*�ӵ�*/
public class Bullet extends FlyingObject{
	private int speed  = 3;//�߹��Ĳ���
	
    public void step() {
		y -= speed;//�ӵ��켣y��������
	}
    
	public Bullet(int x,int y) {
		/*�ӵ��������ս��*/
		image = MainGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
	
	/*�ж��ӵ��Ƿ�Խ��*/
	public boolean outOfBounds() {
		return this.y<=-this.height;
	}
}
