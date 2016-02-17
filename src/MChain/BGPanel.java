package MChain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BGPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7079492381913412521L;

	private ArrayList<ArrowLink> arrows;
	
	private static final Stroke s = new BasicStroke(0.75f);
	
	public BGPanel(LayoutManager layout)
	{
		super(layout);
		arrows = new ArrayList<ArrowLink>();
	}
	
	public void link(SPanel p1, SPanel p2)
	{

		ArrowLink arrow = new ArrowLink(p1, p2, 5);
		
		arrows.add(arrow);
		this.repaint();
	}
	
	public void updateLinks()
	{
		for (ArrowLink arrow : arrows)
        {
            arrow.update();
        }
		this.repaint();

	}
	
	@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(s);
        
        for (ArrowLink arrow : arrows)
        {
            g2d.drawLine(arrow.getsX(), arrow.getsY(), arrow.geteX(), arrow.geteY());
        }


        //cubcurve = new CubicCurve2D.Float(x1,y1,x1+200,y1-115,x2-200,y2+115,x2,y2);
        //g2d.draw(cubcurve);

    }
	
}
