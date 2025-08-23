package MainGame;
import java.awt.image.BufferedImage;

/*���*/
public class Hero extends FlyingObject{
	private int life;//����ֵ
	private int doubleFire;//����ֵ
	private BufferedImage[] images;//ͼƬ����
	private int index;//ͼƬ�л�
	
	public Hero() {
		image = MainGame.hero1;
		width = image.getWidth();
		height = image.getHeight();
		x = 225;   //��ʼʱΪ�̶�ֵ
		y = 400;
		life = 3;  //Ĭ������ֵΪ3
		doubleFire = 0;//Ĭ�ϻ���ֵΪ��������
		images = new BufferedImage [] {MainGame.hero1,MainGame.hero2};
		index = 0;
	}
	
	/*ÿ10������һ��*/
	public  void step() {
		image = images[index++/10%images.length];
//		index++;
//		int a = index/10;
//		int b = a%2;
//		image = images[b];
	}
	/*�ӵ�����״̬*/
	public Bullet[] shoot() {
		int xStep = this.width/4;
		int yStep = 20;
		if(doubleFire>0) {
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x+1*xStep-2,this.y-yStep);
			bs[1] = new Bullet(this.x+3*xStep,this.y-yStep);
			doubleFire -= 2;
			return bs;
		}else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xStep-2,this.y-yStep);
			return bs;
		}
		
	}

	/*���������ƶ�*/
	public void moveTo(int x,int y) {
		this.x = x-this.width/2;
		this.y = y-this.height/2;
	}
	
	/*��Ҳ�Խ��*/
	public boolean outOfBounds() {
		return false;
	}
	
	//������+1
	public void addLife() {
		life++; 
	}
	//������-1
	public void substractLife() {
		life--;
	}
	/*��װ*/
	public int getLife() {
		return life;
	}
	/*���ӻ���*/
	public void addDoubleFire() {
		doubleFire+=40; //�ӻ���ֵ
	}
    /*�������*/
	public void clearDoubleFire() {
		doubleFire = 0;
	}
	/*���ײ�ϵ���*/
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
