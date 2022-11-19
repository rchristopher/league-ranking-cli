package leaguerankingtable;

/**
 * Team object holding the team name and their current league score
 */
public class Team
{
	private final String name;
	private int leagueScore;

	public Team(String name)
	{
		this.name = name.trim();
	}

	public void wins()
	{
		this.leagueScore += 3;
	}

	public void draws()
	{
		this.leagueScore += 1;
	}

	public int getLeagueScore()
	{
		return this.leagueScore;
	}

	public String getName()
	{
		return this.name;
	}

	/**
	 * Play a game between two teams and update the relevant team's score afterwards
	 *
	 * @param teamTwo The team that is playing this team
	 * @param teamOneScore The score for this team
	 * @param teamTwoScore The score for the other team
	 */
	public void playGame(Team teamTwo, int teamOneScore, int teamTwoScore)
	{
		if (teamOneScore > teamTwoScore)                // Team one wins
		{
			this.wins();
		}
		else if (teamTwoScore > teamOneScore)            // Team two wins
		{
			teamTwo.wins();
		}
		else                                                        // Draw
		{
			this.draws();
			teamTwo.draws();
		}
	}

	@Override
	public String toString()
	{
		return this.name + ", " + this.leagueScore + " " + (this.leagueScore > 1 || this.leagueScore == 0 ? "pts" : "pt");
	}
}