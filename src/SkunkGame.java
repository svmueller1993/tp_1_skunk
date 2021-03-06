import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class will have Skunk game logic.
 * 
 * @see CrapsGame.java
 *
 */
public class SkunkGame
{
	SkunkDice dice;
	SkunkDie die1;
	SkunkDie die2;
	int numberOfPlayers;
	int roundNumber;
	int roundChips;
	
	SkunkPlayer roundWinner;
	List<SkunkPlayer> players = new ArrayList<SkunkPlayer>();

	public SkunkGame(SkunkDie pDie1, SkunkDie pDie2, List<SkunkPlayer> players)
	{
		this.dice = new SkunkDice(pDie1, pDie2);
		this.die1 = pDie1;
		this.die2 = pDie2;
		this.players = players;
	}

	/**
	 * Finds the winners of each round in order to give the winner the round chips.
	 * Players with same score will be in tie.
	 * @return returns list of winners
	 */
	public List<SkunkPlayer> getRoundWinner()
	{
		int maxScore = players.get(0).getLastRoundScore();
		for (int i = 1; i < players.size(); i++)
		{
			if(maxScore < players.get(i).getLastRoundScore()) 
			{
				maxScore = players.get(i).getLastRoundScore();
			}
		}
		
		List<SkunkPlayer> roundWinner = new ArrayList<>();
		for (int i = 0; i < players.size(); i++)
		{
			if(maxScore == players.get(i).getLastRoundScore()) 
			{
				roundWinner.add(players.get(i));
			}
		}
		return roundWinner;
	}
	
	public List<SkunkPlayer> getGameScoreWinner()
	{
		int maxScore = players.get(0).getTotalScore();
		for (int i = 1; i < players.size(); i++)
		{
			if(maxScore < players.get(i).getTotalScore()) 
			{
				maxScore = players.get(i).getTotalScore();
			}
		}
		
		List<SkunkPlayer> gameWinner = new ArrayList<>();
		for (int i = 0; i < players.size(); i++)
		{
			if(maxScore == players.get(i).getTotalScore()) 
			{
				gameWinner.add(players.get(i));
			}
		}
		return gameWinner;
	}
	
	
	/**
	 * Finds game winner by comparing number of chips.
	 * Players with same chips will be in tie.
	 * @return list of game winners
	 */
	public List<SkunkPlayer> getGameWinner()
	{
		int maxScore = players.get(0).getChips();
		for (int i = 1; i < players.size(); i++)
		{
			if(maxScore < players.get(i).getChips()) 
			{
				maxScore = players.get(i).getChips();
			}
		}
		
		List<SkunkPlayer> gameWinner = new ArrayList<>();
		for (int i = 0; i < players.size(); i++)
		{
			if(maxScore == players.get(i).getChips()) 
			{
				gameWinner.add(players.get(i));
			}
		}
		
		return gameWinner;
	}
	
	/**
	 * Plays one round for given player.
	 * @param player given player
	 * @return returns true if user can continue play, otherwise false
	 */
	public boolean playGameForOneRound(SkunkPlayer player)
	{
		
		this.dice.roll();
		StdOut.println("Die one rolled: " + this.dice.getLastRoll1());
		StdOut.println("Die two rolled: " + this.dice.getLastRoll2());
		int rollScore = this.dice.getLastTotalRoll();
		
		if (rollScore == 2) // Means both dice rolled a 1 which voids all points
		{
			player.setTotalScore(0);
			player.setChips(player.getChips() - 4);
			StdOut.println("\nYou rolled two skunks, all scores have been set back to 0. You have " + player.getChips() + " chips left.");
			player.setRoundScore(0);
			roundChips = roundChips + 4;
			finishRound(player);
			return false;
		} 
		else if (dice.getLastRoll1() == 1 || dice.getLastRoll2() == 1) // next player will take turn and voids players round score
		{
			if (rollScore == 3) //rolling a skunk and a "Deuce, needs to be in this if statement because if they roll a 2 and a 1 it goes to both statements and removes chips from both
			{
				player.setChips(player.getChips() - 2);
				StdOut.println("\nYou rolled one skunk and a duece. You have " + player.getChips() + " chips left");
				roundChips = roundChips + 2;
			}
			else 
			{
				player.setChips(player.getChips() - 1);
				StdOut.println("\nYou rolled a skunk. You have " + player.getChips() + " chips left.");
				roundChips ++;
			}
			
			player.setRoundScore(0);
			finishRound(player);
			return false;
		}
		
		else
		{
			player.setRoundScore(player.getRoundScore() + rollScore);
			
			StdOut.println("Your total score is: " + player.getCurrentScore());
		
		}
		return true;
	}
	
	/**
	 * Finishes current round for given player.
	 * @param player given player
	 */
	public void finishRound(SkunkPlayer player)
	{
		int roundScore = player.getRoundScore();
		player.setTotalScore(player.getTotalScore() + roundScore);
		player.setRoundScore(0);
		player.setLastRoundScore(roundScore);
	}
	
	/**
	 * Returns game instructions.
	 * @return game instructions
	 */
	public String getGameInstructions() 
	{
		return "\nThe objective of the game is to accumulate a score of 100 points or more. A score is made by rolling the dice and combining \n"
				+ "the points on the two dice. The player has the privilege of continuing to shake to increase his score or of passing the dice to wait for \n"
				+ "the next series, thus preventing the possibility of rolling a Skunk and losing his score.\n"
				+ "Any number of players can play,The suggested number of chips to start is 50 \n"
				+ "The winner of each round collects all chips in \"kitty\" and in addition five chips from each losing player or 10 chips from any player without a score. \n"
				+ "The first player to accumulate a total of 100 or more points can continue to score as many points over 100 as he believes is needed to win.\n"
				+ "When he decides to stop, his total score is the \"goal.\" Each succeeding player receives one more chance to better the goal and end the game.\n"
				+ "\nPENALTIES: A skunk in any series voids the score for that series only and draws a penalty of 1 chip placed in the \"kitty,\" and loss of dice.\n"
				+ "A skunk and a deuce voids the score for that series only and draws a penalty of 2 chips placed in the \"kitty,\" and loss of dice.\n"
				+ "TWO skunks void the ENTIRE accumulated score and draws a penalty of 4 chips placed in the \"kitty,\" and loss of dice.\nPlayer must again start to score from scratch.\n";
	}
	
	public int getRoundChips()
	{
		return roundChips;
	}

	public void setRoundChips(int roundChips)
	{
		this.roundChips = roundChips;
	}

	/**
	 * At the end of each game, collect chips and distribute to game winners.
	 */
	public void collectChips()
	{
		List<SkunkPlayer> list = getRoundWinner();
		
		for (int i = 0; i < players.size(); i++)
		{
			SkunkPlayer p = players.get(i);
			if (!list.contains(p)) {
				// collect chips
				int k = 0;
				if (p.getTotalScore() == 0) {
					// collect 10 chips
					k = 10;
				} else {
					// collect 5 chips
					k = 5;
				}
				p.setChips(p.getChips() - k);
				roundChips = roundChips + k;
			}
		}
		
		for (int i = 0; i < list.size(); i++)
		{
			SkunkPlayer p = list.get(i);
			p.setChips(p.getChips() + roundChips / list.size());
		}
		roundChips = roundChips % list.size();		
	}
	
	public String displayChipNumbers()
	{
		String str = "";
		for (int i = 0; i < players.size(); i++) 
		{	
			SkunkPlayer p = players.get(i);
			str = str + "\n" + p.getName() + ", total score: " + p.getTotalScore()
			+ ", chips: " + p.getChips();
		}
		return str;
	}
	
}