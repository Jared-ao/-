package MainGame;

import javax.swing.JFrame;

import Function.ScoreRecorder;
import Function.Sound;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;



public class Init {

	public Init() {
		JFrame frame = new JFrame("Plane Wars");//����
		MainGame game = new MainGame();//���
		
		frame.add(game);                                          //�����嵽������
		frame.setSize(MainGame.WIDTH,MainGame.HEIGHT);            //���ô��ڿ��
		frame.setResizable(false);                                //���ܵ������ڴ�С
		frame.setAlwaysOnTop(true);                               //����һֱ������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Ĭ�Ϲرշ�ʽ
		frame.setLocationRelativeTo(null);                        //Ĭ�ϳ������м���ʾ
		frame.setVisible(true);                                   //������ӻ����Զ�����Paint()����
		Sound.backgroud();                                        //�������ֳ�ʼ��
		ScoreRecorder.init();                                     //�Ʒֳ����ʼ��
		frame.addWindowListener(new WindowAdapter() {             // ��Ӵ������
            public void windowClosing(WindowEvent e) {            
                ScoreRecorder.saveScore();                        // ����ر�ǰ�������
            }
        });
		game.action();//���������ִ��
	}
	
    

}
