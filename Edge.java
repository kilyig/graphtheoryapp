import java.awt.Color;

public class Edge
{
	private Vertex endpoint1, endpoint2;
	private String label;
	private double weight;
	private boolean isDirected;
	
	public Edge(String label, Vertex endpoint1, Vertex endpoint2)
	{
		weight = 1;
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
		endpoint1.connectEdge(this);
		endpoint2.connectEdge(this);
		this.label = label;
		isDirected = false;
	}
	
	/*
	//For cloning purposes
	public Edge(Edge that)
	{
		this.setEndpoint1(new Vertex(that.endpoint1));
		this.setEndpoint2(new Vertex(that.endpoint2));
		this.label = that.label;
		this.isDirected = that.isDirected;
		this.weight = that.weight;
	}
	*/
	
	public Edge(Vertex endpoint1, Vertex endpoint2)
	{
		weight = 1;
		label = "";
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
		endpoint1.connectEdge(this);
		endpoint2.connectEdge(this);
		isDirected = false;
	}
	
	public Vertex getEndpoint1()
	{
		return endpoint1;
	}
	
	public Vertex getEndpoint2()
	{
		return endpoint2;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void setEndpoint1(Vertex endpoint1)
	{
		this.endpoint1 = endpoint1;
		if(!this.endpoint1.isConnectedTo(this))
			this.endpoint1.connectEdge(this);
	}
	
	public void setEndpoint2(Vertex endpoint2)
	{
		this.endpoint2 = endpoint2;
		if(!this.endpoint2.isConnectedTo(this))
			this.endpoint2.connectEdge(this);
	}
	
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
	
	public String endpointRepresentationString()
	{
		String output = "";
		if(isDirected())
			output += "(";
		else
			output += "{";
		output += endpoint1.getLabel() + ", " + endpoint2.getLabel();
		if(isDirected())
			output += ")";
		else
			output += "}";
		return output;
	}

	public boolean isIncidentTo(Vertex v)
	{
		if(endpoint1.equals(v) || endpoint2.equals(v))
			return true;
		return false;
	}
	
	public void setDirected(boolean isDirected)
	{
		this.isDirected = isDirected;
	}
	
	public boolean isDirected()
	{
		return isDirected;
	}
	
	@Override
	public boolean equals(Object that)
	{
		  if(this == that)
			  return true;
		  if(!(that instanceof Edge))
			  return false;

		  Edge thatEdge = (Edge) that;
		  
		  if(this.getLabel() != thatEdge.getLabel())
			  return false;
		  return true;
		  /*
		  if(this.isDirected() != thatEdge.isDirected())
			  return false;
		  if(this.isDirected() == false)
		  {
			  if(this.getEndpoint1() == thatEdge.getEndpoint1() && this.getEndpoint2() == thatEdge.getEndpoint2())
				  return true;
			  else if(this.getEndpoint1() == thatEdge.getEndpoint2() && this.getEndpoint2() == thatEdge.getEndpoint1())
				  return true;
			  else
				  return false;
		  }
		  else if(this.getEndpoint1() == thatEdge.getEndpoint1() && this.getEndpoint2() == thatEdge.getEndpoint2())
			  return true;
		  else
			  return false;
			  */
	}
	
	public boolean equals(Edge thatEdge)
	{
		  if(this.isDirected() != thatEdge.isDirected())
			  return false;
		  if(this.isDirected() == false)
		  {
			  if(this.getEndpoint1() == thatEdge.getEndpoint1() && this.getEndpoint2() == thatEdge.getEndpoint2())
				  return true;
			  else if(this.getEndpoint1() == thatEdge.getEndpoint2() && this.getEndpoint2() == thatEdge.getEndpoint1())
				  return true;
			  else
				  return false;
		  }
		  else if(this.getEndpoint1() == thatEdge.getEndpoint1() && this.getEndpoint2() == thatEdge.getEndpoint2())
			  return true;
		  else
			  return false;
	}
	
	public boolean allowsWalk(Vertex v1, Vertex v2)
	{
		  if(this.isDirected() == false)
		  {
			  if(this.getEndpoint1().equals(v1) && this.getEndpoint2().equals(v2))
				  return true;
			  if(this.getEndpoint1().equals(v2) && this.getEndpoint2().equals(v1))
				  return true;
			  return false;
		  }else
		  {
			  if(this.getEndpoint1().equals(v1) && this.getEndpoint2().equals(v2))
				  return true;
			  return false;
		  }
	}
}
