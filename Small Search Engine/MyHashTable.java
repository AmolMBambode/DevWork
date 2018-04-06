import java.util.*;

class LinkedHashTable{

	public WordEntry wentry = new WordEntry(null);
	public LinkedHashTable next;

	LinkedHashTable(WordEntry w){
		this.wentry = w;
		this.next = null;
	}
}

public class MyHashTable{

	static int tablesize = 997;
	LinkedHashTable[] table;

	MyHashTable(){
		table = new LinkedHashTable[tablesize];

		for ( int i=0 ; i<tablesize ; i++ ){
			table[i] = null;
 		}
	}

	 public void add(WordEntry w)
	 {
		int hash =Math.abs(w.word.hashCode()% tablesize);
		if(table[hash]==null)
		{
			table[hash] =new  LinkedHashTable(w);
			
			LinkedHashTable entry = table[hash];
			entry.next = new LinkedHashTable(w);
			entry.next.wentry.addPosition(w.position);
		}

		else{
			LinkedHashTable entry = table[hash];
			while(entry.next!= null && !entry.wentry.word.equals(w.word))
			{
				entry=entry.next;
			}
			if(entry.wentry.word.equals(w.word))
			{
			
				entry.wentry.addPosition(w.position);
			}
			else{
				entry.next = new LinkedHashTable(w);
				entry=entry.next;
			
				entry.wentry.addPosition(w.position);
				
			}
				
		}
		
	 }
	 
	 public WordEntry getword_str(String wrd)
	 {
		 int hash = Math.abs(wrd.hashCode()% tablesize);
		 if(table[hash]==null)
			 return null;
		 else{
//			 if(table[hash].wentry.word.equals(wrd))
//				 return table[hash].wentry;
			 
			 LinkedHashTable entry = table[hash];
			 while(entry.next!=null && !entry.wentry.word.equals(wrd))
			 {
				 entry = entry.next;
			 }
			 if(entry == null)
				 return null;
			 else
				 return entry.wentry;
		 }
	 }
	
}
