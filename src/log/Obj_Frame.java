package log;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * ���������ڣ����������棬������ǩ�������ı����Լ�ȷ�ϰ�ť
 * 
 * ��¼�������ע�ᰴť
 * ע��������ȡ����ť
 *
 * ������ʼ������ �ʹ��ڹرպ���
 */
abstract public class Obj_Frame {
	public JFrame frame = new JFrame("PlaneWars Login");
	JLabel label_user = new JLabel("Username:");
	JLabel label_pwd = new JLabel("PassWord:");
	JTextField text_user = new JTextField(50);
	JPasswordField text_psd = new JPasswordField(50);
	
	ImageIcon image_log = new ImageIcon("src//images//log.png"); //��ť����ͼ
	ImageIcon image_reg = new ImageIcon("src//images//register.png"); //��ť����ͼ
	ImageIcon image_back = new ImageIcon("src//images//back.png"); //��ť����ͼ
	ImageIcon image_help = new ImageIcon("src//images//help.png"); //��ť����ͼ
	ImageIcon image_cancel = new ImageIcon("src//images//cancel.png"); //��ť����ͼ
	JButton btn_log = new JButton(image_log);
	JButton btn_reg = new JButton(image_reg);
	JButton btn_cancel = new JButton(image_cancel);
	JButton btn_help= new JButton(image_help);
	JButton btn_back= new JButton(image_back);
	
	ImageIcon image_rule = new ImageIcon("src//images//rule.png"); //����ͼ
	JLabel label_rule = new JLabel();
	
	ImageIcon image_pho = new ImageIcon("src//images//background2.png"); //����ͼ
	JLabel label_pho = new JLabel();
	
	String username_sql; // ���ڶ�ȡ���ݿ��е��û���
	String psd_sql;      // ���ڶ�ȡ���ݿ��е�����
	String username;     
	String psd;
	ConnectMySql connectMySql = new ConnectMySql(); // �������ݿ����Ӷ���
	
	abstract void init();
	abstract void errorWindow(String title, String message);
}
