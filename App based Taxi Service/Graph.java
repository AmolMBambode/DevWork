import java.util.*;
public class Graph {
	
	LinkedList allPlaces = new LinkedList();
	LinkedList allRoads = new LinkedList();
	
	int numPlaces(){
		return allPlaces.size();
	}
	
	int numRoads(){
		return allRoads.size();
	}
	
	LinkedList roadsFrom (Place p){
		if(allPlaces.contains(p)){
			return p.connectedTo;
		}
		else return null;
	}
	
	Road getRoad(Place p , Place q){
		if(p.connectedTo != null){
			
			for(int i=0 ; i< p.connectedTo.size() ; i++){
				connectedToNode current = ((connectedToNode)( p.connectedTo.get(i) ));
				if( current.v == q  )
					return current.r;
			}
			return null;
		}
		return null;
	}
	
	Place[] placesConnectedThrough (Road r){
		if(allRoads.contains(r)){
			return r.where2where;
		}
		else return null;
	}
	
	Place placeOppositeTo (Place p , Road road){
		if(allPlaces.contains(p)){
			for(int i=0 ; i<p.connectedTo.size() ; i++){
				connectedToNode current = ((connectedToNode)p.connectedTo.get(i));
				if(  current.r == road){
					return current.v;
				}
			}
			System.out.println("null 1");
			return null;
		}
		System.out.println("null 2");
		return null;
	}
	
	void addPlace(Place p){
		if(!allPlaces.contains(p)){
			System.out.println("added " + p.location);
		allPlaces.add(p);
		}
		else System.out.println("Place already exist");
	}
	
	void addPlace(String str){
		Place p = new Place(str);
		if(!allPlaces.contains(p)){
			System.out.println("added " + p.location);
			allPlaces.add(p);
		}
		else System.out.println("Place already exist");
		
	}
	
	/*void addRoad (Road road) throws roadAlreadyExistException{
		
		Place p = road.where2where[0];
		Place q = road.where2where[1];
		
		boolean existanceOfP = false;
		boolean existanceOfQ = false;
		boolean safe1 = true;
		boolean safe2 = true;
		Place nodeForP=null , nodeForQ=null;
		
		
		if(allPlaces.contains(p)){
			existanceOfP = true;
		}
		if(allPlaces.contains(q)){
			existanceOfQ = true;
		}
		
		
		System.out.println("addind road from: " + p.location + " to " + q.location);
		
		for(int i=0 ; i<allPlaces.size() ; i++){
			Place current = ((Place)allPlaces.get(i));
			if(existanceOfP){
			if(current == p){
				for( int j=0 ; j<current.connectedTo.size() ; j++ ){
					connectedToNode c2n =  (connectedToNode)current.connectedTo.get(j);
					if(c2n.v == q){
						safe1 = false;
						throw new roadAlreadyExistException("abc");
					}
				}
				if(safe1)
				nodeForP = current;
			}
			}
			if(existanceOfQ){
			if(current == q){
				for( int j=0 ; j<current.connectedTo.size() ; j++ ){
					connectedToNode c2n =  (connectedToNode)current.connectedTo.get(j);
					if(c2n.v == p){
						safe2 = false;
						throw new roadAlreadyExistException("abc");
					}
				}
				if(safe2){
					nodeForQ = current;
				}
			}
		}
		}
		
		if(safe1 && safe2){
			allPlaces.add(p);
			allPlaces.add(q);
			allRoads.add(road);
			if(nodeForP == null)
				nodeForP = p;
			if(nodeForQ == null)
				nodeForQ = q;
				
			connectedToNode newc2nQ = new connectedToNode(nodeForP, road);
			connectedToNode newc2nP = new connectedToNode(nodeForQ, road);
			
			nodeForP.connectedTo.add(newc2nP);
			nodeForQ.connectedTo.add(newc2nQ);
		}
			
	}*/
	
