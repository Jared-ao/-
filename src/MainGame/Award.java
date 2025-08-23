package MainGame;
/*奖励*/
public interface Award {
 	public int DOUBLE_FIRE = 0; //火力值
    public int LIFE = 1;        //玩家生命值
    public int BASE_LIFE = 3;   //基地生命值
	
    /*获得奖励类型*/
    public int getType();
}
