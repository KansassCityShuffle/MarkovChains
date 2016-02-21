package MChain;

import java.util.ArrayList;
import java.util.List;

public class EClass extends Node {
	
	private List<State> states = new ArrayList<State>();
	//private List<EClass> successors = new ArrayList<EClass>();
	
	public EClass(int id, Matrix m, State initial)
	{
		super(id, m);
		this.states.add(initial);
	}
	
	public EClass(int id, String name, Matrix m, State initial)
	{
		super(id, name, m);
		this.states.add(initial);
	}
	
	public boolean isAdjacent(EClass nodeL, Matrix m)
	{
		return super.isAdjacent(nodeL, m);
	}
	
	public void buildClassSuccessors(List<EClass> classList, Matrix m)
	{
		List<Node> tmp = new ArrayList<Node>();
		List<EClass> goalList = new ArrayList<EClass>();
		goalList = classList;
		//goalList.remove(this);
		for(int j = 0; j < goalList.size(); j++)
		{
			if( this.isAdjacent(goalList.get(j), m) )
			{
				tmp.add( goalList.get(j) );
			}
		}
		
		this.setSuccessors(tmp, 0);
	}
	
	private void setSuccessors(List<Node> successors, int i) {
		super.setSuccessors(successors); 
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

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	public String toString()
	{
		String str = new String();
		str += this.getName() + " = {"; 
		for(State st : states)
		{
			str += " " + st.getName();
		}
		str += " } ";
		return str; 
	}

}
