package leaguerankingtable;

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