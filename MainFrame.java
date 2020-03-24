import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	private final int FRAME_WIDTH = 1200;
	private final int FRAME_HEIGHT = 900;
	
	GraphDisplayer gd;
	JButton tryFunctions;
	
	public MainFrame()
	{
		prepareFrame();
		initComponents();
		addComponents();
	}

	private void prepareFrame()
	{
	    setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    setResizable(true);
	    setTitle("Graph Theory Simulator");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	private void initComponents()
	{
		gd = new GraphDisplayer();
		tryFunctions = new JButton("Try functions");
		tryFunctions.addActionListener(new GraphListener());
	}

	private void addComponents()
	{
		add(gd, BorderLayout.CENTER);
		add(tryFunctions, BorderLayout.SOUTH);
	}
	
	class GraphListener implements ActionListener
	{
	 	public void actionPerformed(ActionEvent e)
	 	{
	 		if(e.getSource() == tryFunctions)
	 		{
	 			System.out.println("G is null graph: " + GraphAnalyzer.isNullGraph(gd.E.getGraph()));
	 			System.out.println("G is edgeless: " + GraphAnalyzer.isEdgeless(gd.E.getGraph()));
	 			System.out.println("G is a cone: " + GraphAnalyzer.isCone(gd.E.getGraph()));
	 			System.out.println("G contains leaf: " + GraphAnalyzer.containsLeaf(gd.E.getGraph()));
	 			System.out.println("G contains isolated vertex: " + GraphAnalyzer.containsIsolatedVertex(gd.E.getGraph()));
	 			System.out.println("Degree sequence of G: " + GraphAnalyzer.degreeSequence(gd.E.getGraph()));
	 		}
	 	}
	}
	
	
	
}
