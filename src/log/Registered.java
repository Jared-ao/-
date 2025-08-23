package log;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JOptionPane;



/**
 * 
 * @author
 * 
 *         ��Ҫʵ��ע�����
 *
 */
public class Registered extends Obj_Frame implements ActionListener {

	@Override
	public void init() {
		// TODO �Զ����ɵķ������
		frame.setSize(550, 650);
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
		
		btn_reg.addActionListener(this);
		btn_reg.setBounds(180, 275, 180, 81);
		frame.add(btn_reg);
		
		btn_cancel.addActionListener(this);
		btn_cancel.setBounds(180, 370, 180, 81);
		frame.add(btn_cancel);
		label_pho.setBounds(1, 1, 550, 650);
		label_pho.setIcon(image_pho);
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
	public void reg() {
		/**
		 * ע��Ҫ�� �˺ű���Ϊ6λ���֣� �������С��6λ
		 */
		Statement sql = null;
		ResultSet st;
		PreparedStatement pst = null;
		connectMySql.connect();
		username = text_user.getText();
		psd = text_psd.getText();
		try {
			if (username.length() == 0) {
				errorWindow("Error", "������Ҫע����˺�");
			} else if (username.length() != 6) {
				errorWindow("Error", "�˺ű���Ϊ6λ");
				text_user.setText(null);
			} else if (username.length() == 6 && psd.length() == 0) {
				errorWindow("Error", "���벻��Ϊ��");
			} else if (username.length() == 6 && psd.length() < 6) {
				errorWindow("Error", "����Ҫ���ڻ����6λ");
				text_psd.setText(null);
			} else if (username.length() == 6 && psd.length() >= 6) {
				sql = connectMySql.con.createStatement();
				st = sql.executeQuery("SELECT * FROM log");

				if (!judgeUser(st)) { // �ж��˺��Ƿ��ظ�ע��
					errorWindow("Error", "���˺��ѱ�ע�ᣬ����������!");
				}

				else {
					try {
						pst = connectMySql.con.prepareStatement("INSERT INTO log values(?,?)");
						JOptionPane.showMessageDialog(null, "ע��ɹ�");
						new Log().init();

					} catch (SQLException e) {

					}
					pst.setString(1, username);
					pst.setString(2, psd);
					pst.executeUpdate();
					pst.close();
					frame.dispose();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ������ʾ���� */
	public void errorWindow(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	/* �ж�Ҫע����˺��Ƿ��Ѿ����� */
	public boolean judgeUser(ResultSet st) {
		int flag_reg = 1; // ����1��ʾ�˺Ų��ظ�
		try {
			while (st.next() == true) { // ���ݿ��д�������,�ж�Ҫע����˺��Ƿ��Ѿ�����
				username_sql = st.getString(1);
				if (username_sql.equals(username)) {
					text_user.setText(null);
					text_psd.setText(null);
					flag_reg = 0;// ����0��ʾ�˺Ų��ظ�
				}
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return flag_reg == 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getSource() == btn_reg) {
			reg();
		}
		if (e.getSource() == btn_cancel) {
			frame.dispose();
			Log log = new Log();
			log.init();
		}

	}
}
