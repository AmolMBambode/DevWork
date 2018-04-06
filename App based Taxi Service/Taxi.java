import java.util.*;

public class Taxi{
	LinkedList allTaxies = new LinkedList();
	
	void addTaxi(String name , Place p ) throws taxiAlreadyExistException{
		for(int i=0 ; i<allTaxies.size() ; i++){
			if(  ( (TaxiNode) allTaxies.get(i) ).name == name  )
				throw new taxiAlreadyExistException("skdfjn");
		}
		TaxiNode tn = new TaxiNode(name , p);
		allTaxies.add(tn);
	}
	
}

class TaxiNode /*extends Thread*/{
	
	String name;
	Place currentPlace;
	boolean availability = true;
	
	LinkedList time = new LinkedList();
	
	//long timeRemaining = 0,startTime,total;
	//long currentTime;
	//boolean passengerOnBoard;
	
	public TaxiNode(String name , Place p){
		this.name = name;
		currentPlace = p;
		//start();
	}
	boolean checkAvailability(int t){
		busyTime bt;
		for(int i=0 ; i< time.size() ; i++){
			bt = (busyTime)time.get(i);
			if(bt.start < t && bt.end > t)
				return false;
		}
		return true;
	}
	/*public void run(){
		currentTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
class busyTime{
	int start;
	int end;
	busyTime(int s, int e){
		start = s;
		end = e;
	}
	
}
class taxiAlreadyExistException extends Exception{
	taxiAlreadyExistException(String s){
		super(s);
	}
}

/*
 * class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

public class Dijkstra
{
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
        Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
            vertexQueue.remove(v);

            v.minDistance = distanceThroughU ;
            v.previous = u;
            vertexQueue.add(v);
        }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {
        // mark all the vertices 
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex D = new Vertex("D");
        Vertex F = new Vertex("F");
        Vertex K = new Vertex("K");
        Vertex J = new Vertex("J");
        Vertex M = new Vertex("M");
        Vertex O = new Vertex("O");
        Vertex P = new Vertex("P");
        Vertex R = new Vertex("R");
        Vertex Z = new Vertex("Z");

        // set the edges and weight
        A.adjacencies = new Edge[]{ new Edge(M, 8) };
        B.adjacencies = new Edge[]{ new Edge(D, 11) };
        D.adjacencies = new Edge[]{ new Edge(B, 11) };
        F.adjacencies = new Edge[]{ new Edge(K, 23) };
        K.adjacencies = new Edge[]{ new Edge(O, 40) };
        J.adjacencies = new Edge[]{ new Edge(K, 25) };
        M.adjacencies = new Edge[]{ new Edge(R, 8) };
        O.adjacencies = new Edge[]{ new Edge(K, 40) };
        P.adjacencies = new Edge[]{ new Edge(Z, 18) };
        R.adjacencies = new Edge[]{ new Edge(P, 15) };
        Z.adjacencies = new Edge[]{ new Edge(P, 18) };


        computePaths(A); // run Dijkstra
        System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<Vertex> path = getShortestPathTo(Z);
        System.out.println("Path: " + path);
    }
}
 */
/*LinkedList storage = new LinkedList();
Graph g = new Graph();
Place c = new Place("chanakyapuri");
Place a = new Place("AIIMS");
Place l = new Place("lajpat nagar");
Place i = new Place("IIT main gate");
Place ig = new Place("India Gate");
g.addPlace(c);
Road r1 = new Road(ig, c, 3);
Road r2 = new Road(ig, a, 30);
Road r3 = new Road(ig, l, 5);
Road r4 = new Road(c, a, 20);
Road r5 = new Road(a, l, 15);
Road r6 = new Road(c, i, 5);
Road r7 = new Road(a, i, 30);
Road r8 = new Road(l, i, 40);
Road r9 = new Road(c, ig, 0);
try{
g.addRoad(r8);
g.addRoad(r7);
g.addRoad(r6);
g.addRoad(r5);
g.addRoad(r4);
g.addRoad(r3);
g.addRoad(r2);
g.addRoad(r1);
g.addRoad(r9);
} catch (roadAlreadyExistException s){
	System.out.println("Road already exist");
}

g.printGraph();
System.out.println(g.getRoad(i, c).timeTaken);
System.out.println(g.numPlaces());
System.out.println(g.numRoads());
storage = g.roadsFrom(ig);
for(int k=0 ; k<storage.size() ; k++)
	System.out.println("List = " + ( (connectedToNode)storage.get(k)).r.where2where[1].location );
System.out.println(g.placeOppositeTo(ig, r1).location);
System.out.println("reached 1");
Place[] al = g.placesConnectedThrough(r8);
System.out.println(al[0].location + " " + al[1].location );
System.out.println((g.placeOppositeTo(ig, r1)).location);
System.out.println("reached 2");

g.computePaths(i); // run Dijkstra
System.out.println("Distance to " + ig.location + ": " + ig.minDistance);
List path = g.getShortestPathTo(l);
System.out.println("Path: ");
for(int v = 0 ; v < path.size() ; v++){
	 System.out.println( (( Place)path.get(v)).location);
}

*/
