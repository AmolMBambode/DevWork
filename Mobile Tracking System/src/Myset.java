import java.util.*;

public class Myset {

	LinkedList ll = new LinkedList();

	public boolean IsEmpty() {
		if (ll.size() == 0)
			return true;
		else
			return false;
	}

	public boolean IsMember(Object n) {
		if (ll.contains(n))
			return true;
		else
			return false;
	}

	public Object Get(int i) {
		return ll.get(i);
	}

	public void Insert(Object n) {
		ll.addLast(n);
	}

	public void Delete(Object n) {

		try {
			ll.remove(n);
		} catch (NoSuchElementException e) {
			System.out.println("no such element");
		}
	}

	public Myset Union(Myset a) {
		if (ll.size() == 0) {
			return a;
		} else {
			Myset unionSet = new Myset();
			unionSet.ll = ll;
			unionSet.ll.addAll(a.ll);
			return unionSet;
		}
	}

	public Myset Intersection(Myset a) {
		if (ll.size() == 0) {
			return null;
		} else {
			Myset interSet = new Myset();
			for (int i = 0; i < ll.size(); i++) {
				if (a.ll.contains(ll.get(i))) {
					interSet.ll.add(ll.get(i));
				}
			}
			return interSet;
		}
	}

}
