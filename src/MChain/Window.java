package MChain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Window extends JFrame {

	// random java stuff
	private static final long serialVersionUID = 806842610383597477L;

	// base components
	private ArrayList<SPanel> statePanelList;
	private ArrayList<CPanel> classPanelList;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	JDesktopPane desktop;
	
	private MChain curChain;
		
	
	
	private void newChain()
	{
        curChain = new MChain(null);
	}
	
	private void saveChain()
	{
		JFileChooser saveFile = new JFileChooser();
        saveFile.showSaveDialog(null);
        curChain.save(saveFile.getSelectedFile());
        
	}
	
	private void loadChain()
	{
		JFileChooser loadFile = new JFileChooser();
        loadFile.showOpenDialog(null);
        curChain = curChain.load(loadFile.getSelectedFile());
		renderStateGraph(curChain);
	}
	
	/**
	 * Create the window
	 */
	public void create(MChain chain) {
        
        setTitle("Super Andreï Markov Chains Application");
        setSize(640, 480);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        curChain = chain;
        
        menuBar = new JMenuBar();
        menu = new JMenu("Chain");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext().setAccessibleDescription(
                "General chain manipulation");
        menuBar.add(menu);

        menuItem = new JMenuItem("New", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Create new chain from scratch");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	newChain();
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Save chain to disk");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	saveChain();
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Load chain from disk");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                loadChain();
            }
        });
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Exit the application");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        menu.add(menuItem);

        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription(
                "Graphs");
        menuItem = new JMenuItem("States graph", KeyEvent.VK_G);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Display a graph of connected states");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	renderStateGraph(curChain);
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Classes graph", KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Display a graph of connected classes");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	renderClassGraph(curChain);
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Matrix", KeyEvent.VK_M);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_M, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Display the matrix");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	renderMatrix(curChain);
            }
        });
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Tile windows", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Tile the windows");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	tile(desktop);
            }
        });
        menu.add(menuItem);
        
        
        menuBar.add(menu);

        menu = new JMenu("Nodes");
        menu.setMnemonic(KeyEvent.VK_O);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuItem = new JMenuItem("Add", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Add state");
        menu.add(menuItem);
        menuItem = new JMenuItem("Delete", KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Delete state");
        menu.add(menuItem);
        menuItem = new JMenuItem("Organize", KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Organize states");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //bg.organize();
            }
        });
        menu.add(menuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);
        
        desktop = new JDesktopPane();
        desktop.setBackground(Color.LIGHT_GRAY);
    	add(desktop, BorderLayout.CENTER);
    	
        setVisible(true);
    	
    	if (curChain!=null)
    	{
			renderStateGraph(curChain);
			renderClassGraph(curChain);
			tile(desktop);
    	}

        
        
    }
	
	/**
	 * Display a graph of interconnected states
	 */
	public void renderStateGraph(MChain chain)
	{
		JInternalFrame frame = new JInternalFrame("States Graph");
		frame.setSize(200, 200);

		desktop.add(frame);

		frame.setVisible(true);	
		frame.setResizable(true);
		frame.setMaximizable(true);
		frame.setClosable(true);
		frame.setIconifiable(true);
		frame.setBackground(Color.LIGHT_GRAY);
	
	
		BGPanel bg = new BGPanel(null);
        bg.setBackground(Color.LIGHT_GRAY);

        
        if (statePanelList!=null) statePanelList.clear();

		statePanelList = new ArrayList<SPanel>();
		int cpt = 0;
		
		for (State curState : chain.getStates())
		{
			SPanel nPanel = new SPanel(new BorderLayout(), curState.getName(), cpt*100 +20, 20);
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
		
		frame.add(bg);
		//frame.setSize(bg.getPreferredSize().width, bg.getPreferredSize().height);
		bg.setVisible(true);
        bg.revalidate();

	}
	
	public void renderClassGraph(MChain chain)
	{
		JInternalFrame frame = new JInternalFrame("Classes Graph");
		frame.setSize(200, 200);

		desktop.add(frame);

		frame.setVisible(true);	
		frame.setResizable(true);
		frame.setMaximizable(true);
		frame.setClosable(true);
		frame.setIconifiable(true);
		frame.setBackground(Color.LIGHT_GRAY);
	
	
		BGPanel bg = new BGPanel(null);
        bg.setBackground(Color.LIGHT_GRAY);

		if (classPanelList!=null) classPanelList.clear();

        classPanelList = new ArrayList<CPanel>();
		int cpt = 0;
		
		for (EClass curClass : chain.getClasses())
		{
			CPanel nPanel = new CPanel(new BorderLayout(), curClass.getName(), cpt*100 +20, 20);
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
		frame.add(bg);
		//frame.setSize(bg.getPreferredSize().width, bg.getPreferredSize().height);
		bg.setVisible(true);
        bg.revalidate();
	}
	
	public void renderMatrix(MChain chain)
	{
		JInternalFrame matFrame = new JInternalFrame("Matrix");
		matFrame.setSize(200, 200);

		desktop.add(matFrame);

		matFrame.setVisible(true);	
		matFrame.setClosable(true);
		matFrame.setIconifiable(true);
		matFrame.setBackground(Color.LIGHT_GRAY);
	
		JLabel matLabel = new JLabel("<html>");
		for (int i=0;i<chain.getM().getHeight();i++)
		{
			for (int j=0;j<chain.getM().getHeight();j++)
				matLabel.setText(matLabel.getText()+chain.getM().at(i, j)+" ");
			matLabel.setText(matLabel.getText()+"<br/>");
		}
		
		matLabel.setText(matLabel.getText()+"</html>");
		
		matFrame.add(matLabel);
		matFrame.setSize(matLabel.getPreferredSize().width+12, matLabel.getPreferredSize().height+30);
		matLabel.setVisible(true);
		
	}
	
	public void tile( JDesktopPane desktopPane, int layer ) {
	    JInternalFrame[] frames = desktopPane.getAllFramesInLayer( layer );
	    if ( frames.length == 0) return;
	 
	    tile( frames, desktopPane.getBounds() );
	}
	public void tile( JDesktopPane desktopPane ) {
	    JInternalFrame[] frames = desktopPane.getAllFrames();
	    if ( frames.length == 0) return;
	 
	    tile( frames, desktopPane.getBounds() );
	}
	private void tile( JInternalFrame[] frames, Rectangle dBounds ) {
	    int cols = (int)Math.sqrt(frames.length);
	    int rows = (int)(Math.ceil( ((double)frames.length) / cols));
	    int lastRow = frames.length - cols*(rows-1);
	    int width, height;
	 
	    if ( lastRow == 0 ) {
	        rows--;
	        height = dBounds.height / rows;
	    }
	    else {
	        height = dBounds.height / rows;
	        if ( lastRow < cols ) {
	            rows--;
	            width = dBounds.width / lastRow;
	            for (int i = 0; i < lastRow; i++ ) {
	                frames[cols*rows+i].setBounds( i*width, rows*height,
	                                               width, height );
	            }
	        }
	    }
	            
	    width = dBounds.width/cols;
	    for (int j = 0; j < rows; j++ ) {
	        for (int i = 0; i < cols; i++ ) {
	            frames[i+j*cols].setBounds( i*width, j*height,
	                                        width, height );
	        }
	    }
	}
}
