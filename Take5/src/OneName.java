package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;


public class OneName{
	private JLabel playerName = new JLabel();
	private JLabel comname1 = new JLabel();
	private JLabel comname2 = new JLabel();
	private JLabel comname3 = new JLabel();
	public static JTextField name = new JTextField();
	public static JTextField name_com1 = new JTextField();
	public static JTextField name_com2 = new JTextField();
	public static JTextField name_com3 = new JTextField();
	public static JButton ready;
	public static String player1name;
	public static String com1name;
	public static String com2name;
	public static String com3name;

	public void inputname(){
		init_nameLabel();
		init_nameText();
		Start.init_JLabel();
		Start.init_JButton();
		Start.init_JPanel();
		init_namePanel();
		Start.nimmt.setText("");
		Start.cow.setText("Type your names and Three Computer names");
		Start.cow.setBounds(200,45,500,40);
		ready = new JButton("Ready");
		ready.setBounds(330,100,130,30);
		ready.addActionListener(new readyListener());
		Start.buttonPanel.add(ready);
		Start.setHomeButton();
	}

	public void init_namePanel(){
		Start.coverPanel.add(Start.nimmt);
		Start.coverPanel.add(Start.cow);
		Start.coverPanel.add(Start.ibImage);
		Start.buttonPanel.add(playerName);
		Start.buttonPanel.add(comname1);
		Start.buttonPanel.add(comname2);
		Start.buttonPanel.add(comname3);
		Start.buttonPanel.add(name);
		Start.buttonPanel.add(name_com1);
		Start.buttonPanel.add(name_com2);
		Start.buttonPanel.add(name_com3);
		Start.coverPanel.setPreferredSize(new Dimension(800,450));
		Start.buttonPanel.setPreferredSize(new Dimension(800,150));
		Start.coverPanel.setLocation(400,225);
		Start.buttonPanel.setLocation(400,475);
		Start.startFrame.getContentPane().add(Start.coverPanel,BorderLayout.NORTH);
		Start.startFrame.getContentPane().add(Start.buttonPanel,BorderLayout.SOUTH);
		Start.startFrame.setSize(800,600);
		Start.startFrame.setVisible(true);
		Start.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init_nameLabel(){
		playerName.setText("Player ");
		comname1.setText("Com1 ");
		comname2.setText("Com2 ");
		comname3.setText("Com3 ");
		Start.setFont(playerName);
		Start.setFont(comname1);
		Start.setFont(comname2);
		Start.setFont(comname3);
		playerName.setBounds(150,10,130,40);
		comname1.setBounds(300,10,130,40);
		comname2.setBounds(440,10,130,40);
		comname3.setBounds(580,10,130,40);
	}

	public void init_nameText(){
		name.setBounds(100,50,150,40);
		name_com1.setBounds(250,50,150,40);
		name_com2.setBounds(400,50,150,40);
		name_com3.setBounds(550,50,150,40);
	}

}