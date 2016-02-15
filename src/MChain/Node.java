package MChain;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Node {
	
	private int id; 
	private String name;
	private ArrayList<Node> successors; 
	
	private int d; 
	private boolean n_isAbsorbant; 
	private boolean n_isPersistent; 
	private boolean n_isTransitory;
	private boolean n_isExplored; 
	 
	public Node(int id, Matrix m)
	{ 
		this.setId(id); 
		this.setName(this.getName() + id); 
		this.setExplored(false);
		this.setAbsorbant( m.at(id, id) == 1 );
	}

	public Node(int id, String name, Matrix m)
	{
		this.setId(id); 
		this.setName(name); 
		this.setExplored(false);
		this.setAbsorbant( m.at(id, id) == 1 );
	}
	
	public boolean isAccessible(Node node, int size)
	{
		Stack<Node> stack = new Stack<Node>();
		stack.push(this);
		boolean[] visited = new boolean[size];
		while(!stack.isEmpty())
		{
			Node current_n = stack.pop();
			if( !visited[current_n.getId()] && !current_n.isAbsorbant() )
			{
				visited[current_n.getId()] = true; 
				if( current_n == node )
					return true;
				else 
				{
					for(Node tmp : this.getSuccessors())
					{
						stack.push(tmp);
					}
				}
			}
			else 
				return false; 
		}
		return false;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Node> getSuccessors() {
		return successors;
	}

	public void setSuccessors(ArrayList<Node> successors) {
		this.successors = successors;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public boolean isAbsorbant() {
		return n_isAbsorbant;
	}

	public void setAbsorbant(boolean n_isAbsorbant) {
		this.n_isAbsorbant = n_isAbsorbant;
	}

	public boolean isPersistent() {
		return n_isPersistent;
	}

	public void setPersistent(boolean n_isPersistent) {
		this.n_isPersistent = n_isPersistent;
	}

	public boolean isTransitory() {
		return n_isTransitory;
	}

	public void setTransitory(boolean n_isTransitory) {
		this.n_isTransitory = n_isTransitory;
	}

	public boolean isExplored() {
		return n_isExplored;
	}

	public void setExplored(boolean n_isExplored) {
		this.n_isExplored = n_isExplored;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
