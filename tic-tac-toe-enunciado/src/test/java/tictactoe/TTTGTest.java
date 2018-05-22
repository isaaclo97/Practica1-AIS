package tictactoe;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import es.codeurjc.ais.tictactoe.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;

import static org.mockito.Mockito.*;

public class TTTGTest {

	@Test
	public void testCheckDraw_True() {
		TicTacToeGame t3g = new TicTacToeGame();
		
		Connection c1 = mock(Connection.class);
		
		t3g.addConnection(c1);
		
		Player p1 = new Player(0, "x", "Jorge");
		Player p2 = new Player(1, "o", "Isaac");
		t3g.addPlayer(p1);
		t3g.addPlayer(p2);
		
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		
	}
}
