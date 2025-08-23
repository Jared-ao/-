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
 * ��¼�����ʵ��
 * 
 * @author
 */
public class Log extends Obj_Frame implements ActionListener {

	
	Registered reg = new Registered();
	
	Init init; //��������
	
	@Override
	public void init() {
		
		frame.setSize(660,780);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false); 
		
		label_user.setBounds(130, 180, 100, 20);
		label_user.setForeground(Color.black);
		label_user.setFont(new Font("��������", Font.BOLD, 15));
		frame.add(label_user);
		label_pwd.setBounds(130, 210, 100, 20);
		label_pwd.setForeground(Color.black);
		label_pwd.setFont(new Font("��������", Font.BOLD, 15));
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
		Statement sql;          //ʹ��Statement����ִ�����ݿ����
		ResultSet st;           //Ϊ��ִ�� Statement ���󣬱����͵����ݿ�� SQL ��佫����Ϊ�����ṩ�� Statement �ķ�����
		connectMySql.connect(); //�������ݿ�
		
		
		try {
			sql = connectMySql.con.createStatement();
			st = sql.executeQuery("SELECT * FROM log");
			username = text_user.getText();
			psd = text_psd.getText();
			if(username.length() == 0 ) {
				errorWindow("Error", "�˺Ż����벻��Ϊ�գ��������˺Ż�����");
				text_psd.setText(null);
			}
			else if(username.length() != 6) {
				errorWindow("Error", "�˺�Ϊ6λ����������ȷ���˺�");
				text_user.setText(null);
				text_psd.setText(null);
			}
			else if(username.length() == 6 && psd.length()==0) {
				errorWindow("Error", "����������");
			}
			else if(!judgePsd(st)) {
				errorWindow("Error", "���������˺Ų�����,���������룡");
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
		// TODO �Զ����ɵķ������
		/**
		 * ������Ϣ��ʾ����
		 * ���������Ϣ�����ͺ�Ҫ��ʾ������
		 */
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/*�ж��Ƿ��������*/
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	    return flag_User == 2 && flag_Psd == 3;   
	}
	
	@Override
	//����button
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
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


