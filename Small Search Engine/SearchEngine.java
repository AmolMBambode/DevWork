import java.util.*;

public class SearchEngine{

	public InvertedPageIndex invindex=new InvertedPageIndex();
	public SearchEngine() {
		
		
	}

	public void addPage(String nameofpage)
	{
		if(!invindex.containsPage(nameofpage))
		{
			System.out.println("Adding page: "+nameofpage);
		invindex.addPage(nameofpage);
		PageEntry pt=new PageEntry(nameofpage,this);
		
		}
		else{
			System.out.println("The page "+nameofpage+" has already been added");
		}
	}
	
	public void FindPagesWhichContainWord(String searchfor)
	{
		LinkedList foundset= new  LinkedList();
		foundset=invindex.getPagesWhichContainWord(searchfor);
		if(foundset.size()==0)
		{
			System.out.println("This word does not exist in database!");
		}
		else{
		System.out.print("The word "+searchfor+" is found in the pages: ");
		
		for(int i=0;i<foundset.size();i++)
		{
			System.out.print(foundset.get(i)+ ", ");
		}
		System.out.println("");
		}
	}
	
	public void FindPositionsOfWordInAPage(String wordn, String pagen)
	{
		if(!invindex.containsPage(pagen))
		{
			System.out.println("This page has not been indexed");
		}
		else
		{
			int gg;
			PageEntry ppap = new PageEntry(pagen,this);
			for(gg=0;gg<ppap.pindex.wordentrylist.size();gg++)
			{
			if(((WordEntry)(ppap.pindex.getWordEntries().get(gg))).word.equals(wordn))
				break;
			}
			WordEntry wee = (WordEntry)ppap.pindex.getWordEntries().get(gg);
			if(wee.positionlist.size()>0)
			{
			System.out.print("The locations of the word - "+wee.word+ " in the page - "+pagen+" is: ");
			for(int ff=0;ff<wee.positionlist.size();ff++)
			{
				System.out.print(((Position)(wee.positionlist.get(ff))).wi + " ");
			}
			System.out.println("");
			}
			else{
				System.out.println("Webpage "+pagen+" does not contain word "+wordn);
			}
		}
	}
	
	
	public void performAction(String actionMessage) {
		
//		PageEntry pp= new PageEntry(actionMessage,this);
//		invindex.getPagesWhichContainWord("stack_datastructure_wiki");
		
//		System.out.println("ok finals");
//		this.FindPagesWhichContainWord("stack_datastructure_wiki");
//		this.FindPositionsOfWordInAPage("stack", "stack_datastructure_wiki");
		
		String[] function = actionMessage.split("\\s+");
		
		if(function[0].equals("addPage") && function.length==2)
		{
			this.addPage(function[1]);
		}
		else if(function[0].equals("queryFindPagesWhichContainWord") && function.length==2)
		{
			this.FindPagesWhichContainWord(function[1].toLowerCase());
			
		}
		else if(function[0].equals("queryFindPositionsOfWordInAPage") && function.length==3)
		{
			this.FindPositionsOfWordInAPage(function[1].toLowerCase(), function[2]);
			
		}
		else{
			System.out.println("The request entered is invalid!");
		}
		
		
	}
}

