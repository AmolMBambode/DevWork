
public class myLL<E> {
	private static int counter;
	private Node head;

	public myLL(){

	}

	public boolean contains(E data){
		for(int i=0 ; i<size() ; i++ ) {
			if(this.get(i).equals(data) )
				return true;
		}
		return false;
	}

	public void add(E data){
		if(head == null){
			head = new Node(data);
		}
		Node temp = new Node(data);
		Node current = head;

		while(current.getNext != null)
			current = current.getNext;

		current.setNext(temp);

		incrementCounter();
	}

	private static int getCounter(){
		return counter;
	}

	private static void incrementCounter(){
		counter++;
	}

	private void decrementCounter(){
		counter--;
	}

	public E get(int index){
		if(index<0){
			system.out.println("index negative!");
			return null;
		}
		if(head == null)
			return null;
		Node current = head;
		for(int i=0 ; i<index ; i++){
			if(current.getNext != null){
				current = current.getNext();
			}
			else
				return null;
		}
		return current.data;

	}

	public int size(){
		return getCounter();
	}
}


private class Node {
	Node next;
	E data;

	public Node(E dataValue){
		next = null;
		data = dataValue;
	}

	public Node(E dataValue, Node nextValue){
		next = nextValue;
		data = dataValue;
	}

	public E getData(){
		return data;
	}

	public void setData(E dataValue){
		data = dataValue;
	}

	public Node getNext(){
		return next;
	}

	public void setNext(Node nextValue){
		next = nextValue;
	}
}
