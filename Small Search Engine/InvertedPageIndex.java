import java.util.LinkedList;

public class InvertedPageIndex {

	public MyHashTable hashtableofwords = new MyHashTable();
	public LinkedList pagesavailable  = new LinkedList();
	
	void addPage(String p)
	{
		this.pagesavailable.add(p);
	}
	boolean containsPage(String p)
	{
		return this.pagesavailable.contains(p);
	}
	LinkedList getPagesWhichContainWord(String stri)
	{
		LinkedList lala = new LinkedList();
		LinkedList pagesfound = new LinkedList();
		WordEntry foundword = new WordEntry(null);
		
		foundword=this.hashtableofwords.getword_str(stri);
		if(foundword==null)
		{
			pagesfound=null;
		}
		else{
			
			for(int i=0;i<foundword.positionlist.size();i++)
			{
				
				if(!lala.contains(((Position)(foundword.positionlist.get(i))).pe.pagename))
				{
				lala.add(((Position)(foundword.positionlist.get(i))).pe.pagename);
				}
				
			}
		}
		return lala;
	}
	
}
