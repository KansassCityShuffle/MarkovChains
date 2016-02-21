package MChain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Window extends JFrame {

	// random java stuff
	private static final long serialVersionUID = 806842610383597477L;

	// base components
	private BGPanel bg;
	private ArrayList<SPanel> statePanelList;
	private ArrayList<CPanel> classPanelList;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	//flags
	
	/**
	 * Create the window
	 */
	public void create() {
        
        setTitle("Super Andreï Markov Chains Application");
        setSize(640, 480);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        
        menuBar = new JMenuBar();
        menu = new JMenu("Chain");
        menu.setMnemonic(KeyEvent.VK_C);
        menu.getAccessibleContext().setAccessibleDescription(
                "General chain manipulation");
        menuBar.add(menu);

        menuItem = new JMenuItem("New", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Create new chain from scratch");
        menu.add(menuItem);
        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Save chain to disk");
        menu.add(menuItem);
        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Load chain from disk");
        menu.add(menuItem);
        menuItem = new JMenuItem("Close", KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Unload chain");
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Exit the application");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);;
            }
        });
        menu.add(menuItem);

        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription(
                "Graphs");
        menuItem = new JMenuItem("States graph", KeyEvent.VK_G);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_5, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Display a graph of connected states");
        menu.add(menuItem);
        menuItem = new JMenuItem("Classes graph", KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_6, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Display a graph of connected classes");
        menu.add(menuItem);
        menuItem = new JMenuItem("Matrix", KeyEvent.VK_M);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_7, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Display the matrix");
        menu.add(menuItem);

        menuBar.add(menu);

        menu = new JMenu("Nodes");
        menu.setMnemonic(KeyEvent.VK_O);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuItem = new JMenuItem("Add", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_8, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Add state");
        menu.add(menuItem);
        menuItem = new JMenuItem("Delete", KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_9, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Delete state");
        menu.add(menuItem);
        menuItem = new JMenuItem("Organize", KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_0, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Organize states");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                bg.organize();
            }
        });
        menu.add(menuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);
        
        
        bg = new BGPanel(null);
        bg.setBackground(Color.LIGHT_GRAY);
        add(bg);
        
        setVisible(true);
        
    }
	
	/**
	 * Display a graph of interconnected states
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
						bg.link(panel, panel2, chain.getM());
				}
			}
		}
		

	}
	
	public void renderClassGraph(MChain chain)
	{
		classPanelList = new ArrayList<CPanel>();
		int cpt = 0;
		
		for (EClass curClass : chain.getClasses())
		{
			CPanel nPanel = new CPanel(new BorderLayout(), curClass.getName(), cpt*100 +100, 300);
			nPanel.setClass(curClass);
			classPanelList.add(nPanel);
			bg.add(nPanel);
			cpt++;
		}

		for (CPanel panel2 : classPanelList)
		{
			for (CPanel panel : classPanelList)
			{
				
				for (int i=0; i < panel2.getEClass().getSuccessors().size(); i++)
				{
					if (panel.getEClass()==panel2.getEClass().getSuccessors().get(i))
						bg.link(panel, panel2);
				}
				
			}
		}
		

	}
	
}
