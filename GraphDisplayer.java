//https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JComponent;

public class GraphDisplayer extends JComponent
{
	Embedding E;
	double vertexRadius = 10;
	float edgeWidth = 5;
	double varX = 0;
	double varY = 0;
	
	Color vertexColor = Color.BLACK;
	Color edgeColor = Color.GRAY;
	
	
	Graphics2D g2;
	
	int count = 0;
	
	public GraphDisplayer(Embedding E)
	{
		addMouseListener(new EmbeddingListener());
		addMouseMotionListener(new EmbeddingListener());
		this.E = E;
	}
	
	public GraphDisplayer()
	{
		addMouseListener(new EmbeddingListener());
		addMouseMotionListener(new EmbeddingListener());
		Graph G = new Graph("G");
		
		/*
		Vertex V1 = new Vertex("V1");
		Vertex V2 = new Vertex("V2");
		Vertex V3 = new Vertex("V3");
		Vertex V4 = new Vertex("V4");
		
		Edge E1 = new Edge(V1,V2);
		Edge E2 = new Edge(V4,V2);
		Edge E3 = new Edge(V2,V3);
		Edge E4 = new Edge(V3,V4);

		G.addVertex(V1);
		G.addVertex(V2);
		G.addVertex(V3);
		G.addVertex(V4);
		G.addEdge(E1);
		G.addEdge(E2);
		G.addEdge(E3);
		G.addEdge(E4);
		
		*/
		E = new Embedding(G);
		/*
		E.setLocation(V1, Math.random(), Math.random());
		E.setLocation(V2, Math.random(), Math.random());
		E.setLocation(V3, Math.random(), Math.random());
		E.setLocation(V4, Math.random(), Math.random());
		*/
	}
	
	public void paintComponent(Graphics g)
	{
		g2 = (Graphics2D) g;		
		
		int edgePlacer = 0;
		g2.setStroke(new BasicStroke(edgeWidth));
		g2.setColor(edgeColor);
		while(edgePlacer < E.getGraph().getEdgeList().size())
		{
			Edge currentEdge = E.getGraph().getEdgeList().get(edgePlacer);
			Point2D.Double locationofEndpoint1 = E.getVertexLocations().get(currentEdge.getEndpoint1());
			Point2D.Double locationofEndpoint2 = E.getVertexLocations().get(currentEdge.getEndpoint2());
			
			g2.drawLine((int) (locationofEndpoint1.getX()*getWidth()), (int) (locationofEndpoint1.getY()*getHeight()),
						(int) (locationofEndpoint2.getX()*getWidth()), (int) (locationofEndpoint2.getY()*getHeight()));
			edgePlacer++;
		}
		
		g2.setColor(vertexColor);
		int vertexPlacer = 0;
		while(vertexPlacer < E.getVertexLocations().size())
		{
			
			Point2D.Double location = E.getVertexLocations().get(E.getGraph().getVertexList().get(vertexPlacer));
			
			double centerX = getWidth()*location.getX();
			double centerY = getHeight()*location.getY();
			Ellipse2D.Double vertex = new Ellipse2D.Double(centerX-vertexRadius, centerY-vertexRadius, 2*vertexRadius, 2*vertexRadius);
			g2.fill(vertex);
			vertexPlacer++;
		}
	}
	
	public void drawEdgeAide(double x1, double y1, double x2, double y2)
	{
		g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g2.drawRect(100, 100, 300, 300);
	}

	public Embedding getEmbedding()
	{
		return E;
	}
	
	public void setEmbedding(Embedding E)
	{
		this.E = E;
	}
	
	private class EmbeddingListener implements MouseListener, MouseMotionListener
	{
		double initX;
		double initY;
		
		Graphics2D g3 = g2;

		@Override
		public void mouseClicked(MouseEvent e) 
		{				
			double x = (double)(e.getX())/(double)(getWidth());
			double y = (double)(e.getY())/(double)(getHeight());
			
			Vertex newVertex = new Vertex("V" + (E.getGraph().getVertexList().size()+1));
			E.getGraph().addVertex(newVertex);
			E.setLocation(E.getGraph().getVertexList().get(E.getGraph().getVertexList().size()-1), x, y);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			initX = (double)(e.getX())/(double)(getWidth());
			initY = (double)(e.getY())/(double)(getHeight());	
		}
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			double finalX = (double)(e.getX())/(double)(getWidth());
			double finalY = (double)(e.getY())/(double)(getHeight());

			if(finalX != initX && finalY != initY)
			{
				if((closestVertex(initX, initY) != null) && (closestVertex(finalX, finalY) != null))
				{
					Edge newEdge = new Edge(closestVertex(initX, initY), closestVertex(finalX, finalY));
					E.getGraph().addEdge(newEdge);
					repaint();
				}
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e)
		{

		}
		
		@Override
		public void mouseExited(MouseEvent e)
		{

		}
		
		@Override
		public void mouseDragged(MouseEvent e)
		{
			/*
			System.out.println("initX = " + initX);
			System.out.println("initX*getWidth() = " + (int)(initX*getWidth()));
			System.out.println("initY*getHeight() = " + initY*getHeight());
			System.out.println("e.getX() = " + e.getX());
			System.out.println("e.getY() = " + e.getY());
			
			drawEdgeAide(initX*getWidth(), initY*getHeight(), e.getX(), e.getY());
			*/
			//drawEdgeAide(initX*getWidth(), initY*getHeight(), e.getX(), e.getY());
			//g3.drawLine((int)(initX*getWidth()), (int)(initY*getHeight()), e.getX(), e.getY());
		}
		@Override
		public void mouseMoved(MouseEvent e) {}
		
		private Vertex closestVertex(double x, double y)
		{
			double minDistance = 5*vertexRadius;
			Vertex closestVertex = null;
			int vertexIterator = 0;
			while(vertexIterator < E.getVertexLocations().size())
			{
				Vertex currentVertex = E.getGraph().getVertexList().get(vertexIterator);
				Point2D.Double location = E.getVertexLocations().get(currentVertex);
				/*
				System.out.println("Checkng vertex " + currentVertex.getLabel());
				System.out.println(location.getX());
				System.out.println(location.getY());
				*/
				if(Point2D.Double.distance(x*getWidth(), y*getHeight(), location.getX()*getWidth(), location.getY()*getHeight()) <= minDistance)
				{
					minDistance = Point2D.Double.distance(x*getWidth(), y*getHeight(), location.getX()*getWidth(), location.getY()*getHeight());
					closestVertex = currentVertex;
				}
				vertexIterator++;
			}
			return closestVertex;
		}

	}
}
