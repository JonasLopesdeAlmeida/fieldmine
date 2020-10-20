package br.com.jmmd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.jmmd.exception.ExplosionException;

public class Field {

	private boolean mined = false;
	private boolean open = false;
	private boolean marked = false;

	private final int line;
	private final int column;

	private List<Field> neighbors = new ArrayList<>();

	public Field(int line, int column) {

		this.line = line;
		this.column = column;
	}

	boolean AddNeighbor(Field neighbor) {

		boolean differentLine = line != neighbor.line;
		boolean differentColumn = column != neighbor.column;
		boolean diagonalLine = differentLine && differentColumn;

		// calculating the difference between line and column.
		int deltaLine = Math.abs(line - neighbor.line);
		int deltaColumn = Math.abs(column - neighbor.column);
		int deltaGeneral = deltaColumn + deltaLine;

		// value 1 is an artefatc that represents a neighbor is not on the diagonal
		// line.
		if (deltaGeneral == 1 && !diagonalLine) {
			neighbors.add(neighbor);
			return true;
			// value 2 is an artefatc that represents a neighbor is on the diagonal line.
		} else if (deltaGeneral == 2 && diagonalLine) {
			neighbors.add(neighbor);
			return true;
		} else {
			return false;
		}
	}

	// this method checks if the single square of the field is open or marked(flag
	// icon).
	void changeMarkup() {

		if (!open) {
			marked = !marked;

		}

	}

	boolean open() {

		if (!open && !marked) {
			open = true;

			if (mined) {

				throw new ExplosionException("Game Over!!");

			}
			if (safeNeighborhood()) {
				// condition that verifies that all neighborhoods are safe and each one is able
				// to open.
				// When there is an action to open a neighbor the next gonna be open
				// subsequently
				neighbors.forEach(v -> v.open());
			}
			return true;
		} else {
			return false;
		}
	}

	boolean safeNeighborhood() {

		// this method aims to protect the entire neighborhood.
		return neighbors.stream().noneMatch(n -> n.mined);
	}

	void mined() {

		mined = true;
	}
	
	public boolean isMined() {
		return mined;
	}

	public boolean isMarked() {
		return marked;
	}

	public boolean isOpen() {
		return open;
	}

	public boolean isClosed() {
		return !isOpen();
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	// this method says whether that goal has been achieved.
	boolean achievedGoal() {

		boolean revealed = !mined && open;
		boolean protectedField = mined && marked;

		// if have got both result then i have got a achieved goal.
		return revealed || protectedField;
	}

	// method that identifies how many mines i have in the neighborhood.
	long neighborhoodHaveMines() {
		// return the number of neighbors that are mined
		return neighbors.stream().filter(n -> n.mined).count();
	}

	void restartGame() {
		open = false;
		mined = false;
		marked = false;
	}

	@Override
	public String toString() {

		if (marked) {
			return "X";
		} else if (open && mined) {
			return "*";
		} else if (open && neighborhoodHaveMines() > 0) {
			return Long.toString(neighborhoodHaveMines());
		} else if (open) {
			return " ";
		} else {
			return "?";
		}
	}

}
