package log;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JOptionPane;

import MainGame.Init;


/**
 * 
 * 登录界面的实现
 * 
 * @author
 */
public class Log extends Obj_Frame implements ActionListener {

	
	Registered reg = new Registered();
	
	Init init; //主程序类
	
	@Override
	public void init() {
		
		frame.setSize(660,780);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false); 
		
		label_user.setBounds(130, 180, 100, 20);
		label_user.setForeground(Color.black);
		label_user.setFont(new Font("方正舒体", Font.BOLD, 15));
		frame.add(label_user);
		label_pwd.setBounds(130, 210, 100, 20);
		label_pwd.setForeground(Color.black);
		label_pwd.setFont(new Font("方正舒体", Font.BOLD, 15));
		frame.add(label_pwd);
		text_user.setBounds(210, 180, 200, 20);
		frame.add(text_user);
		text_psd.setBounds(210, 210, 200, 20);
		frame.add(text_psd);
		
		
		btn_log.addActionListener(this);
		btn_log.setBounds(180, 275, 180, 81);
		frame.add(btn_log);
		btn_reg.addActionListener(this);
		btn_reg.setBounds(180, 370, 180, 81);
		frame.add(btn_reg);
		btn_help.addActionListener(this);
		btn_help.setBounds(180, 465, 180, 81);
		frame.add(btn_help);
		
		label_pho.setIcon(image_pho);
		label_pho.setSize(660, 780);
		frame.add(label_pho);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void log() {
		Statement sql;          //使用Statement对象执行数据库语句
		ResultSet st;           //为了执行 Statement 对象，被发送到数据库的 SQL 语句将被作为参数提供给 Statement 的方法：
		connectMySql.connect(); //链接数据库
		
		
		try {
			sql = connectMySql.con.createStatement();
			st = sql.executeQuery("SELECT * FROM log");
			username = text_user.getText();
			psd = text_psd.getText();
			if(username.length() == 0 ) {
				errorWindow("Error", "账号或密码不能为空，请输入账号或密码");
				text_psd.setText(null);
			}
			else if(username.length() != 6) {
				errorWindow("Error", "账号为6位，请输入正确的账号");
				text_user.setText(null);
				text_psd.setText(null);
			}
			else if(username.length() == 6 && psd.length()==0) {
				errorWindow("Error", "请输入密码");
			}
			else if(!judgePsd(st)) {
				errorWindow("Error", "密码错误或账号不存在,请重新输入！");
				text_psd.setText(null);
			}
			else {
				frame.dispose();
				init = new Init();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	void errorWindow(String title, String message) {
		// TODO 自动生成的方法存根
		/**
		 * 错误信息提示函数
		 * 输入错误信息的类型和要提示的内容
		 */
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/*判断是否密码错误*/
	public boolean judgePsd(ResultSet st) {
	    int flag_User = 0;
	    int flag_Psd = 0;
	    try {
	    	while(st.next()==true) {
				username_sql = st.getString(1);
				psd_sql = st.getString(2);
				if(username_sql.equals(username)) {
					flag_User = 2;
				}
				if(username_sql.equals(username) && psd_sql.equals(psd)) {
					flag_Psd = 3;
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	    return flag_User == 2 && flag_Psd == 3;   
	}
	
	@Override
	//触发button
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == btn_log) {
			log();
		}
		if(e.getSource() == btn_help) {
			frame.dispose();
			Help help = new Help();
			help.init();
		}
		if(e.getSource() == btn_reg) {
			frame.dispose();
			Registered reg = new Registered();
			reg.init();
		}
	}
}


