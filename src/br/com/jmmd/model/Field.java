package br.com.jmmd.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
	
	private boolean mined = false;
	private boolean open = false;
	private boolean checked = false;
	
	private final int line;
	private final int column;
	
	private List<Field> neighbors = new ArrayList<>();
	
	
	public Field( int line, int column) {
	
		this.line = line;
		this.column = column;
	}
	
	
  public boolean AddNeighbor(Field neighbor) {
	   
	   boolean differentLine = line != neighbor.line;
	   boolean differentColumn = column != neighbor.column;
       boolean diagonalLine = differentLine && differentColumn;
  
       //calculating the difference between line and column. 
       int deltaLine = Math.abs(line - neighbor.line);
       int deltaColumn = Math.abs(column - neighbor.column);
       int deltaGeneral = deltaColumn + deltaLine;
       
       //value 1 is an artefatc that represents a neighbor is not on the diagonal line.
       if(deltaGeneral == 1 && !diagonalLine ) {
    	   neighbors.add(neighbor);
    	   return true;
       //value 2 is an artefatc that represents a neighbor is on the diagonal line.
       }else if(deltaGeneral == 2 && diagonalLine ) {
    	   neighbors.add(neighbor);
    	   return true;
       }else {
    	   return false;
       }
   }
	
	

}
