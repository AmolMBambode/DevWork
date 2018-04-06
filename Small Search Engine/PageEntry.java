import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PageEntry{
	public String pagename;
	public PageIndex pindex;
	public SearchEngine ss;

	PageEntry(String pn,SearchEngine se){
		this.ss = se;
		this.pagename = pn;
		String dir = "./webpages/" + pn;
		pndex = new PageIndex();
		ss.invindex.addPage(pn);

		BufferedReader br1 = null;

		try {
			String actionString1;
			br1 = new BufferedReader(new FileReader(dir));
			ArrayList<String> finalWords = new ArrayList<String>();
			int count = 1;

			while( (actionString1 = br1.readLine()) != null ) {

				actionString1 = actionString1.toLowerCase();

				String[] words = actionString1.split("[ { } [ ] < > = ( ) . , ; ' \" ? # ! - : ]+\\s*");

				myLL<String> ignorefiller = new myLL<String>();
				
				
				ignorefiller.add("a");
				ignorefiller.add("an");
				ignorefiller.add("the");
				ignorefiller.add("they");
				ignorefiller.add("these");
				ignorefiller.add("this");
				ignorefiller.add("for");
				ignorefiller.add("is");
				ignorefiller.add("are");
				ignorefiller.add("was");
				ignorefiller.add("of");
				ignorefiller.add("or");
				ignorefiller.add("and");
				ignorefiller.add("does");
				ignorefiller.add("will");
				ignorefiller.add("whose");

				for(int ui=0;ui<words.length;ui++)
				{

					if(words[ui].equals("stacks"))
						words[ui]="stack";
					if(words[ui].equals("structures"))
							words[ui]="structure";
					if(words[ui].equals("applications"))
						words[ui]="application";
				}

				for(int hh = 0 ; hh < words.length; hh++, count++){
					Position posi = new Position(this,count);
					if(!ignorefiller.contains(words[hh])){
						pindex.addPositionForWord(words[hh],posi);

						WordEntry Dvya = new WordEntry(words[hh]);
						Dvya.position = posi;
						ss.invindex.htpj.add(Dvya);
					}
				}

			}
		} catch (IOException e) {
			System.out.println("The page you asked for does not exist");
		} finally {
			try {
				if (br1 != null)br1.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
	}

	public PageIndex getPageIndex(){
		return pindex;
	}
}