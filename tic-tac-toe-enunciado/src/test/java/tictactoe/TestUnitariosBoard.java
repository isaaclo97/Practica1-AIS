package tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import es.codeurjc.ais.tictactoe.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;

public class TestUnitariosBoard {
	
	TicTacToeGame t3g;
	
	/**
	 * FUNCION GENERADORA DE VICTORIAS DEL PRIMER JUGADOR SOBRE EL SEGUNDO
	 * */
	public void generadorVictoria() {		
		t3g.mark(0);
		t3g.mark(4);
		t3g.mark(1);
		t3g.mark(6);
		t3g.mark(2);
	}
	
	/**
	 * FUNCION GENERADORA DE EMPATES ENTRE AMBOS JUGADORES
	 * */
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

	/**
	 * INICIALIZACION DE LAS VARIABLES NECESARIAS EN LOS TEST UNITARIOS
	 * */
	@Before
	public void setUp() {
		t3g = new TicTacToeGame();
		
		t3g.addPlayer(new Player(0, "x", "Jorge"));
		t3g.addPlayer(new Player(1, "o", "Isaac"));
	}
	
	/**
	 * TEST SOBRE LA COMPROBACION DIRECTA DE EMPATES
	 * */
	@Test
	public void testCheckDraw_True() {
		generadorEmpate();
		assertEquals("Empate entre ambos jugadores", t3g.checkDraw(), true);
	}
	
	/**
	 * TEST SOBRE LA VICTORIA DIRECTA DE UNO DE LOS JUGADORES
	 * */
	@Test
	public void testCheckDraw_False() {		
		generadorVictoria();
		assertEquals("Victoria de un jugador", t3g.checkDraw(), false);
	}
	
	/**
	 * TEST SOBRE LA FUNCION COMPROBANTE DE SI HAY GANADOR, EMPATANDO EN ESTE CASO
	 * */
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
	
	/**
	 * TEST SOBRE LA FUNCION COMPROBANTE DE SI HAY GANADOR, VICTORIA DE UN JUGADOR
	 * */
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
