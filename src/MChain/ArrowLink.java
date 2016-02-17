package MChain;

public class ArrowLink {

	// s->b->e
	private int sX;
	private int sY;
	private int bX;
	private int bY;
	private int eX;
	private int eY;
	private SPanel p1;
	private SPanel p2;
	
	public ArrowLink(SPanel p1, SPanel p2, int disp)
	{

		this.sX = p1.getX();
		this.sY = p1.getY();
		this.eX = p2.getX();
		this.eY = p2.getY();
		this.setP1(p1);
		this.setP2(p2);
	}
	
	public void update()
	{
		this.sX = p1.getX();
		this.sY = p1.getY();
		this.eX = p2.getX();
		this.eY = p2.getY();
	}

	public int getsX() {
		return sX;
	}

	public void setsX(int sX) {
		this.sX = sX;
	}

	public int getsY() {
		return sY;
	}

	public void setsY(int sY) {
		this.sY = sY;
	}

	public int getbX() {
		return bX;
	}

	public void setbX(int bX) {
		this.bX = bX;
	}

	public int getbY() {
		return bY;
	}

	public void setbY(int bY) {
		this.bY = bY;
	}

	public int geteX() {
		return eX;
	}

	public void seteX(int eX) {
		this.eX = eX;
	}

	public int geteY() {
		return eY;
	}

	public void seteY(int eY) {
		this.eY = eY;
	}

	public SPanel getP1() {
		return p1;
	}

	public void setP1(SPanel p1) {
		this.p1 = p1;
	}

	public SPanel getP2() {
		return p2;
	}

	public void setP2(SPanel p2) {
		this.p2 = p2;
	}
	
	
	
}
