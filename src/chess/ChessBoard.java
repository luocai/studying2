package chess;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ChessBoard extends JFrame {
//	private JPanel contentPanel;
	Board board;
	private JMenuBar upBar;
	private JPanel downBar;
	private JMenu systembutton;
	private JButton restartbutton;
	private JButton backbutton;
	private JButton exitbutton;
	private JMenuItem restart;
	private JMenuItem back;
	private JMenuItem exit;
	
	//新加内容哦
	private JButton give_in;
	
	public ChessBoard ()
	{
		
		board = new Board();
		
		setTitle("单机五子棋");
	//	contentPanel = (JPanel) this.getContentPane();
		upBar = new JMenuBar();
		systembutton = new JMenu("系统");
		restart = new JMenuItem("重新开始");
		back = new JMenuItem("悔棋");
		exit = new JMenuItem("退出");

		setJMenuBar(upBar);
		systembutton.add(restart);
		systembutton.add(back);
		systembutton.add(exit);
		MyItemListener lis = new MyItemListener();
		exit.addActionListener(lis);
		back.addActionListener(lis);
		restart.addActionListener(lis);
	
		upBar.add(systembutton);
		
		downBar = new JPanel();
		restartbutton = new JButton("重新开始");
		backbutton = new JButton("悔棋");
		exitbutton = new JButton("退出");
		give_in = new JButton("投降");
		
		exitbutton.addActionListener(lis);
		backbutton.addActionListener(lis);
		restartbutton.addActionListener(lis);
		give_in.addActionListener(lis);
		downBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		downBar.add(restartbutton);
		downBar.add(backbutton);
		downBar.add(exitbutton);
		downBar.add(give_in);
		
		add(downBar, BorderLayout.SOUTH);
		add (board);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 650);
		//pack();
	}
	private class MyItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == restart || obj == restartbutton)
			{
				System.out.println("重新开始...");
				board.restartGame();
			}
			else if (obj == exit || obj == exitbutton)
			{
				System.exit(0);
			}
			else if (obj == back || obj == backbutton)
			{
				System.out.println("悔棋...");
				board.goback();
			}
			else if (obj == give_in)
			{
				System.out.println("投降");
				board.give_in();
				board.restartGame();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChessBoard board = new ChessBoard();
		board.setVisible(true);
	}

}
