package tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import es.codeurjc.ais.tictactoe.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;

public class BoardTest {
	
	TicTacToeGame t3g;
	
	public void generadorVictoria() {		
		t3g.mark(0);
		t3g.mark(4);
		t3g.mark(1);
		t3g.mark(6);
		t3g.mark(2);
	}
	
	public void generadorEmpate() {
		t3g.mark(0);
		t3g.mark(4);
		t3g.mark(3);
		t3g.mark(6);
		t3g.mark(2);
		t3g.mark(8);
		t3g.mark(7);
		t3g.mark(1);
		t3g.mark(5);
	}

	@Before
	public void setUp() {
		t3g = new TicTacToeGame();
		
		t3g.addPlayer(new Player(0, "x", "Jorge"));
		t3g.addPlayer(new Player(1, "o", "Isaac"));
	}
	
	@Test
	public void testCheckDraw_True() {
		generadorEmpate();
		assertEquals("Empate entre ambos jugadores", t3g.checkDraw(), true);
	}
	
	@Test
	public void testCheckDraw_False() {		
		generadorVictoria();
		assertEquals("Victoria de un jugador", t3g.checkDraw(), false);
	}
	
	@Test
	public void testgetCellsIfWinner_NoWinner() {
		generadorEmpate();
		WinnerResult wr = new WinnerResult();
		wr.win = false;
		wr.pos = null;
		WinnerResult wr2 = t3g.checkWinner();
		assertEquals("No hay victoria: boolean", wr2.win, wr.win);
		assertEquals("No hay victoria: array vacio", wr2.pos, wr.pos);
	}
	
	@Test
	public void testgetCellsIfWinner_Winner() {
		generadorVictoria();
		WinnerResult wr = new WinnerResult();
		wr.win = true;
		int[] val = {0, 1, 2};
		wr.pos = val;
		WinnerResult wr2 = t3g.checkWinner();
		assertEquals("Hay victoria: boolean", wr2.win, wr.win);
		assertArrayEquals("Hay victoria: array iguales", wr2.pos, wr.pos);
	}
	
}
