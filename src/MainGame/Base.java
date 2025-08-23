package MainGame;
import java.awt.image.BufferedImage;

/*��ұ�������*/
public class Base{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	private int speed = 1;//����
	private int life;//����ֵ
	private BufferedImage[] images;//ͼƬ����
	private int index;//ͼƬ�л�
	
	public Base() {
		
		image = MainGame.Base0;
		width = image.getWidth();
		height = image.getHeight();
		x = MainGame.Background.getWidth()/2-MainGame.Base0.getWidth()/2;   //��ʼʱΪ�̶�ֵ
		y = MainGame.Background.getHeight()-MainGame.Base0.getHeight();
		life = 5;  //Ĭ������ֵΪ3
		images = new BufferedImage [] {MainGame.Base0,MainGame.Base1};
		index = 0;//ͼƬ�л�����
		
	}
	
	/*ÿ10������һ��,����ˮƽ�ƶ�*/
	public  void step() {
		image = images[index++/10%images.length];
		x+=speed;
		if(x>=MainGame.WIDTH-this.width) {
			speed = -1;
		}
		if(x<=0) {
			speed = 1;
		}
	}
	
	/*���ز���Խ��*/
	public boolean outOfBounds() {
		return false;
	}

	public void addLife() {
		life++; //������+1
	}
	public void substractLife() {
		life--;//������-1
	}
	public int getLife() {
		return life;
	}
	
	/*����ײ�ϵ���*/
	public boolean hit(FlyingObject obj) {
		int x1 = obj.x-this.width/2;
		int x2 = obj.x+obj.width+this.width/2;
		int y1 = obj.y-this.height/2;
		int y2 = obj.y+obj.height/2;
		int x = this.x+this.width/2;
		int y = this.y+this.height/2;
		
		return x>=x1 && x<=x2 && y>=y1 && y<=y2;
		
	}
}

