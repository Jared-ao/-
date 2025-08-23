package MainGame;

import javax.swing.JFrame;

import Function.ScoreRecorder;
import Function.Sound;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;



public class Init {

	public Init() {
		JFrame frame = new JFrame("Plane Wars");//窗口
		MainGame game = new MainGame();//面板
		
		frame.add(game);                                          //添加面板到窗口上
		frame.setSize(MainGame.WIDTH,MainGame.HEIGHT);            //设置窗口宽高
		frame.setResizable(false);                                //不能调整窗口大小
		frame.setAlwaysOnTop(true);                               //设置一直在上面
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //默认关闭方式
		frame.setLocationRelativeTo(null);                        //默认程序在中间显示
		frame.setVisible(true);                                   //程序可视化，自动调用Paint()方法
		Sound.backgroud();                                        //背景音乐初始化
		ScoreRecorder.init();                                     //计分程序初始化
		frame.addWindowListener(new WindowAdapter() {             // 添加窗体监听
            public void windowClosing(WindowEvent e) {            
                ScoreRecorder.saveScore();                        // 窗体关闭前保存分数
            }
        });
		game.action();//启动程序的执行
	}
	
    

}
