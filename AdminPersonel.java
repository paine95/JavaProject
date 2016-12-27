import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.InputMethodListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AdminPersonel {

	JFrame frame;
	private String medid;
	private JTextField newpwd2;
	private JTextField pwdc2;
    private JTextField cid;
    private JTextField aid;
    private String docid;
    private JTextField aname;
    private JTextField cname;
    private JTextField deptid;
    private JTextField textField;
    private JTextField aprice;
    private JTextField cprice;
    private String itemid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPersonel window = new AdminPersonel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public AdminPersonel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		final JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 204, 204));
		panel_1.setLocation(0, 40);
		panel_1.setSize(1000, 500);
		frame.getContentPane().add(panel_1,BorderLayout.CENTER);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(0, 540, 1000, 60);
		frame.getContentPane().add(panel_2,BorderLayout.SOUTH);
		panel_2.setLayout(null);
		
		JLabel lblCopyright = new JLabel("CopyRight");
		lblCopyright.setForeground(Color.WHITE);
		lblCopyright.setBounds(920, 0, 60, 30);
		panel_2.add(lblCopyright);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(0, 0, 1000, 40);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("人员管理");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//刷新页面（重绘Panel）
				panel_1.removeAll();
				panel_1.repaint();
				JPanel panel = new JPanel();
				panel.setBackground(SystemColor.window);
				panel.setBounds(160, 25, 800, 450);
				panel_1.add(panel);
				//账号管理
				JButton btnNewButton_3 = new JButton("医生账号");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//医生账号管理
					}
				});
				btnNewButton_3.setBounds(15, 25, 98, 26);
				panel_1.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("药师账号");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//药师账号管理
					}
				});
				btnNewButton_4.setBounds(15, 63, 98, 26);
				panel_1.add(btnNewButton_4);
				
				JButton btnNewButton_5 = new JButton("挂号收费");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//药师账号管理
					}
				});
				btnNewButton_5.setBounds(15, 101, 98, 26);
				panel_1.add(btnNewButton_5);
			}
		});
		btnNewButton.setBounds(10, 5, 100, 25);
		btnNewButton.setBackground(SystemColor.menu);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("药库管理");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//刷新页面（重绘Panel）
				panel_1.removeAll();
				panel_1.repaint();
				//药库管理
				
			}
		});
		btnNewButton_1.setBounds(230, 5, 100, 25);
		btnNewButton_1.setBackground(SystemColor.menu);
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("收费项目管理");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//刷新页面（重绘Panel）
				panel_1.removeAll();
				panel_1.repaint();
				//收费信息
			}
		});
		btnNewButton_2.setBounds(340, 5, 125, 25);
		btnNewButton_2.setBackground(SystemColor.menu);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("科室管理");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//刷新页面（重绘Panel）
				panel_1.removeAll();
				panel_1.repaint();
			}
		});
		btnNewButton_5.setBackground(SystemColor.menu);
		btnNewButton_5.setBounds(120, 5, 100, 25);
		panel_3.add(btnNewButton_5);
		panel_1.removeAll();
		panel_1.repaint();
		
		panel_1.setBackground(new Color(204, 204, 204));
		
		final JPanel panel = new JPanel();
	
        panel.setBackground(SystemColor.window);
		panel.setBounds(160, 25, 800, 450);
		panel_1.add(panel);	
		//账号管理
		panel.removeAll();
		panel.repaint();
		panel.setBackground(SystemColor.window);
		panel.setBounds(160, 25, 800, 450);
		panel_1.add(panel);
		panel.setLayout(null);
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(24, 10, 384, 430);
		panel.add(panel_4);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(99, 18, 170, 25);
		ArrayList<String> list = new ArrayList<String>();
		list.add("全部科室");
		ResultSet rs=SQL.executeQuery("select * from Dept");
		try {
			while(rs.next()){
			  list.add(rs.getString("id")+" "+rs.getString("name"));
			  }
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SQL.close();
		String[] s=new String[list.size()];
	
		for(int i=0;i<list.size();i++)
			s[i]=(String)list.get(i);
		comboBox.setModel(new DefaultComboBoxModel(s/*new String[] {"药品名"})*/));
		comboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		
		
				
		JLabel lblNewLabel = new JLabel("选择科室：");
		lblNewLabel.setBounds(10, 10, 85, 40);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_4.setLayout(null);
		panel_4.add(comboBox);
		panel_4.add(lblNewLabel);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(20, 60, 354, 360);
		panel_4.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("当前挂号量:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 99, 106, 15);
		panel_7.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("当前总收入:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 173, 106, 15);
		panel_7.add(label_1);
		
		
		
		final JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(418, 10, 354, 418);
		panel_5.setVisible(false);
		panel.add(panel_5);
		

				
				
			
				
	
				
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBounds(39, 108, 75, 211);
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("科");
		lblNewLabel1.setForeground(new Color(255, 255, 255));
		lblNewLabel1.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(0, 10, 75, 68);
		panel_6.add(lblNewLabel1);
		
		JLabel label_2 = new JLabel("室");
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 52, 75, 68);
		panel_6.add(label_2);
		
		JLabel label_6 = new JLabel("查");
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setFont(new Font("宋体", Font.BOLD, 20));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(0, 94, 75, 68);
		panel_6.add(label_6);
		
		JLabel label_7 = new JLabel("看");
		label_7.setForeground(new Color(255, 255, 255));
		label_7.setFont(new Font("宋体", Font.BOLD, 20));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(0, 136, 75, 68);
		panel_6.add(label_7);
				

		
	}
}
