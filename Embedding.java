import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class Embedding
{
	Graph G;
	private HashMap<Vertex, Point2D.Double> vertexLocations;
	private HashMap<Vertex, Color> vertexColors;
	private HashMap<Edge, Color> edgeColors;
	
	public Embedding(Graph G)
	{
		this.G = G;
		vertexLocations = new HashMap<Vertex,Point2D.Double>();
	}
	
	public void setLocation(Vertex v, double locationX, double locationY)
	{
		if(0 <= locationX && locationX <= 1)
			if(0 <= locationY && locationY <= 1)
				if(getGraph().containsVertex(v))
					vertexLocations.put(v, new Point2D.Double(locationX, locationY));
	}
	
	public HashMap<Vertex,Point2D.Double> getVertexLocations()
	{
		return vertexLocations;
	}
	
	public Graph getGraph()
	{
		return G;
	}
	
}
