import java.util.*;

public class MySet<E> {
	myLL<E> ll = new myLL<E> ();

	public void addElement (E element){
		ll.add(element);
	}

	public boolean isElement(E element){
		return this.ll.contains(element);
	}

	public E get (int i){
		try{
		return this.ll.get(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int size(){
		return this.ll.size();
	}

	public MySet<E> intersection (MySet<E> a) {
		Myset<E> temp = new MySet<E>();
		for(int i = 0 ; i < a.size() ; i++){

			if(this.isElement(a.get(i)))
			{
				tempset2.list.add(a.get(i));
			}
		}
		return temp;
	}
	public Myset<E> union (Myset<E> otherset)
	{
		Myset<E> tempset1 = new Myset<E>();
		tempset1.list=this.list;
		for(int i=0; i<otherset.size(); i++)
		{
			if(this.isElement(otherset.get(i)))
			{}
			else{
				tempset1.list.add(otherset.get(i));
			}	
	}
		return tempset1;
	}


}
