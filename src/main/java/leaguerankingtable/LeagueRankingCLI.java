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
	private static final Pattern PATTERN = Pattern.compile("^(\\D+)\\s*(\\d+),\\s*(\\D+)\\s*(\\d+)");

	public static void main(String[] args)
	{
		File inputFile = null;

		// Read any command line arguments and validate that they are correct
		if (args != null && args.length > 0)
		{
			for (int i = 0; i < args.length; i++)
			{
				if ("--filename".equalsIgnoreCase(args[i]) && args.length >= i + 1)
				{
					inputFile = new File(args[i + 1]);
					break;
				}
			}

			if (inputFile == null)
			{
				System.out.println("Usage error: use --filename <filepath>");
				return;
			}
		}

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

		Leaderboard leaderboard = new Leaderboard();

		// Read input lines from System.in scanner
		while (in.hasNextLine())
		{
			String inputLine = in.nextLine();

			// End input if line separator is entered
			if ("".equals(inputLine) || inputLine.equals(System.lineSeparator()))
			{
				break;
			}

			// Find team details from regex pattern matcher
			Matcher matcher = PATTERN.matcher(inputLine);
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

		try
		{
			in.close();
		} catch (Exception e)
		{
			System.out.println("Error closing scanner: " + e.getMessage());
		}
	}
}
