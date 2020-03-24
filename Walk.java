import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Walk
{
	private Graph G;
	private ArrayList<Vertex> vertexSequence = new ArrayList<Vertex>();
	private ArrayList<Edge> edgeSequence = new ArrayList<Edge>();
	
	public Walk(Graph G)
	{
		this.G = G;
	}

	public Walk(Walk that)
	{
		this.G = new Graph(that.getGraph());
		Vertex firstVertex = this.G.vertices().get(this.G.vertices().indexOf(that.vertexSequence().get(0)));
		this.add(firstVertex);
		for(int i = 0; i < that.edgeSequence().size(); i++)
		{
			Edge oldEdge = that.edgeSequence().get(i);
			Vertex newEndpoint1 = this.G.vertices().get(this.G.vertices().indexOf(oldEdge.getEndpoint1()));
			Vertex newEndpoint2 = this.G.vertices().get(this.G.vertices().indexOf(oldEdge.getEndpoint2()));
			Edge newEdge = new Edge(oldEdge.getLabel(), newEndpoint1, newEndpoint2);
			newEdge.setDirected(oldEdge.isDirected());
			this.add(newEdge);
		}
	}
	
	public Graph getGraph()
	{
		return G;
	}
	
	public ArrayList<Vertex> vertexSequence()
	{
		return vertexSequence;
	}
	
	public ArrayList<Edge> edgeSequence()
	{
		return edgeSequence;
	}
	
	public void add(Edge e)
	{
		if(G.edges().contains(e))
		{
			if(vertexSequence.size() == 0)
			{
				System.out.println("Please first indicate the starting vertex.");
			}else if(vertexSequence.get(vertexSequence.size()-1).equals(e.getEndpoint1()))
			{
				edgeSequence.add(e);
				vertexSequence.add(e.getEndpoint2());
			}else if(vertexSequence.get(vertexSequence.size()-1).equals(e.getEndpoint2()))
			{
				edgeSequence.add(e);
				vertexSequence.add(e.getEndpoint1());
			}else
			{
				System.out.println("Edge " + e.getLabel() + " is not incident to vertex " + vertexSequence.get(vertexSequence.size()-1).getLabel());
			}
		}else
			System.out.println("Graph " + G.getLabel() + " does not contain edge " + e.getLabel());
	}
	
	public void add(Vertex v)
	{
		if(G.vertices().contains(v))
		{
			if(vertexSequence.size() != 0)
			{
				Vertex lastVertex = vertexSequence.get(vertexSequence.size()-1);
				int edgeCounter = 0;
				Edge possibleEdge = null;
				for(Edge e: G.edges())
				{
					if(e.allowsWalk(lastVertex, v))
					{
						possibleEdge = e;
						edgeCounter++;
					}
				}
				if(edgeCounter == 0)
					System.out.println("Graph " + G.getLabel() + " does not contain edge {" + vertexSequence.get(vertexSequence.size()-1).getLabel() + ", " + v.getLabel() + "}");
				else if(edgeCounter == 1)
				{
					vertexSequence.add(v);
					edgeSequence.add(G.edges().get(G.edges().indexOf(possibleEdge)));
				}else
					System.out.println("In graph " + G.getLabel() + ", there are " + edgeCounter + " ways to go from vertex " + lastVertex.getLabel() + " to " + v.getLabel() + ", so you must specify the edge you want to add.");
			}else
			{
				vertexSequence.add(v);
			}	
		}else
			System.out.println("Graph " + G.getLabel() + " does not contain vertex " + v.getLabel());
	}
	
	public void removeLast()
	{
		vertexSequence.remove(vertexSequence.size()-1);
		edgeSequence.remove(edgeSequence.size()-1);
	}
	
	public void removeFirst()
	{
		vertexSequence.remove(0);
		edgeSequence.remove(0);
	}
	
	public String toString()
	{
		String output = "<";
		for(int i = 0; i < vertexSequence.size(); i++)
		{
			output += vertexSequence.get(i).getLabel();
			if(i != (vertexSequence.size()-1))
				output += ", ";
		}
		output += ">";
		return output;
	}
	
	public int length()
	{
		if(vertexSequence.size() == 0)
			return 0;
		return (vertexSequence.size()-1);
	}
	
	public boolean isOpenWalk()
	{
		Vertex firstVertex = vertexSequence.get(0);
		Vertex lastVertex = vertexSequence.get(vertexSequence.size()-1);
		
		if(firstVertex.equals(lastVertex))
			return false;
		return true;
	}
	
	public boolean isClosedWalk()
	{
		return !isOpenWalk();
	}
	
	public boolean isTrail()
	{
		if(isOpenWalk() && noEdgeOccursMoreThanOnce())
			return true;
		return false;
	}
	
	public boolean noEdgeOccursMoreThanOnce()
	{
		Set<Edge> edgeSet = new HashSet<Edge>(edgeSequence);
		if(edgeSet.size() < edgeSequence.size())
			return false;
		return true;
	}
	
	public boolean noVertexOccursMoreThanOnce()
	{
		Set<Vertex> vertexSet = new HashSet<Vertex>(vertexSequence);
		if(vertexSet.size() < vertexSequence.size())
			return false;
		return true;
	}
	
	public boolean isCircuit()
	{
		if(isClosedWalk() && noEdgeOccursMoreThanOnce())
			return true;
		return false;
	}
	
	public boolean isPath()
	{
		if(isTrail() && noVertexOccursMoreThanOnce())
			return true;
		return false;
	}
	
	public boolean isCycle()
	{
		Walk removedEnds = new Walk(this);
		removedEnds.removeFirst();
		removedEnds.removeLast();
		if(isCircuit() && length() >= 1 && removedEnds.noVertexOccursMoreThanOnce())
			return true;
		return false;
	}
	
	public boolean containsEveryEdge()
	{
		for(Edge e: G.edges())
		{
			if(!edgeSequence.contains(e))
				return false;
		}
		return true;
	}
	
	public boolean containsEveryVertex()
	{
		for(Vertex v: G.vertices())
		{
			if(!vertexSequence.contains(v))
				return false;
		}
		return true;
	}
	
	public boolean isEulerCircuit()
	{
		if(isCircuit() && containsEveryVertex() && containsEveryEdge())
			return true;
		return false;
	}
	
	public boolean isEulerTrail()
	{
		if(isOpenWalk() && isTrail() && containsEveryEdge())
			return true;
		return false;
	}
	
	public boolean isHamiltonianCycle()
	{
		if(isCycle() && containsEveryVertex())
			return true;
		return false;
	}
	
	public boolean isHamiltonianPath()
	{
		if(isPath() && containsEveryVertex())
			return true;
		return false;
	}
	
}
