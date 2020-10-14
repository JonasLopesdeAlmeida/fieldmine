package br.com.jmmd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {
	
	private int numberoflines;
	private int numberofcolumns;
	private int numberofmines;
	
	private final List<Field> fields = new ArrayList<>();

	public Board(int numberoflines, int numberofcolumns, int numberofmines) {
		this.numberoflines = numberoflines;
		this.numberofcolumns = numberofcolumns;
		this.numberofmines = numberofmines;
	
	   generateFields();
	   associateNeighborhood();
	   sortMines();
	}

	public void open(int line, int column) {
		fields.parallelStream()
		.filter(f -> f.getLine() == line && f.getColumn() == column)
	    .findFirst()
	    .ifPresent(f -> f.open());
	}
	
	public void changeMarkup(int line, int column) {
		fields.parallelStream()
		.filter(f -> f.getLine() == line && f.getColumn() == column)
	    .findFirst()
	    .ifPresent(f -> f.changeMarkup());
	}
	

	private void generateFields() {
		for (int line = 0; line < numberoflines; line++) {
			for (int column = 0;column < numberofcolumns; column++) {
				
				fields.add(new Field(line, column));
			}
			
		}
		
	}
	
	private void associateNeighborhood() {
		for(Field f1: fields) {
			for(Field f2: fields) {
				f1.AddNeighbor(f2);
			}
		}
		
	}

	private void sortMines() {
		long spreadMines = 0;
		Predicate<Field> mined = f -> f.isMined();
		
		do {
			//just to know the number of spread mines.
			spreadMines = fields.stream().filter(mined).count();
		    int random = (int) (Math.random() * fields.size());
		    fields.get(random).mined();
		} while (spreadMines < numberofmines);
		
	}

	public boolean achievedGoal() {

		return fields.stream().allMatch(f -> f.achievedGoal());
	}
	
	public void restart() {
		fields.stream().forEach(f -> f.restartGame());
	    sortMines();
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (int l = 0; l < numberoflines; l++) {
			
			for (int c = 0; c < numberofcolumns; c++) {
				sb.append("");
				sb.append(fields.get(i));
				sb.append("");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	

	
}
