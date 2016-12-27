
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegSelectDoc extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String mid;
    
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegAp frame = new RegAp();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RegSelectDoc(final User user) {
		setTitle("分配医生");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000,600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		final JPanel result = new JPanel();
		result.setBounds(137, 166, 772, 359);
		contentPane.add(result);
		result.setLayout(null);
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 50, 452, 241);
		scrollPane.setViewportBorder(null);
		result.add(scrollPane);

		final JTable table=new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] heads={"ID","姓名","性别","年龄","就诊排队人数"};
		Object[][] r=Doctor.getDoc(user.getDeptid());
		DefaultTableModel model=new DefaultTableModel();
		model.setDataVector(r, heads);
	    table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("确  定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = table.getSelectedRow();
				String sql="select * from Doctor where id='"+table.getValueAt(row, 0).toString()+"'";
				ResultSet rs=SQL.executeQuery(sql);
				String s1=null;
				try {
					if(rs.next()){
						s1=rs.getString("list");
						System.out.println(s1);
	
					}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				SQL.close();
				String s2=s1+user.getMID();
				String sql1="update Doctor set list='"+s2+"' where id='"+table.getValueAt(row, 0).toString()+"'";
				if(SQL.executeUpdate(sql1)!=-1)
				{   
	
					JOptionPane.showMessageDialog(null, "分配成功！");
					user.setDocid(table.getValueAt(row, 0).toString()); 
					
					int i=1;
			        user.setState(i);
			        if(user.getAID()==null){
					Date n= new Date(System.currentTimeMillis());
					
			        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			        String dateStr="";
			        try {   
			            dateStr = sdf.format(n);
			             
			        } catch (Exception e) {   
			            e.printStackTrace();   
			        }  
			        
			        Timestamp ts = new Timestamp(System.currentTimeMillis());   
			        try {   
			            ts = Timestamp.valueOf(dateStr);    
			        } catch (Exception e) {   
			            e.printStackTrace();   
			        }  
			        user.setRegtime(ts);
			       
					String sql2="insert into UserMedicalHistory(mid,id,name,sex,age,address,phone,deptid,docid,regtime,state) values"+"( '"+user.getMID()+"','"+user.getID()+"','"+user.getName()+"','"+user.getSex()+"','"+user.getAge()+"','"+user.getAddress()+"','"
					+user.getPhone()+"','"+user.getDeptid()+"','"+user.getDocid()+"','"+ts+"','"+user.getState()+"')";
			        if(SQL.executeUpdate(sql2)==-1)
			        	JOptionPane.showMessageDialog(null, "患者信息无法加入数据库！");
			        }
			        else{
			        	Date n= new Date(System.currentTimeMillis());
						Date apt=user.getAptime();
						
				        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				        String dateStr="";
				        String apStr="";
				        try {   
				            dateStr = sdf.format(n);
				            apStr=sdf.format(apt);
				             
				        } catch (Exception e) {   
				            e.printStackTrace();   
				        }  
				        
				        Timestamp ts = new Timestamp(System.currentTimeMillis());  
				        Timestamp apts = new Timestamp(System.currentTimeMillis());
				        try {   
				            ts = Timestamp.valueOf(dateStr);
				            apts = Timestamp.valueOf(apStr);   
				        } catch (Exception e) {   
				            e.printStackTrace();   
				        }  
				        user.setRegtime(ts);
				       
						String sql2="insert into UserMedicalHistory(mid,id,name,sex,age,address,phone,deptid,docid,aptime,regtime,state) values"+"( '"+user.getMID()+"','"+user.getID()+"','"+user.getName()+"','"+user.getSex()+"','"+user.getAge()+"','"+user.getAddress()+"','"
						+user.getPhone()+"','"+user.getDeptid()+"','"+user.getDocid()+"','"+apts+"','"+ts+"','"+user.getState()+"')";
				        if(SQL.executeUpdate(sql2)==-1)
				        	JOptionPane.showMessageDialog(null, "患者信息无法加入数据库！");
				        }
			        }
					dispose();
				    RegMainPage p=new RegMainPage();
				    p.setVisible(true);
				}
				
				
			});
		btnNewButton.setBounds(322, 306, 93, 30);
		result.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("分配医生：");
		lblNewLabel.setBounds(136, 10, 290, 30);
		result.add(lblNewLabel);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
	

		JLabel label = new JLabel("患者就诊号:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("宋体", Font.BOLD, 26));
		label.setBounds(273, 107, 649, 49);
		mid=this.computeMID();
		label.setText("该患者就诊号："+mid);
		user.setMID(mid);
		contentPane.add(label);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 984, 33);
		menuBar.setBackground(new Color(173, 216, 230));
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("退出登录");
		mnNewMenu.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				if(user.getDocid()==null)
				{
					JOptionPane.showMessageDialog(null, "请为患者分配医生！");
					
					
				}
				else
				{
				int check=JOptionPane.showConfirmDialog(null, "确认退出登录？");
				if(check==JOptionPane.OK_OPTION)
				{
					dispose();
					Login p=new Login();
					p.setVisible(true);
				}		
			}}
		});
		
		JMenu mnNewMenu_3 = new JMenu("现场挂号");
		mnNewMenu_3.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				if(user.getDocid()==null)
				{
					JOptionPane.showMessageDialog(null, "请为患者分配医生！");
					
					
				}
				else
				{
				dispose();
				RegRegistery p=new RegRegistery();
				p.setVisible(true);
				}	}	
		});
		
		JMenu mnNewMenu_5 = new JMenu("首页");
		mnNewMenu_5.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				if(user.getDocid()==null)
				{
					JOptionPane.showMessageDialog(null, "请为患者分配医生！");
					
					
				}
				else
				{
				dispose();
				RegMainPage p=new RegMainPage();
				p.setVisible(true);
			}}
		});
		menuBar.add(mnNewMenu_5);
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_2 = new JMenu("预约查询");
		mnNewMenu_2.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				if(user.getDocid()==null)
				{
					JOptionPane.showMessageDialog(null, "请为患者分配医生！");
					
					
				}
				else
				{
					dispose();
					RegAp p=new RegAp();
					p.setVisible(true);
				}
			}
		});
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_4 = new JMenu("收费管理");
		mnNewMenu_4.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				if(user.getDocid()==null)
				{
					JOptionPane.showMessageDialog(null, "请为患者分配医生！");
					
					
				}
				else
				{
				dispose();
				RegCharge p=new RegCharge();
				p.setVisible(true);
				}
			}
		});
		menuBar.add(mnNewMenu_4);
		
			
		
		menuBar.add(mnNewMenu);
		
		
		
		}  
	

	private String computeMID(){ 
		    Random rm = new Random();  
		    double pross = (1 + rm.nextDouble()) * Math.pow(10, 4);  
		    
		    String lastfive = String.valueOf((int)pross);   
		    Calendar c = Calendar.getInstance();
		    int y=c.get(Calendar.YEAR);
		    y=y%10+((y/10)%10)*10;
		    int m=c.get(Calendar.MONTH)+1;
		    int d=c.get(Calendar.DAY_OF_MONTH);
		    String mid="M"+String.valueOf(y)+String.valueOf(m)+String.valueOf(d)+lastfive;
		    return mid;
		    }
}

