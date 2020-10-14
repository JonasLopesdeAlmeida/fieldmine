package br.com.jmmd.mf;

import br.com.jmmd.model.Board;

public class Application {

	
	public static void main(String[] args) {
		
		Board board = new Board(6, 6, 6);
		
		board.open(3, 3);
		board.changeMarkup(4, 4);
		board.changeMarkup(4, 6);
		
		
		System.out.println(board);
	}
}
