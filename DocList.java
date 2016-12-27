import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class DocList extends JFrame {
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String sql="select * from Doctor where id= 10001";
					ResultSet rs=SQL.executeQuery(sql);
					Doctor d=null;
					try {
						if(rs.next()){
							d=new Doctor();
							d.setID(rs.getString("id"));
							d.setName(rs.getString("name"));
							d.setSex(rs.getString("sex"));
							d.setAge(Integer.parseInt(rs.getString("age")));
							d.setList(rs.getString("list"));
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SQL.close();
					DocList frame = new DocList(d);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public DocList(final Doctor doc) {
		setBackground(SystemColor.textHighlight);
		setTitle("医护人员后台系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 640);
		getContentPane().setLayout(null);
		
		ImageIcon bg=new ImageIcon("src/UserApbg.png");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 984, 33);
		menuBar.setBackground(new Color(173, 216, 230));
		getContentPane().add(menuBar);
		
		JMenu mexit = new JMenu("退出登录");
		mexit.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				int check=JOptionPane.showConfirmDialog(null, "确认退出登录？");
				if(check==JOptionPane.OK_OPTION)
				{
					dispose();
					Login p=new Login();
					p.setVisible(true);
				}		
			}
		});
		
		JMenu m1 = new JMenu("基本诊断页");
		m1.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				
			}
		});
		
		JMenu m2 = new JMenu("当前就诊队列");
		m2.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				dispose();
				RegRegistery p=new RegRegistery();
				p.setVisible(true);
				}		
		});
		menuBar.add(m2);
		menuBar.add(m1);
		
			
		
		menuBar.add(mexit);
		
		JPanel panel = new JPanel();
		panel.setBounds(101, 51, 777, 515);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(61, 86, 655, 198);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 655, 144);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] heads={"就诊号","姓名","性别","年龄","挂号时间","状态"};
		Object[][] r=UserSQL.getDocList(doc.getID());
		DefaultTableModel model=new DefaultTableModel();
		model.setDataVector(r, heads);
	    table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("就诊");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)
				{
					int row=table.getSelectedRow();
					String mid=table.getValueAt(row, 0).toString();
					User u=UserSQL.getUser(mid);
					dispose();
					DocDiag p=new DocDiag(u,doc);
					p.setVisible(true);
				}
				
			}
		});
		btnNewButton_1.setBounds(152, 170, 85, 23);
		panel_1.add(btnNewButton_1);
		
		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button.setBounds(437, 170, 85, 23);
		panel_1.add(button);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(61, 279, 655, 227);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton button_1 = new JButton("过号");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)
				{
					int row=table.getSelectedRow();
					String pmid=table.getValueAt(row, 0).toString();
					String list1=doc.getList();
					int length1=list1.length();
					for(int i=0;i<length1;i=i+12)
					{
						String usermid=list1.substring(i,i+12);	
						if(usermid.equals(pmid))
							{
							 String doclist=list1.substring(0,i)+list1.substring(i+12,length1)+usermid;
							 String sql="update Doctor set list='"+doclist+"'where id='"+doc.getID()+"';update UserMedicalHistory set state=11 where mid='"+usermid+"';";
							  
						     if(SQL.executeUpdate(sql)==-1)
						    	 JOptionPane.showMessageDialog(null,"数据库操作失败");
						     doc.setList(doclist);
						       table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								String[] heads={"就诊号","姓名","性别","年龄","挂号时间","状态"};
								Object[][] r=UserSQL.getDocList(doc.getID());
								DefaultTableModel model=new DefaultTableModel();
								model.setDataVector(r, heads);
							    table.setModel(model);
								scrollPane.setViewportView(table);
						     
							 break;
						     }
					}
					
				}
				
			}
		});
		button_1.setBounds(247, 170, 85, 23);
		panel_1.add(button_1);
		
		JButton button_5 = new JButton("叫回");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1&&table.getValueAt(table.getSelectedRow(), 5)!="正常")
				{
					int check=JOptionPane.showConfirmDialog(null, "叫回后,此患者将重新排在队首,确定叫回？");
					if(check==JOptionPane.OK_OPTION)
					{
						
					int row=table.getSelectedRow();
					String pmid=table.getValueAt(row, 0).toString();
					String list1=doc.getList();
					int length1=list1.length();
					for(int i=0;i<length1;i=i+12)
					{
						String usermid=list1.substring(i,i+12);	
						if(usermid.equals(pmid))
							{
							
							 String doclist=usermid+list1.substring(0,i)+list1.substring(i+12,length1);
							 String sql="update Doctor set list='"+doclist+"'where id='"+doc.getID()+"';update UserMedicalHistory set state=1 where mid='"+usermid+"';";
							 
						     if(SQL.executeUpdate(sql)==-1)
						    	 JOptionPane.showMessageDialog(null,"数据库操作失败.");
						     doc.setList(doclist);
						       table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								String[] heads2={"就诊号","姓名","性别","年龄","挂号时间","状态"};
								Object[][] r2=UserSQL.getDocList(doc.getID());
								DefaultTableModel model2=new DefaultTableModel();
								model2.setDataVector(r2, heads2);
							    table.setModel(model2);
								scrollPane.setViewportView(table);
						     break;
						     }
					}
					}
				}
			}
		});
		button_5.setBounds(342, 170, 85, 23);
		panel_1.add(button_5);
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 40, 645, 115);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] heads2={"就诊号","姓名","性别","年龄","挂号时间","预约时间","状态"};
		Object[][] r2=UserSQL.getDocApList(doc.getID());
		DefaultTableModel model2=new DefaultTableModel();
		model2.setDataVector(r2, heads2);
	    table_1.setModel(model2);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("当前队列:");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(61, 55, 132, 21);
		panel.add(lblNewLabel);
	
		
		JLabel label = new JLabel("预约患者:");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(10, 10, 149, 21);
		panel_2.add(label);
		
		JButton button_2 = new JButton("删除");
		button_2.setBounds(442, 176, 85, 23);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("叫回");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow()!=-1&&table_1.getValueAt(table_1.getSelectedRow(), 6)!="等待"&&table_1.getValueAt(table_1.getSelectedRow(), 6)!="可直接就诊")
				{
					int check=JOptionPane.showConfirmDialog(null, "叫回后,此患者将重新排在队首,确定叫回？");
					if(check==JOptionPane.OK_OPTION)
					{
						
					int row=table_1.getSelectedRow();
					String pmid=table_1.getValueAt(row, 0).toString();
					String list1=doc.getList();
					int length1=list1.length();
					for(int i=0;i<length1;i=i+12)
					{
						String usermid=list1.substring(i,i+12);	
						if(usermid.equals(pmid))
							{
							
							 String doclist=usermid+list1.substring(0,i)+list1.substring(i+12,length1);
							 String sql="update Doctor set list='"+doclist+"'where id='"+doc.getID()+"';update UserMedicalHistory set state=1 where mid='"+usermid+"';";
							 
						     if(SQL.executeUpdate(sql)==-1)
						    	 JOptionPane.showMessageDialog(null,"数据库操作失败.");
						     doc.setList(doclist);
						     table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								String[] heads2={"就诊号","姓名","性别","年龄","挂号时间","预约时间","状态"};
								Object[][] r2=UserSQL.getDocApList(doc.getID());
								DefaultTableModel model2=new DefaultTableModel();
								model2.setDataVector(r2, heads2);
							    table_1.setModel(model2);
								scrollPane_1.setViewportView(table_1);
						     break;
						     }
					}
					}
				}
				
			}
		});
		button_3.setBounds(347, 176, 85, 23);
		panel_2.add(button_3);
		
		JButton button_4 = new JButton("过号");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow()!=-1&&table_1.getValueAt(table_1.getSelectedRow(), 6)!="已过号")
				{
					int row=table_1.getSelectedRow();
					String pmid=table_1.getValueAt(row, 0).toString();
					String list1=doc.getList();
					int length1=list1.length();
					for(int i=0;i<length1;i=i+12)
					{
						String usermid=list1.substring(i,i+12);	
						if(usermid.equals(pmid))
							{
							 String doclist=list1.substring(0,i)+list1.substring(i+12,length1)+usermid;
							 String sql="update Doctor set list='"+doclist+"'where id='"+doc.getID()+"';update UserMedicalHistory set state=11 where mid='"+usermid+"';";
							 
						     if(SQL.executeUpdate(sql)==-1)
						    	 JOptionPane.showMessageDialog(null,"数据库操作失败.");
						     doc.setList(doclist);
						     table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								String[] heads2={"就诊号","姓名","性别","年龄","挂号时间","预约时间","状态"};
								Object[][] r2=UserSQL.getDocApList(doc.getID());
								DefaultTableModel model2=new DefaultTableModel();
								model2.setDataVector(r2, heads2);
							    table_1.setModel(model2);
								scrollPane_1.setViewportView(table_1);
						     break;
						     }
					}
					
				}
			}
		});
		button_4.setBounds(252, 176, 85, 23);
		panel_2.add(button_4);
		
		JButton button_6 = new JButton("就诊");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow()!=-1)
				{
					int row=table_1.getSelectedRow();
					String mid=table_1.getValueAt(row, 0).toString();
					User u=UserSQL.getUser(mid);
					dispose();
					DocDiag p=new DocDiag(u,doc);
					p.setVisible(true);
				}
			}
		});
		button_6.setBounds(157, 176, 85, 23);
		panel_2.add(button_6);
		
		JButton btnNewButton = new JButton("刷新队列");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 153, 255));
		btnNewButton.setBounds(332, 10, 119, 28);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] heads={"就诊号","姓名","性别","年龄","挂号时间","状态"};
				Object[][] r=UserSQL.getDocList(doc.getID());
				DefaultTableModel model=new DefaultTableModel();
				model.setDataVector(r, heads);
			    table.setModel(model);
				scrollPane.setViewportView(table);
				
				table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] heads2={"就诊号","姓名","性别","年龄","挂号时间","预约时间","状态"};
				Object[][] r2=UserSQL.getDocApList(doc.getID());
				DefaultTableModel model2=new DefaultTableModel();
				model2.setDataVector(r2, heads2);
			    table_1.setModel(model2);
				scrollPane_1.setViewportView(table_1);
				
			}
		});
	}
}
