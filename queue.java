public class queue {
	private int counter;
	private Data head;

	// constructor
	public queue() {
		head = null;

	}

	// adds data
	public synchronized void enqueue(int Time, String Name, int Texp,
			String Type, int Qty, char StockName, int Price, boolean Partial) {
		// System.out.println();
		

		if (head == null) {
			head = new Data(Time, Name, Texp, Type, Qty, StockName, Price,
					Partial);
		}

		Data temp = new Data(Time, Name, Texp, Type, Qty, StockName, Price,
				Partial);
		Data current = head;

		if (current != null) {
			// start from head, go to last
			while (current.getNext() != null) {
				current = current.getNext();
			}

			current.setNext(temp);
		}
		incrementCounter();

	}

	// delete data
	public synchronized Data dequeue() {
		if (getCounter() == 0) {
			// System.out.println("stackUnderflow");
			return null;
		} else {
			Data current = head;
			head = current.getNext();
			decrementCounter();
			// System.out.println();
			
			return head;
		}

	}

	public synchronized int getCounter() {
		return counter;
	}

	private synchronized void incrementCounter() {
		counter++;
	}

	private synchronized void decrementCounter() {
		counter--;
	}

	public synchronized Data get(int index)
	// returns the element at the specified position in this list.
	{
		// index must be 1 or higher
		if (index <= 0)
			return null;
		Data current = null;
		if (head != null) {
			current = head.getNext();
			for (int i = 0; i < index; i++) {
				if (current.getNext() == null)
					return null;

				current = current.getNext();
			}
			return current.getData();
		} else
			return null;
	}
}
