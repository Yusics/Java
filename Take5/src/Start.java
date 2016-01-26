package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import javax.swing.border.*;


public class Start {
	public static JLabel nimmt;
	public static JLabel cow;
	public static JButton startButton;
	public static JButton ruleButton;
	public static JLabel comment;
	public static JLabel design;
	public static JPanel buttonPanel;
	public static JPanel coverPanel;
	public static JFrame startFrame = new JFrame("Take 5!");
	public static JLabel ibImage = new JLabel(new ImageIcon("../res/take7.jpg")); 
	public static JButton homeImage;

	public void startGame(){
		init_JLabel();
		init_JButton();
		startButton.addActionListener(new StartListener());
		ruleButton.addActionListener(new RuleListener());
		init_JPanel();
		buttonPanel.add(comment);
		buttonPanel.add(design);
		init_model();
	}

	public static void setFont(Component c){
		c.setFont(new Font("COPPERPLATE gothic BOLD",Font.PLAIN,20));
	}

	public static void setHomeButton(){
		 homeImage = new JButton("Home");
		 homeImage.addActionListener(new homeButtonListener());
		 homeImage.setBounds(700,50,50,50);
		 Start.buttonPanel.add(homeImage);
	}

	public static void init_JLabel(){
		nimmt = new JLabel("6 NIMMT");
		cow   = new JLabel("誰是牛頭王");
		comment = new JLabel("<html><body>"+"最佳動腦"+"<br>"+"紙牌遊戲"+"<body></html>");
		design  = new JLabel("<html><body>"+"Wolfgang Kramer"+"<br><br>"+"Yiting, Yusiang");
		nimmt.setBounds(360,20,120,20);
		cow.setBounds(350,45,180,20);
		comment.setBounds(100,50,120,80);
		design.setBounds(600,50,250,80);
		setFont(nimmt);
		setFont(cow);
		setFont(comment);
		setFont(design);
		nimmt.setForeground(Color.WHITE);
		cow.setForeground(Color.WHITE);
		ibImage.setBounds(274,90,252,330);
	}

	public static  void init_JButton(){
		startButton = new JButton("Start");
		ruleButton = new JButton("Rules");
		Color colb = new Color(155,210,217);
		startButton.setBounds(245,50,130,80);
		ruleButton.setBounds(425,50,130,80);
		SoftBevelBorder bevelraised = new SoftBevelBorder(BevelBorder.RAISED);
		startButton.setBackground(colb);
		ruleButton.setBackground(colb);
		startButton.setOpaque(true);
		ruleButton.setOpaque(true);
		//startButton.setEnabled(true);
		//ruleButton.setEnabled(true);
		startButton.setBorder(bevelraised);
		ruleButton.setBorder(bevelraised);
		setFont(startButton);
		setFont(ruleButton);
	}

	public static void init_JPanel(){
		coverPanel  = new JPanel();
		buttonPanel = new JPanel();
		coverPanel.setLayout(null);
		buttonPanel.setLayout(null);
		Color colL = new Color(44,62,80);
		Color colB = new Color(250,250,228);
		coverPanel.setBackground(colL);
		buttonPanel.setBackground(colB);
	}

	public static void init_model(){
		coverPanel.add(nimmt);
		coverPanel.add(cow);
		coverPanel.add(ibImage);
		buttonPanel.add(startButton);
		buttonPanel.add(ruleButton);
		coverPanel.setPreferredSize(new Dimension(800,450));
		buttonPanel.setPreferredSize(new Dimension(800,150));
		coverPanel.setLocation(400,225);
		buttonPanel.setLocation(400,475);
		startFrame.getContentPane().add(coverPanel,BorderLayout.NORTH);
		startFrame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		startFrame.setSize(800,600);
		startFrame.setVisible(true);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}