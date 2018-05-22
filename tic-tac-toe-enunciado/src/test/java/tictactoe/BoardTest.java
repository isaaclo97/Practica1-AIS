package tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;
import es.codeurjc.ais.tictactoe.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;

public class BoardTest {
	
	public TicTacToeGame empateAux(TicTacToeGame t3g) {
		Player p1 = new Player(0, "x", "Jorge");
		Player p2 = new Player(1, "o", "Isaac");
		
		t3g.addPlayer(p1);
		t3g.addPlayer(p2);
		
		t3g.mark(0);
		t3g.mark(4);
		t3g.mark(1);
		t3g.mark(6);
		t3g.mark(2);
		
		return t3g;
	}
	
	public TicTacToeGame victoriaAux(TicTacToeGame t3g) {
		Player p1 = new Player(0, "x", "Jorge");
		Player p2 = new Player(1, "o", "Isaac");
		
		t3g.addPlayer(p1);
		t3g.addPlayer(p2);

		t3g.mark(0);
		t3g.mark(4);
		t3g.mark(3);
		t3g.mark(6);
		t3g.mark(2);
		t3g.mark(8);
		t3g.mark(7);
		t3g.mark(1);
		t3g.mark(5);
		
		return t3g;
	}
	
	@Test
	public void testCheckDraw_True() {
		TicTacToeGame t3g = new TicTacToeGame();
		t3g = victoriaAux(t3g);
		assertEquals("Empate entre ambos jugadores", t3g.checkDraw(), true);
	}
	
	@Test
	public void testCheckDraw_False() {
		TicTacToeGame t3g = new TicTacToeGame();		
		t3g = empateAux(t3g);
		assertEquals("Victoria de un jugador", t3g.checkDraw(), false);
	}
	
	@Test
	public void testgetCellsIfWinner_NoWinner() {
		TicTacToeGame t3g = new TicTacToeGame();
		t3g = victoriaAux(t3g);
		WinnerResult wr = new WinnerResult();
		wr.win = false;
		wr.pos = null;
		WinnerResult wr2 = t3g.checkWinner();
		assertEquals("No hay victoria: boolean", wr2.win, wr.win);
		assertEquals("No hay victoria: array vacio", wr2.pos, wr.pos);
	}
	
	@Test
	public void testgetCellsIfWinner_Winner() {
		TicTacToeGame t3g = new TicTacToeGame();
		t3g = empateAux(t3g);
		WinnerResult wr = new WinnerResult();
		wr.win = true;
		int[] val = {0, 1, 2};
		wr.pos = val;
		WinnerResult wr2 = t3g.checkWinner();
		assertEquals("Hay victoria: boolean", wr2.win, wr.win);
		assertArrayEquals("Hay victoria: array iguales", wr2.pos, wr.pos);
	}
	
}
