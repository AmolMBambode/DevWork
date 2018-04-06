import java.util.*;

public class WordEntry {

	public String word;
	public Position position = new Position();
	public myLL<Position> positionList = new myLL<Position>();

	WordEntry(String word){
		this.word = word;
	}

	public void addPosition (Position pos) {
		this.position = pos;
		this.positionList.add((Position)pos);
	}

	public void addPositions (myLL<Position> positions){
		this.positionList = pos;
	}

	myLL<Position> getAllPositionsForThisWord(){
		return positionList;
	}
}