import java.util.ArrayList;
import java.util.Collections;

public class GraphAnalyzer
{
	public static boolean isEdgeless(Graph G)
	{
		if(G.edges().size() == 0)
			return true;
		return false;
	}
	
	public static boolean isNullGraph(Graph G)
	{
		if(G.vertices().size() == 0)
			return true;
		return false;
	}
	
	public static boolean isCone(Graph G)
	{
		for(Vertex v: G.vertices())
		{
			if(v.degree() == G.vertexCount()-1)
				return true;
		}
		return false;
	}
	
	public static boolean containsIsolatedVertex(Graph G)
	{
		for(Vertex v: G.vertices())
		{
			if(v.degree() == 0)
				return true;
		}
		return false;
	}
	
	public static boolean containsLeaf(Graph G)
	{
		for(Vertex v: G.vertices())
		{
			if(v.degree() == 1)
				return true;
		}
		return false;
	}
	
	public static String degreeSequence(Graph G)
	{
		ArrayList<Integer> degrees = new ArrayList<Integer>();
		for(Vertex x: G.vertices())
			degrees.add(x.degree());
		Collections.sort(degrees);
		String output = "";
		for(Integer d: degrees)
			output += d;
		return output;
	}
	
	public static boolean isEulerian(Graph G)
	{
		for(Vertex v: G.vertices())
			if(v.degree() % 2 != 0)
				return false;
		return true;
	}
	
	//public static boolean isConnected
	
}
