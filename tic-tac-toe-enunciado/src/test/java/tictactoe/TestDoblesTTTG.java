package tictactoe;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.*;
import static org.mockito.Mockito.*;

public class TestDoblesTTTG {
	
	TicTacToeGame t3g = new TicTacToeGame();
    Connection c1;
    Connection c2;
    Player p1;
    Player p2;

    /**
	 * INICIALIZACION DE LAS VARIABLES NECESARIAS EN LOS TEST CON DOBLES
	 * */
	@Before
	public void setUp() {
		c1 = mock(Connection.class);
		t3g.addConnection(c1);
		c2 = mock(Connection.class);
		t3g.addConnection(c2);
		
		p1 = new Player(1, "x", "Jorge");
		t3g.addPlayer(p1);
        verify(c1).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1)));
        verify(c2).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1)));
        reset(c1); 
        reset(c2);
		p2 = new Player(2, "o", "Isaac");        
        t3g.addPlayer(p2);
        verify(c1).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
        verify(c2).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1,p2)));

        
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
	}
	
	/**
	 * TESTEO DE LA VICTORIA DEL PRIMER JUGADOR EN MOVER FICHA
	 * */
	@Test
	public void testVictoriaPrimerJugador() {
		t3g.mark(0);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(8);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(1);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(5);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(2);	
        
        ArgumentCaptor<TicTacToeGame.WinnerValue> argument = ArgumentCaptor.forClass(TicTacToeGame.WinnerValue.class);
        verify(c1).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), argument.capture());
        TicTacToeGame.WinnerValue event = argument.getValue();
        
        int[] pos = {0, 1, 2};       
        assertEquals("Gana el jugador primero: ", event.player.getId(), p1.getId());
        assertArrayEquals("Posiciones de las fichas: ", event.pos, pos);
	}
	
	/**
	 * TESTEO DE LA VICTORIA DEL SEGUNDO JUGADOR EN MOVER FICHA
	 * */
	@Test
	public void testVictoriaSegundoJugador() {
		t3g.mark(8);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(0);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(4);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(1);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(6);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(2);
        
        ArgumentCaptor<TicTacToeGame.WinnerValue> argument = ArgumentCaptor.forClass(TicTacToeGame.WinnerValue.class);
        verify(c1).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), argument.capture());
        TicTacToeGame.WinnerValue event = argument.getValue();
        
        int[] pos = {0, 1, 2};       
        assertEquals("Gana el jugador segundo: ", event.player.getId(), p2.getId());
        assertArrayEquals("Posiciones de las fichas: ", event.pos, pos);
	}
	
	/**
	 * TESTEO DEL EMPATE ENTRE AMBOS JUGADORES
	 * */
	@Test
	public void testEmpate() {
		t3g.mark(4);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(0);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(1);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(7);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(2);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(6);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(3);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p2);
        reset(c1);
        reset(c2);
        t3g.mark(5);
        verify(c1).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        verify(c2).sendEvent(TicTacToeGame.EventType.SET_TURN, p1);
        
        t3g.mark(8);	
        
        ArgumentCaptor<TicTacToeGame.WinnerValue> argument = ArgumentCaptor.forClass(TicTacToeGame.WinnerValue.class);
        verify(c1).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), argument.capture());
        TicTacToeGame.WinnerValue event = argument.getValue();
        
        assertEquals("Empate no hay evento: ", event, null);
	}

}
