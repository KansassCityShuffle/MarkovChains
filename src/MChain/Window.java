package MChain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	// random java stuff
	private static final long serialVersionUID = 806842610383597477L;

	// base components
	private BGPanel bg;
	private ArrayList<SPanel> statePanelList;
	
	/**
	 * Create the window
	 */
	public void create() {
        
        setTitle("Super Andreï Markov Chains Application");
        setSize(640, 480);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        bg = new BGPanel(null);
        bg.setBackground(Color.LIGHT_GRAY);
        add(bg);
        
        setVisible(true);
    }
	
	/**
	 * Displays a graph of interconnected states
	 */
	public void renderStateGraph(MChain chain)
	{
		statePanelList = new ArrayList<SPanel>();
		int cpt = 0;
		
		for (State curState : chain.getStates())
		{
			SPanel nPanel = new SPanel(new BorderLayout(), curState.getName(), cpt*100 +100, 100);
			nPanel.setState(curState);
			statePanelList.add(nPanel);
			bg.add(nPanel);
			cpt++;
		}

		for (SPanel panel2 : statePanelList)
		{
			for (SPanel panel : statePanelList)
			{
				for (int i=0; i < panel2.getState().getSuccessors().size(); i++)
				{
					if (panel.getState()==panel2.getState().getSuccessors().get(i))
						bg.link(panel, panel2);
				}
			}
		}
		

	}
	
}
