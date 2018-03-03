import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SkunkPlayerTest
{

	@Before
	public void setUp() throws Exception
	{
		
	}

	@Test
	public void testSkunkPlayer()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		assertNotNull(p);
	}
	
	
	
	@Test
	public void testAddPoints()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		p.addPoints(4);
		int points = p.getPlayerTotalScore();
		assertTrue(points == 4);
		
	}
	
	@Test
	public void testTotalScoreVoid()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		p.addPoints(5);
		p.addPoints(6);
		p.totalScoreVoid();
		int points = p.getPlayerTotalScore();
		assertTrue(points == 0);
	}
	
	@Test
	public void testSetTotalScore()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		p.setTotalScore(10);
		int points = p.getPlayerTotalScore();
		assertTrue(points == 10);
	}
	
	@Test
	public void testGetRoundsetRoundScore()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		p.setRoundScore(4);
		assertTrue(p.getRoundScore() == 4);
	}
	
	@Test
	public void testGetPlayerName()
	{
		String name = "Test Player";
		SkunkPlayer p = new SkunkPlayer(name);
		assertTrue(p.getPlayerName() == name);
	}
	
	@Test
	public void testGetChipsSetChips()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		p.setChips(55);
		assertTrue(p.getChips() == 55);
	}
	
	@Test
	public void testGetRoundWinsSetRoundWins()
	{
		SkunkPlayer p = new SkunkPlayer("Test Player");
		p.setRoundWins(3);
		assertTrue(p.getRoundWins() == 3);
	}
}
