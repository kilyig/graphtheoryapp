import java.awt.Color;
import java.util.ArrayList;

public class Vertex
{
	private String label;
	private ArrayList<Edge> edges;
	
	public Vertex()
	{
		label = null;
		edges = new ArrayList<Edge>();
	}
	
	/*
	public Vertex(Vertex that)
	{
		this.label = that.label;
		this.edges = new ArrayList<Edge>();
		for(Edge e: that.edges)
		{
			this.connectEdge(new Edge(e));
		}
	}
	*/
	
	public Vertex(String name)
	{
		this.label = name;
		edges = new ArrayList<Edge>();
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void connectEdge(Edge e)
	{
		edges.add(e);		
	}
	
	public void removeEdge(Edge e)
	{
		edges.remove(e);
	}
	
	public boolean isAdjacentTo(Vertex v)
	{
		for(Edge e: edges)
		{
			if(e.isIncidentTo(v))
				return true;
		}
		return false;
	}
	
	public boolean isAnEndpointOf(Edge e)
	{
		if(edges.contains(e))
			return true;
		else
			return false;
	}
	
	public int degree()
	{
		return edges.size();
	}
	
	public void printEdgeSet()
	{
		for(Edge e: edges)
		{
			System.out.print(e.endpointRepresentationString());
		}
		System.out.println("");
	}
	
	@Override
	public boolean equals(Object that)
	{
		if(this == that)
			return true;
		if(!(that instanceof Vertex))
			return false;

		Vertex thatVertex = (Vertex) that;
		  
		if(this.getLabel().equals(thatVertex.getLabel()))
			return true;
		return false;
	}
}
