package MainGame;

import java.awt.image.BufferedImage;

public class Sea {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	private BufferedImage[] images;//图片数组
	private int index;//图片切换
	
	Sea(){
		image = MainGame.sea0;
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = MainGame.Background.getHeight()-this.height;
		images = new BufferedImage [] {MainGame.sea0,MainGame.sea1,MainGame.sea2};
		index = 0;//图片切换索引
	}
	
	public  void step() {
		image = images[index++/10%images.length];
	}
	
	/*海水不会越界*/
	public boolean outOfBounds() {
		return false;
	}
}
