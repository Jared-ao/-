package MainGame;

import java.awt.image.BufferedImage;

public class Sea {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	private BufferedImage[] images;//ͼƬ����
	private int index;//ͼƬ�л�
	
	Sea(){
		image = MainGame.sea0;
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = MainGame.Background.getHeight()-this.height;
		images = new BufferedImage [] {MainGame.sea0,MainGame.sea1,MainGame.sea2};
		index = 0;//ͼƬ�л�����
	}
	
	public  void step() {
		image = images[index++/10%images.length];
	}
	
	/*��ˮ����Խ��*/
	public boolean outOfBounds() {
		return false;
	}
}
