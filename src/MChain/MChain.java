package MChain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

		if (m != null)
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
	
	
	public void save(File file)
	{
		PrintWriter out = null;
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
	    try {
	        out = new PrintWriter(new FileWriter(file.getAbsolutePath()));
	        out.println(m.getHeight());
	        out.println(m.toString());
	    } catch (IndexOutOfBoundsException e) {
			e.printStackTrace();                 
	    } catch (IOException e) {
			e.printStackTrace();
	    } finally {
	        if (out != null)
	            out.close();
	    }		
	}
	
	public MChain load(File file)
	{
		BufferedReader br = null;
		String line = "";
		int size = 0;
		float vals[][] = null;
		
		try {

			br = new BufferedReader(new FileReader(file.getAbsolutePath()));
			int row = 0;
			if ((size = Integer.parseInt(br.readLine())) != 0)
				vals = new float[size][size];
				
			while ((line = br.readLine()) != null) {

				String[] elt = line.split(" ");
				if (elt.length!=size) break;
				for (int col=0; col < elt.length; col++)
					vals[row][col] = Float.parseFloat(elt[col]);
				row++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		}
		
		if (vals==null) return null;
		

		
		Matrix mat = new Matrix(size, size, vals);
		System.out.println(mat.toString());
		if(!mat.isWellFormed()) return null;
		MChain chain = new MChain(mat);

		return chain;
	}
	
	
}
