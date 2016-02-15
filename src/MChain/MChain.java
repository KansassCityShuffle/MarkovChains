package MChain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MChain {
	
	private Matrix m; 
	private List<ArrayList <State>> classes = new LinkedList<ArrayList<State>>();
	
	public MChain(Matrix m)
	{
		this.setM(m) ; 
	}

	public Matrix getM() {
		return m;
	}

	public void setM(Matrix m) {
		this.m = m;
	}

	private void buildClasses()
	{
		int cpt = 0; 
		ArrayList<State> tmp = new ArrayList<State>();
		for(int i = 0; i < this.m.getHeight(); i++)
		{
			State s = new State(i, this.m);
			tmp.add(s);
		}
		for(State st : tmp)
		{
			st.buildSuccessors(tmp, this.m);
		}
		for(int i = 0; i < this.m.getHeight() - 1; i ++)
		{
			for(int j = 1; j < this.m.getWidth(); j++)
			{
				if(!tmp.get(i).isExplored() && tmp.get(i).isAbsorbant())
				{		
					ArrayList<State> st = new ArrayList<State>(); 
					st.add(tmp.get(i));
					classes.add(st);
				}
				if(!tmp.get(j).isExplored() && tmp.get(j).isAbsorbant())
				{		
					ArrayList<State> st = new ArrayList<State>(); 
					st.add(tmp.get(j));
					classes.add(st);
				}
				
				else if(tmp.get(i).isAccessible(tmp.get(j), this.m.getHeight()) && 
						tmp.get(j).isAccessible(tmp.get(i), this.m.getHeight()));
				tmp.get(i).setExplored(true);
				tmp.get(j).setExplored(true);
			}
		}
	}
	
}
