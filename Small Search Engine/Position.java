import java.util.*;
public class Position {
	
	public PageEntry pe;
	public int wi;
	
	Position()
	{
		this.pe=null;
		this.wi=0;
	}
	
	Position(PageEntry p, int wordIndex)
	{
		this.pe=p;
		this.wi=wordIndex;
	}
	
	public PageEntry getPageEntry()
	{
		return pe;
	}
	
	public int getWordIndex()
	{
		return wi;
	}
}
