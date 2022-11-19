package leaguerankingtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * League Ranking CLI application entry point
 */
public class LeagueRankingCLI
{
	private static final Pattern pattern = Pattern.compile("^(\\D+)\\s*(\\d+),\\s*(\\D+)\\s*(\\d+)");

	public static void main(String[] args)
	{
		File inputFile = null;

		// Read any command line arguments and validate that they are correct
		if (args != null && args.length > 0)
		{
			for (int i = 0; i < args.length; i++)
			{
				if (args[i].equalsIgnoreCase("--filename"))
				{
					if (args.length >= i + 1)
					{
						inputFile = new File(args[i + 1]);
					}
				}
			}

			if (inputFile == null)
			{
				System.out.println("Usage error: use --filename <filepath>");
				return;
			}
		}

		Leaderboard leaderboard = new Leaderboard();

		Scanner in;

		// Attempt to read input file if it was provided in the command-line args
		if (inputFile != null)
		{
			try
			{
				in = new Scanner(inputFile);
			} catch (FileNotFoundException e)
			{
				System.out.println("Error reading file: " + e.getMessage());
				return;
			}
		}
		else
		{
			in = new Scanner(System.in);
		}

		// Read input lines from System.in scanner
		while (in.hasNextLine())
		{
			String inputLine = in.nextLine();

			// End input if line separator is entered
			if (inputLine.equals("") || inputLine.equals(System.lineSeparator()))
			{
				break;
			}

			// Find team details from regex pattern matcher
			Matcher matcher = pattern.matcher(inputLine);
			if (matcher.find())
			{
				// Get input fields from regex matcher
				String teamOneName = matcher.group(1);
				int teamOneGameScore = Integer.parseInt(matcher.group(2));
				String teamTwoName = matcher.group(3);
				int teamTwoGameScore = Integer.parseInt(matcher.group(4));

				// Get teams from the league or create new teams if they don't exist yet
				Team teamOne = leaderboard.getTeam(teamOneName);
				Team teamTwo = leaderboard.getTeam(teamTwoName);

				// Update team league scores based on current game score
				teamOne.playGame(teamTwo, teamOneGameScore, teamTwoGameScore);
			}
			else
			{
				System.out.println("Invalid input");
				return;
			}
		}

		leaderboard.printFinalScores();
	}
}
