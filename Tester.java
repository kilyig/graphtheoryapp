
public class Tester
{
	public static void main(String[] args)
	{
		/*
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		*/
		
		Graph G = new Graph("B");
		
		Vertex V1 = new Vertex("V1");
		Vertex V2 = new Vertex("V2");
		Vertex V3 = new Vertex("V3");
		Vertex V4 = new Vertex("V4");

		Edge E1 = new Edge("E1",V1,V2);
		Edge E2 = new Edge("E2",V4,V2);
		Edge E3 = new Edge("E3",V2,V3);
		E3.setDirected(true);
		Edge E5 = new Edge("E5",V3,V2);
		E5.setDirected(true);
		Edge E4 = new Edge("E4",V3,V4);
		
		G.add(V1);
		G.add(V2);
		G.add(V3);
		G.add(V4);
		
		G.add(E1);
		G.add(E2);
		G.add(E3);
		G.add(E4);
		G.add(E5);
		
		Walk walk = new Walk(G);
		

		//walk.add(V3);
		walk.add(V2);
		walk.add(V3);
		walk.add(E4);
		walk.add(E2);
		walk.add(E1);

		System.out.println(walk.toString());
		System.out.println("Length: " + walk.length());
		System.out.println("isOpenWalk(): " + walk.isOpenWalk());
		System.out.println("isClosedWalk(): " + walk.isClosedWalk());
		System.out.println("noEdgeOccursMoreThanOnce(): " + walk.noEdgeOccursMoreThanOnce());
		System.out.println("isTrail(): " + walk.isTrail());
		System.out.println("noVertexOccursMoreThanOnce(): " + walk.noVertexOccursMoreThanOnce());
		System.out.println("isCircuit(): " + walk.isCircuit());
		System.out.println("isPath(): " + walk.isPath());
		System.out.println("isCycle(): " + walk.isCycle());
		System.out.println("isCycle(): " + walk.isCycle());
		System.out.println("containsEveryEdge(): " + walk.containsEveryEdge());
		System.out.println("containsEveryVertex(): " + walk.containsEveryVertex());
		//System.out.println("isEulerCircuit(): " + walk.isEulerCircuit()));
		System.out.println(walk.toString());

	}
}
