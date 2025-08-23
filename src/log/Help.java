package log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Help extends Obj_Frame implements ActionListener {
	
	public void init() {
		frame.setSize(550,650);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		btn_back.addActionListener(this);
		btn_back.setBounds(225, 570, 80, 30);
		frame.add(btn_back);
		
		
		label_rule.setIcon(image_rule);
		label_rule.setSize(550, 650);
		frame.add(label_rule);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == btn_back) {
			frame.dispose();
			Log log = new Log();
			log.init();
		}

	}
	@Override
	void errorWindow(String title, String message) {
		// TODO 自动生成的方法存根
	}
}
