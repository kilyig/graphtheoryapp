import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph
{
	private String label;
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
		
	public Graph()
	{
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		label = "";
	}
	
	public Graph(Graph that)
	{
		this.label = that.getLabel();
		this.vertices = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
		for(Vertex v: that.vertices())
		{
			this.add(new Vertex(v.getLabel()));
		}
		for(Edge oldEdge: that.edges())
		{
			Vertex newEndpoint1 = this.vertices.get(this.vertices.indexOf(oldEdge.getEndpoint1()));
			Vertex newEndpoint2 = this.vertices.get(this.vertices.indexOf(oldEdge.getEndpoint2()));
			Edge newEdge = new Edge(oldEdge.getLabel(), newEndpoint1, newEndpoint2);
			newEdge.setDirected(oldEdge.isDirected());
			this.add(newEdge);
		}
	}
	
	public Graph(String label)
	{
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.label = label;
	}
	
	public void add(Vertex vertex)
	{
		if(!contains(vertex))
		{
			vertices.add(vertex);
			//System.out.println(toString());
		}
	}
	
	public boolean contains(Vertex vertex)
	{
		for(Vertex v: vertices)
		{
			if(v.equals(vertex))
				return true;
		}
		return false;
	}
	
	public boolean contains(Edge edge)
	{
		for(Edge e: edges)
		{
			if(e.equals(edge))
				return true;
		}
		return false;
	}
	
	public ArrayList<Vertex> vertices()
	{
		return vertices;
	}
	
	public ArrayList<Edge> edges()
	{
		return edges;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String toString()
	{
		String output = "Graph name: " + getLabel() + "\n";
		output += "G = (V,E)" + "\n";
		output += "V = " + vertexSetString() + "\n";
		output += "E = " + edgeSetString();
		return output;
	}
	
	public String vertexSetString()
	{
		String output = "{";
		int count = 0;
		for(Vertex v: vertices)
		{
			output += v.getLabel();
			count++;
			if(!(count == vertices.size()))
				output += ", ";
		}
		output += "}";
		return output;
	}
	
	public String edgeSetString()
	{
		String output = "{";
		int count = 0;
		for(Edge e: edges)
		{
			output += e.endpointRepresentationString();
			count++;
			if(!(count == edges.size()))
				output += ", ";
		}
		output += "}";
		return output;
	}
	
	public void add(Edge e)
	{
		if(contains((e.getEndpoint1())) && contains((e.getEndpoint2())))
		{
			edges.add(e);
			//System.out.println(toString());
		}
	}
	
	public int vertexCount()
	{
		return vertices.size();
	}
	
	public int edgeCount()
	{
		return edges.size();
	}
	
}
