package MainGame;


/*子弹*/
public class Bullet extends FlyingObject{
	private int speed  = 3;//走过的步数
	
    public void step() {
		y -= speed;//子弹轨迹y坐标向上
	}
    
	public Bullet(int x,int y) {
		/*子弹跟随玩家战机*/
		image = MainGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
	
	/*判断子弹是否越界*/
	public boolean outOfBounds() {
		return this.y<=-this.height;
	}
}
