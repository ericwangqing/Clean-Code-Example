package com.object.mentor.cleancode.chapter2;

import java.util.ArrayList;
import java.util.List;

public class IntentionRevealingNames {
	public static final int FLAGGED = 4;
	public static final int STATUS_VALUE = 0;
	private List<int[]> theList;
	private List<int[]> gameBoard;
	private List<Cell> gameBoardInCells;
	
	public List<int[]> getThem(){
		List<int[]> list1 = new ArrayList<int[]>();
		for(int[] x: theList)
			if(x[0] == 4) list1.add(x);
		return list1;
	}
	
	public List<int[]> getFlaggedCells(){
		List<int[]> flaggedCells = new ArrayList<int[]>();
		for(int[] cell: gameBoard)
			if(cell[STATUS_VALUE] == FLAGGED) flaggedCells.add(cell);
		return flaggedCells;
	}
	
	public List<Cell> getFlaggedCellsInCells(){
		List<Cell> flaggedCells = new ArrayList<Cell>();
		for(Cell cell: gameBoardInCells)
			if(cell.isFlagged()) flaggedCells.add(cell);
		return flaggedCells;
	}
}
