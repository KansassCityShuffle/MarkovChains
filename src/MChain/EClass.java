package MChain;

public class EClass extends Node {
	
	public EClass(int id, Matrix m)
	{
		super(id, m);
	}
	
	public EClass(int id, String name, Matrix m)
	{
		super(id, name, m);
	}
	
	public int getId() {
		return super.getId();
	}
	
	public void setId(int id) {
		super.setId(id);
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

	public boolean isAbsorbant() {
		return super.isAbsorbant();
	}
	
	public void setAbsorbant(boolean b) {
		super.setAbsorbant(b);
	}

	public boolean isExplored(){
		return super.isExplored();
	}
	
	public void setExplored(boolean b) {
		super.setExplored(b);
	}
	
	public boolean isPersistent() {
		return super.isPersistent();
	}

	public void setPersistent(boolean b) {
		super.setPersistent(b);
	}
	
	public boolean isTransitory() {
		return super.isTransitory();
	}

	public void setTransitory(boolean b) {
		super.setTransitory(b);
	}
	
	public int getD() {
		return super.getD();
	}

	public void setD(int d) {
		super.setD(d);
	}
	
}