	void addRoad (int t , String src , String dest) throws roadAlreadyExistException{
		//System.out.println();
		//System.out.println("list");
		//for(int i= 0  ; i< allPlaces.size() ; i++){
		//	System.out.println( ( (Place) allPlaces.get(i) ).location );
		//}
		
		Place p,q;

		boolean existanceOfP = false;
		boolean existanceOfQ = false;
		boolean safe1 = true;
		boolean safe2 = true;
		Place nodeForP=null , nodeForQ=null;
		p = hasPlace(src);
		q = hasPlace(dest);
		
		if(p != null){
			existanceOfP = true;
		}
		else p = new Place(src);
		if(q != null){
			existanceOfQ = true;
		} else q = new Place(dest);
		
		//System.out.println("adding road from " + p.location + " to " + q.location);
		
		Road road = new Road(p, q, t);
		
		
		
		//System.out.println("Existance P = " + existanceOfP + " Q = " + existanceOfQ);
		
		for(int i=0 ; i<allPlaces.size() ; i++){
			Place current = ((Place)allPlaces.get(i));
			if(existanceOfP){
			if(current.location.equals(p.location)){
				for( int j=0 ; j<current.connectedTo.size() ; j++ ){
					connectedToNode c2n =  (connectedToNode)current.connectedTo.get(j);
					if(c2n.v.location.equals(q.location)){
						safe1 = false;
						throw new roadAlreadyExistException("abc");
					}
				}
				if(safe1)
				nodeForP = current;
			}
			}
			if(existanceOfQ){
			if(current.location.equals(q.location)){
				for( int j=0 ; j<current.connectedTo.size() ; j++ ){
					connectedToNode c2n =  (connectedToNode)current.connectedTo.get(j);
					if(c2n.v.location.equals(p.location)){
						safe2 = false;
						throw new roadAlreadyExistException("abc");
					}
				}
				if(safe2){
					nodeForQ = current;
				}
			}
		}
		}
		if(safe1 && safe2){
			if(!existanceOfP){
				//System.out.println("adding P");
			allPlaces.add(p);
			}
			if(!existanceOfQ){
				//System.out.println("Adding Q");
			allPlaces.add(q);
			}
			allRoads.add(road);
			if(nodeForP == null)
				nodeForP = p;
			if(nodeForQ == null)
				nodeForQ = q;
				
			connectedToNode newc2nQ = new connectedToNode(nodeForP, road);
			connectedToNode newc2nP = new connectedToNode(nodeForQ, road);
			
			nodeForP.connectedTo.add(newc2nP);
			nodeForQ.connectedTo.add(newc2nQ);
		}	
		
		
	}
	
	Place hasPlace(String str){
		for(int i=0 ; i<allPlaces.size() ; i++){
			if( ( (Place)allPlaces.get(i) ).location.equals(str) ){
				return (Place)allPlaces.get(i);
			}
		}
		return null;
	}
	
	void addRoad(Road road) throws roadAlreadyExistException{
		
		Place p = road.where2where[0];
		Place q = road.where2where[1];
		
		System.out.println("adding road from " + p.location + " to " + q.location);
		
		boolean existanceOfP = false;
		boolean existanceOfQ = false;
		boolean safe1 = true;
		boolean safe2 = true;
		Place nodeForP=null , nodeForQ=null;
		
		if(allPlaces.contains(p)){
			existanceOfP = true;
		}
		if(allPlaces.contains(q)){
			existanceOfQ = true;
		}
		
		System.out.println("Existance P = " + existanceOfP + " Q = " + existanceOfQ);
		
		for(int i=0 ; i<allPlaces.size() ; i++){
			Place current = ((Place)allPlaces.get(i));
			if(existanceOfP){
			if(current.location.equals(p.location)){
				for( int j=0 ; j<current.connectedTo.size() ; j++ ){
					connectedToNode c2n =  (connectedToNode)current.connectedTo.get(j);
					if(c2n.v.location.equals(q.location)){
						safe1 = false;
						throw new roadAlreadyExistException("abc");
					}
				}
				if(safe1)
				nodeForP = current;
			}
			}
			if(existanceOfQ){
			if(current.location.equals(q.location)){
				for( int j=0 ; j<current.connectedTo.size() ; j++ ){
					connectedToNode c2n =  (connectedToNode)current.connectedTo.get(j);
					if(c2n.v.location.equals(p.location)){
						safe2 = false;
						throw new roadAlreadyExistException("abc");
					}
				}
				if(safe2){
					nodeForQ = current;
				}
			}
		}
		}
		if(safe1 && safe2){
			if(!existanceOfP){
				System.out.println("adding P");
			allPlaces.add(p);
			}
			if(!existanceOfQ){
				System.out.println("Adding Q");
			allPlaces.add(q);
			}
			allRoads.add(road);
			if(nodeForP == null)
				nodeForP = p;
			if(nodeForQ == null)
				nodeForQ = q;
				
			connectedToNode newc2nQ = new connectedToNode(nodeForP, road);
			connectedToNode newc2nP = new connectedToNode(nodeForQ, road);
			
			nodeForP.connectedTo.add(newc2nP);
			nodeForQ.connectedTo.add(newc2nQ);
		}	
		
		
	}
	
