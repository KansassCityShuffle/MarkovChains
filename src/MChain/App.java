package MChain;

import java.io.File;
import java.util.List;



public class App {

	private static final float[][] values5x5 = 
	{
			{1, 0, 0, 0, 0},
			{0, 1, 0, 0, 0},
			{0.1f, 0, 0.5f, 0.2f, 0.2f},
			{0, 0.5f, 0, 0.5f, 0},
			{0.5f, 0, 0.5f, 0, 0}
	}; 
	


	public static void main(String[] args) {
		
		System.out.println("Super Andreï Markov Chains Application");
		
		Matrix a = new Matrix(5, 5, values5x5);
		
		if(!a.isWellFormed())
			return;
		
		System.out.println(a.toString());
		Matrix b = a.multiply(a);
		
		if(!b.isWellFormed())
			return; 
	
		System.out.println(b.toString());
	
		MChain mc = new MChain(a);
		mc.printClasses();

		
		// graphic stuff
		Window ctx = new Window();
		ctx.create(mc);

		 
	}

}
