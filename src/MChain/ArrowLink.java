package MChain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ArrowLink {

	// s->b->e
	private float sX;
	private float sY;
	private float bX;
	private float bY;
	private float eX;
	private float eY;
	private float vx;
	private float vy;
	private float L;
	private float disp;
	private JPanel p1;
	private JPanel p2;
	private float tW;
	private float tH;
	private float coeff;
	private JLabel clabel;
	
	public ArrowLink(JPanel p1, JPanel p2, float disp, float coeff, JPanel bg)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.disp = disp;
		this.tW = p2.getWidth();
		this.tH = p2.getHeight();
		if (coeff!=0.f)
		{
			this.coeff = coeff;
			Float cf = coeff;
			clabel = new JLabel(cf.toString());
			clabel.setFont(clabel.getFont().deriveFont(clabel.getFont().getSize()*0.75f) );
			clabel.setOpaque(true);
			clabel.setBackground(Color.LIGHT_GRAY);
		}
		else
		{
			clabel = new JLabel();
			clabel.setOpaque(false);
		}
		update();
		bg.add(clabel);
		bg.repaint();
		clabel.validate();
		clabel.repaint();
	}
	
	public void draw (Graphics2D g)
	{
		QuadCurve2D curve = new QuadCurve2D.Float(sX, sY, bX, bY, eX, eY);
        g.draw(curve);
        
        		float t = 0.5f;
        float cx = (1 - t) * (1 - t) * eX + 2 * (1 - t) * t * bX + t * t * sX;
        float cy = (1 - t) * (1 - t) * eY + 2 * (1 - t) * t * bY + t * t * sY;
		
        if (p1!=p2)
        {
	        float xa = sX - 2*bX + eX;
	        float ya = sY - 2*bY + eY;
	        float xb = 2*bX - 2*sX;
	        float yb = 2*bY - 2*sY;
			float A = 4*(xa*xa + ya*ya);
			float B = 4*(xa*xb + ya*yb);
			float C = xb*xb + yb*yb;
			float Sabc = 2*(float)Math.sqrt(A+B+C);
			float A_2 = (float)Math.sqrt(A);
			float A_32 = 2*A*A_2;
			float C_2 = 2*(float)Math.sqrt(C);
			float BA = B/A_2;
			float len = ( A_32*Sabc + 
			          A_2*B*(Sabc-C_2) + 
			          (4*C*A-B*B)*(float)Math.log( (2*A_2+BA+Sabc)/(BA+C_2) ) 
			        )/(4*A_32);
	
	        t = (tW/2) / len;
	        float hx = (1 - t) * (1 - t) * eX + 2 * (1 - t) * t * bX + t * t * sX;
	        float hy = (1 - t) * (1 - t) * eY + 2 * (1 - t) * t * bY + t * t * sY;
	
	        t = (tW/2+5.f) / len;
	        float hcx = (1 - t) * (1 - t) * eX + 2 * (1 - t) * t * bX + t * t * sX;
	        float hcy = (1 - t) * (1 - t) * eY + 2 * (1 - t) * t * bY + t * t * sY;
	
	        float h2x = hcx - 4.f * vy;
	        float h2y = hcy + 4.f * vx;
	        float h3x = hcx + 4.f * vy;
	        float h3y = hcy - 4.f * vx;
	        
	        Line2D l1 = new Line2D.Float(hx,hy,h2x,h2y);
	        Line2D l2 = new Line2D.Float(hx,hy,h3x,h3y);
	        g.draw(l1);
	        g.draw(l2);
        }
	        
        clabel.setBounds((int)(cx-clabel.getPreferredSize().width/2),
        		(int)(cy-clabel.getPreferredSize().height/2),
        		clabel.getPreferredSize().width, clabel.getPreferredSize().height);
	}
	
	public void update()
	{
		if (p1!=p2)
		{
			sX = p1.getX()+p1.getWidth()*0.5f;
			sY = p1.getY()+p1.getHeight()*0.5f;
			eX = p2.getX()+p2.getWidth()*0.5f;
			eY = p2.getY()+p2.getHeight()*0.5f;
			L = (float)Math.sqrt(((eX-sX)*(eX-sX))+((eY-sY)*(eY-sY)));
			vx = (eX-sX)/L;
			vy = (eY-sY)/L;
			bX = ((eX+sX)*0.5f) + vy * disp;
			bY = ((eY+sY)*0.5f) - vx * disp;
		}
		else
		{
			sX = p1.getX()+p1.getWidth()*0.5f;
			sY = p1.getY()+p1.getHeight();
			eX = p2.getX();
			eY = p2.getY()+p2.getHeight()*0.5f;
			L = (float)Math.sqrt(((eX-sX)*(eX-sX))+((eY-sY)*(eY-sY)));
			vx = (eX-sX)/L;
			vy = (eY-sY)/L;
			bX = ((eX+sX)*0.5f) + vy * disp;
			bY = ((eY+sY)*0.5f) - vx * disp;
		}
		
	}	
	
	
}
