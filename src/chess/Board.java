package chess;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;

public class Board extends JPanel implements MouseListener {

	public static final int MARGIN = 40;
	public static final int GRID_SPAN = 45;
	public static final int ROWS = 10;
	public static final int COLS = 10;
	Point []chessList = new Point[(ROWS+1)*(COLS+1)];
	boolean isBlack = true;
	boolean gameover = false;
	int chessCount = 0;
	int xindex, yindex;
	public Board ()
	{
		setBackground(Color.orange);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
				int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
				if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameover )
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				else
					setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);// ÊÇÂùÖØÒªÅ¶
		for (int i = 0; i <= ROWS; i++)
		{
			g.drawLine(MARGIN, MARGIN + i*GRID_SPAN, MARGIN + COLS*GRID_SPAN, MARGIN + i*GRID_SPAN);
		}
		for (int i = 0; i <= COLS; i++)
		{
			g.drawLine(MARGIN + i*GRID_SPAN, MARGIN, MARGIN + i*GRID_SPAN, MARGIN + ROWS*GRID_SPAN);
		}
		
		for (int i = 0; i < chessCount; i ++)
		{
			int xpos = chessList[i].getX() *GRID_SPAN + MARGIN;
			int ypos = chessList[i].getY() *GRID_SPAN + MARGIN;
			g.setColor(chessList[i].getColor());
			g.fillOval(xpos - Point.DIAMETER / 2, ypos - Point.DIAMETER / 2, Point.DIAMETER, Point.DIAMETER);
		
			if (i == chessCount - 1)
			{
				g.setColor(Color.RED);
				g.drawRect(xpos - Point.DIAMETER / 2, ypos - Point.DIAMETER / 2 , Point.DIAMETER, Point.DIAMETER);
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (gameover)
			return ;
		String colorName = isBlack ? "ºÚÆå" : "°×Æå" ;
		xindex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
		yindex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
		if (xindex < 0 || xindex > ROWS || yindex < 0 || yindex > COLS)
			return;
		if (findChess(xindex, yindex))
			return;
		
		Point ch = new Point(xindex, yindex, isBlack ? Color.black : Color.white);
		chessList[chessCount++] = ch;
		repaint();
		if (isWin())
		{
			String msg = String.format("¹§Ï²£¬%sÓ®À²£¡", colorName);
			JOptionPane.showMessageDialog(this, msg);// ²»ÊÇºÜ¶®
			gameover = true;
			
		}
		isBlack = !isBlack; //why
	}
	
	private boolean findChess(int xindex2, int yindex2) {
		// TODO Auto-generated method stub
		for (Point c : chessList)
			if (c != null && c.getX() == xindex2 && c.getY() == yindex2)
				return true;
		return false;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private boolean isWin() {
		// TODO Auto-generated method stub
		int continueCount = 1;
		for (int i = xindex - 1; i >= 0; i--)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(i , yindex, c) != null)
				continueCount++;
			else
				break;
		}
		
		for (int i = xindex + 1; i <= ROWS; i++)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(i , yindex, c) != null)
				continueCount++;
			else
				break;
		}
		if (continueCount >= 5)
			return true;
		else
			continueCount = 1;
		
		for (int y = yindex - 1; y >= 0; y--)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(xindex, y, c) != null)
				continueCount++;
			else
				break;
		}
		
		for (int y = yindex + 1; y <= ROWS; y++)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(xindex, y, c) != null)
				continueCount++;
			else
				break;
		}
		
		if (continueCount >= 5)
			return true;
		else
			continueCount = 1;
		
		for (int x = xindex + 1, y = yindex - 1; y >= 0 && x <= ROWS; x++, y--)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null)
				continueCount++;
			else
				break;
		}
		for (int x = xindex - 1, y = yindex + 1; y <= COLS && x >= 0; x--, y++)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null)
				continueCount++;
			else
				break;
		}
		if (continueCount >= 5)
			return true;
		else
			continueCount = 1;
		
		for (int x = xindex - 1, y = yindex - 1; y >= 0 && x >= 0; x--, y--)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null)
				continueCount++;
			else
				break;
		}
		
		for (int x = xindex + 1, y = yindex + 1; y <= COLS && x <= ROWS; x++, y++)
		{
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null)
				continueCount++;
			else
				break;
		}
		
		if (continueCount >= 5)
			return true;
		else
			continueCount = 1;
			
		return false;
	}
	
	private Point getChess (int x, int y, Color color)
	{
		for (Point c : chessList)
		{
			if (c != null && c.getX() == x && c.getY() == y && c.getColor() == color)
				return c;
		}
		return null;
	}
	
	public void restartGame ()
	{
		for (int i = 0; i < chessList.length; i++)
			chessList[i] = null;
		isBlack = true;
		gameover = false;
		chessCount = 0;
		repaint();		
	}
	public void goback() {
		// TODO Auto-generated method stub
		if (chessCount == 0)
			return ;
		chessList[chessCount - 1] = null;
		chessCount--;
		if (chessCount > 0)
		{
			xindex = chessList[chessCount - 1].getX();
			yindex = chessList[chessCount - 1].getY();
		}
		isBlack = !isBlack;
		repaint();
	}
	
	public void give_in ()
	{
		String msg = String.format("%sÍ¶½µÀ²", isBlack ? "ºÚÆå" : "°×Æå");
		JOptionPane.showConfirmDialog(this, msg);
	}


}
