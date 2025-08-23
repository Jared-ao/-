package log;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * 父类主窗口，包含主界面，两个标签、两个文本框以及确认按钮
 * 
 * 登录界面添加注册按钮
 * 注册界面添加取消按钮
 *
 * 包含初始化函数 和窗口关闭函数
 */
abstract public class Obj_Frame {
	public JFrame frame = new JFrame("PlaneWars Login");
	JLabel label_user = new JLabel("Username:");
	JLabel label_pwd = new JLabel("PassWord:");
	JTextField text_user = new JTextField(50);
	JPasswordField text_psd = new JPasswordField(50);
	
	ImageIcon image_log = new ImageIcon("src//images//log.png"); //按钮背景图
	ImageIcon image_reg = new ImageIcon("src//images//register.png"); //按钮背景图
	ImageIcon image_back = new ImageIcon("src//images//back.png"); //按钮背景图
	ImageIcon image_help = new ImageIcon("src//images//help.png"); //按钮背景图
	ImageIcon image_cancel = new ImageIcon("src//images//cancel.png"); //按钮背景图
	JButton btn_log = new JButton(image_log);
	JButton btn_reg = new JButton(image_reg);
	JButton btn_cancel = new JButton(image_cancel);
	JButton btn_help= new JButton(image_help);
	JButton btn_back= new JButton(image_back);
	
	ImageIcon image_rule = new ImageIcon("src//images//rule.png"); //背景图
	JLabel label_rule = new JLabel();
	
	ImageIcon image_pho = new ImageIcon("src//images//background2.png"); //背景图
	JLabel label_pho = new JLabel();
	
	String username_sql; // 用于读取数据库中的用户名
	String psd_sql;      // 用于读取数据库中的密码
	String username;     
	String psd;
	ConnectMySql connectMySql = new ConnectMySql(); // 创建数据库链接对象
	
	abstract void init();
	abstract void errorWindow(String title, String message);
}
