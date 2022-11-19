package leaguerankingtable;

import java.util.*;

/**
 * Leaderboard class holding a map of teams in the league
 */
public class Leaderboard
{
	private final Map<String, Team> leagueScores = new HashMap<>();

	/**
	 * Get a team from the league, or add a new team if it doesn't exist yet
	 *
	 * @param teamName The name of the team in the league
	 * @return Team object for the teamName entered
	 */
	public Team getTeam(String teamName)
	{
		// Only store team objects once
		leagueScores.putIfAbsent(teamName, new Team(teamName));

		return leagueScores.get(teamName);
	}

	/**
	 * Print the final scores of the league in order or most points
	 */
	public void printFinalScores()
	{
		if (leagueScores.size() > 0)
		{
			// Get a list of all teams
			List<Team> finalTeamScores = new ArrayList<>(leagueScores.values());

			// Sort into correct score and name order
			finalTeamScores.sort(Comparator.comparing(Team::getLeagueScore).reversed().thenComparing(Team::getName));

			// Print out teams in order with position indicators
			for (int i = 1; i < finalTeamScores.size() + 1; i++)
			{
				Team team = finalTeamScores.get(i - 1);
				int position = i;

				if (i >= 2 && team.getLeagueScore() == finalTeamScores.get(i - 2).getLeagueScore())
				{
					position--;
				}

				System.out.println(position + ". " + finalTeamScores.get(i - 1).toString());
			}
		}
		else
		{
			System.out.println("No teams found in input");
		}
	}
}
