package MChain;

import java.util.ArrayList;
import java.util.Stack;

public class State extends Node {
	
	public State(int id, Matrix m)
	{
		super(id, m);
	}
	
	public State(int id, String name, Matrix m)
	{	
		super(id, name, m);
	}

	public void buildSuccessors(ArrayList<State> nodeList, Matrix m)
	{
		ArrayList<State> tmp = new ArrayList<State>();
		for(int j = 0; j < m.getWidth(); j++)
		{
			if( m.at(super.getId(), j) > 0 )
			{
				tmp.add( nodeList.get(j) );
			}
		}
		this.setSuccessors(tmp);
	}
	
	public boolean isAccessible(State state, int size)
	{
		return super.isAccessible(state, size);
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