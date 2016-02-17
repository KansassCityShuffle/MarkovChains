package MChain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;

public class SPanel extends JPanel implements MouseInputListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6767908029581301027L;

	private JLabel nPanelLabel;
	private State state;
	
	public SPanel(BorderLayout layout, String name, int x, int y) 
	{
		super(layout);
		addMouseMotionListener(this);
		addMouseListener(this);

		//this.setOpaque(false);
		this.setBackground(Color.WHITE);
		nPanelLabel = new JLabel(name);
		nPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBounds(x, y,
				nPanelLabel.getPreferredSize().width+ 10, nPanelLabel.getPreferredSize().height+10);
		nPanelLabel.validate();
		nPanelLabel.repaint();
		nPanelLabel.setVisible(true);
		this.add(nPanelLabel, BorderLayout.CENTER);
		this.validate();
		nPanelLabel.revalidate();

	}
	
	public void buildLinks(ArrayList<State> states)
	{
		
	}
	
	public void setState(State s)
	{
		state = s;
	}
	
	public State getState()
	{
		return state;
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawOval(0, 0, g.getClipBounds().width-1, g.getClipBounds().height-1);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.RED);
		this.getParent().repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(Color.BLACK);
		this.getParent().repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.setForeground(Color.ORANGE);
		this.setBounds(
				this.getBounds().x+e.getX()-this.getBounds().width/2,
				this.getBounds().y+e.getY()-this.getBounds().height/2,
				this.getBounds().width, this.getBounds().height);
		this.getParent().repaint();		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