	void resetPath(Place source){
		for(int i=0 ; i<allPlaces.size(); i++){
			((Place)allPlaces.get(i)).previous = null;
			((Place)allPlaces.get(i)).src = source.location;
			if( ( (Place)allPlaces.get(i)).location.equals(source.location)){
				( (Place)allPlaces.get(i)).minDistance = 0;
			}
			else ( (Place)allPlaces.get(i)).minDistance = (int)(Integer.MAX_VALUE);
		}
	}
	
	void computePaths(Place source) {
		resetPath(source);
		
        LinkedList vertexQueue = new LinkedList(); //node = place
       // System.out.println("in here 1");
        vertexQueue.add(source);
        pr(vertexQueue);

        //Iterator it1 = vertexQueue.iterator();
		//while(it1.hasNext()){
		//System.out.println("in while3 ");
		//	System.out.print(" " + ((Place)it1.next()).location + " ");
			//it1 = (Iterator) it1.next();
	//	}
        
        
        while (!vertexQueue.isEmpty()) {
        //	System.out.println("in while1");
        	Place u = (Place)(vertexQueue.poll());
        	vertexQueue = pr(vertexQueue);
        	//System.out.println("plled " + u.location);
            	// Visit each edge exiting u
        		for (int i=0 ; i<u.connectedTo.size() ; i++)
        		{
        			connectedToNode c2n = (connectedToNode)(u.connectedTo.get(i));
        			Place target = c2n.v;
        			int weight = c2n.r.timeTaken;
        			int distanceThroughU = u.minDistance + weight;
        			if (distanceThroughU < target.minDistance) {
        				vertexQueue.remove(target);
        				vertexQueue = pr(vertexQueue);
        				//System.out.println("removed " + target.location);
        				target.minDistance = distanceThroughU ;
        				target.previous = u;
        				//Iterator it = vertexQueue.iterator();
        				//while(it.hasNext()){
        				//System.out.println("in while2 ");
        				//	System.out.print(" " + ((Place)it.next()).location + " ");
        					//it = (Iterator) it.next();
        				//}
        				//System.out.println("adding target " + target.location + target.minDistance + target.previous.location);
        				vertexQueue.add(target);
        				vertexQueue = pr(vertexQueue);
        			}
        		}
        }
    }
	
	LinkedList pr(LinkedList ll){
		
		for(int i = 0 ; i < ll.size() ; i++){
			Place p = (Place)ll.get(i);
			Place mini = p;
			for(int j=i+1 ; j< ll.size() ; j++){
				Place q = (Place)ll.get(j);
				if(p.minDistance > q.minDistance){
					mini = q;
				}
			}
			ll.remove(mini);
			ll.add(i,mini);
		}
		return ll;
	}
	
	public List getShortestPathTo(Place target)
    {
		//if(target.previous.previous != null && target.previous.previous.previous != null)
		//System.out.println("im here----------" + target.location + " " +target.previous.location + " " + target.previous.previous.location + " " + target.previous.previous.previous.location);
        List path = new ArrayList();
        for (Place vertex = target; vertex != null; vertex = vertex.previous){
        	//System.out.println(path.size());
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
	
	void printGraph(){
		System.out.println("All Roads");
		for(int i=0 ; i<allRoads.size() ; i++){
			Road current = (Road)allRoads.get(i);
			System.out.println("connects: " + current.where2where[0].location + " and " + current.where2where[1].location + "\t\t\tTime: " + current.timeTaken);
		}
		System.out.println();
		System.out.println("All Places");
		for(int i=0 ; i<allPlaces.size() ; i++){
			Place current = (Place)allPlaces.get(i);
			System.out.println(current.location + " connected to:");
			for(int j=0 ; j<current.connectedTo.size() ; j++){
				connectedToNode c2n = (connectedToNode)current.connectedTo.get(j);
				System.out.println("\t"+c2n.v.location + "\t\t\tTime: " + c2n.r.timeTaken);
			}
		}
	}
	
}

class Place {
	String src;
	int minDistance;
	Place previous;
	
	String location;
	LinkedList connectedTo = new LinkedList();
	
	public int compareTo(Place other)
    {
        return Integer.compare(minDistance, other.minDistance);
    }
	
	public Place(String str){
		location = str;
	}
}
class Road {
	Place where2where[] = new Place[2];
	int timeTaken;
	
	public Road(Place src , Place dest , int t){
		where2where[0] = src;
		where2where[1] = dest;
		timeTaken = t;
	}
}
class connectedToNode {
	Place v;
	Road r;
	
	public connectedToNode(Place p , Road road) {
		v = p;
		r = road;
	}
}

class roadAlreadyExistException extends Exception{
	roadAlreadyExistException(String s){
		super(s);
	}
}
