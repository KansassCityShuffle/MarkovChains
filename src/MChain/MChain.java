package MChain;

import java.util.ArrayList;
import java.util.List;

public class MChain {
	
	private Matrix m; 
	private List<State> states = new ArrayList<State>();
	private List<EClass> classes = new ArrayList<EClass>();
	
	/**
	 * 
	 * @param m
	 */
	public MChain(Matrix m)
	{
		this.setM(m); 
		this.buildClasses();
		List<EClass> tmp = new ArrayList<EClass>();
		tmp.addAll(this.getClasses());
		for(EClass c : this.getClasses())
		{
			c.buildClassSuccessors(tmp, m);
		}
	}

	/**
	 * 
	 */
	private void buildClasses()
	{
		int cpt = 0; 
		ArrayList<State> tmp = new ArrayList<State>();
		for(int i = 0; i < this.m.getHeight(); i++)
		{
			State s = new State(i, "s" + i, this.m);
			tmp.add(s);
		}
		for(State st : tmp)
		{
			st.buildStateSuccessors(tmp, this.m);
		}
		for(int i = 0; i < this.m.getHeight() - 1; i ++)
		{
			if(!tmp.get(i).isExplored())
			{	
				EClass current = new EClass( i, "C" + i, this.m, tmp.get(i) );
				if(tmp.get(i).isAbsorbant())
					cpt++;
				else 
				{
					for(int j = 1; j < m.getWidth(); j++)
					{
						if(!tmp.get(j).isExplored() && i != j)
						{
							if(tmp.get(j).isAbsorbant())
							{
								classes.add( cpt, new EClass( i, "C" + j, this.m, tmp.get(j) ) ); 
								tmp.get(j).setExplored(true);
								cpt++; 
							}
							
							else if(tmp.get(i).isAccessible(tmp.get(j), this.m.getHeight()) && 
									tmp.get(j).isAccessible(tmp.get(i), this.m.getHeight()))
							{	
								current.getStates().add(tmp.get(j));
								tmp.get(j).setExplored(true);
							}
						}
					}
				}
				classes.add( current );
				cpt++;
				tmp.get(i).setExplored(true);
			}
		}
		states.addAll(tmp);
	}

	
	public void setProperties()
	{
		for(EClass c : this.getClasses())
		{
			/* set Absorption property on each class (states absorption is already defined) */
			c.setAbsorbant(c.getStates().size() == 1 && c.getStates().get(0).isAbsorbant());
			
			
		}
	}
	
	/**
	 * 
	 */
	public void printClasses()
	{
		for(int i = 0; i < this.getClasses().size(); i++)
		{	
			System.out.println(this.getClasses().get(i).toString());
			System.out.println("successors " + this.getClasses().get(i).getSuccessors().toString());
		}
		
	}
	
	public Matrix getM() {
		return m;
	}

	public void setM(Matrix m) {
		this.m = m;
	}
	
	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	public List<EClass> getClasses() {
		return classes;
	}

	public void setClasses(List<EClass> classes) {
		this.classes = classes;
	}
	
}
