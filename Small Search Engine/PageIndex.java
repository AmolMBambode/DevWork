import java.util.*;

public class PageIndex {

	public myLL<WordEntry> wordList = new myLL<WordEntry>();

	public void addPositionForWord(String str, Position p){
		int indeX = 0;
		boolean found = false;

		for (int i=0 ; i<wordList.size() ; i++){
			if(wordList.get(i)== null){
				break;
			}
			if(wordList.get(i).word.equals(str)){
				indeX = i;
				found = true;
				break;
			}
			else continue;
		}

		if(found){
			wordList.get(indeX).addPosition(p);
		}
		else{
			WordEntry newWord = new WordEntry(str);
			newWord.addPosition(p);
			wordList.add(newWord);
		}
	}

	public myLL<WordEntry> getWordEntries(){
		return wordList;
	}

}