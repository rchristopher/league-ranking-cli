package league.ranking.table;

import leaguerankingtable.LeagueRankingCLI;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class LeagueRankingCLITest
{
	@Test
	public void test_basic_valid_input()
	{
		// Setup input lines list
		String inputLines = "Lions 3, Snakes 3" + System.lineSeparator() +
				"Tarantulas 1, FC Awesome 0" + System.lineSeparator() +
				"Lions 1, FC Awesome 1" + System.lineSeparator() +
				"Tarantulas 3, Snakes 1" + System.lineSeparator() +
				"Lions 4, Grouches 0" + System.lineSeparator();

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "1. Tarantulas, 6 pts" + System.lineSeparator() +
				"2. Lions, 5 pts" + System.lineSeparator() +
				"3. FC Awesome, 1 pt" + System.lineSeparator() +
				"3. Snakes, 1 pt" + System.lineSeparator() +
				"5. Grouches, 0 pts";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_basic_valid_input_empty_last_line()
	{
		// Setup input lines list
		String inputLines = "Lions 3, Snakes 3" + System.lineSeparator() +
				"Tarantulas 1, FC Awesome 0" + System.lineSeparator() +
				"Lions 1, FC Awesome 1" + System.lineSeparator() +
				"Tarantulas 3, Snakes 1" + System.lineSeparator() +
				"Lions 4, Grouches 0" + System.lineSeparator() +
				System.lineSeparator();

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "1. Tarantulas, 6 pts" + System.lineSeparator() +
				"2. Lions, 5 pts" + System.lineSeparator() +
				"3. FC Awesome, 1 pt" + System.lineSeparator() +
				"3. Snakes, 1 pt" + System.lineSeparator() +
				"5. Grouches, 0 pts";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_basic_valid_input_file()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(new String[]{"--filename", "./LeagueScores.txt"}); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "1. Lions, 8 pts" + System.lineSeparator() +
				"2. Tarantulas, 7 pts" + System.lineSeparator() +
				"3. FC Awesome, 4 pts" + System.lineSeparator() +
				"4. Grouches, 1 pt" + System.lineSeparator() +
				"4. Snakes, 1 pt";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_basic_valid_input_team_2_wins()
	{
		// Setup input lines list
		String inputLines = "Lions 3, Snakes 3" + System.lineSeparator() +
				"Tarantulas 1, FC Awesome 0" + System.lineSeparator() +
				"Lions 1, FC Awesome 1" + System.lineSeparator() +
				"Tarantulas 3, Snakes 4" + System.lineSeparator() +
				"Lions 4, Grouches 0" + System.lineSeparator();

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "1. Lions, 5 pts" + System.lineSeparator() +
				"2. Snakes, 4 pts" + System.lineSeparator() +
				"3. Tarantulas, 3 pts" + System.lineSeparator() +
				"4. FC Awesome, 1 pt" + System.lineSeparator() +
				"5. Grouches, 0 pts";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_basic_invalid_input_file()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(new String[]{"--filename", "./doesNotExist.txt"}); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "Error reading file: ./doesNotExist.txt (No such file or directory)";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_basic_invalid_input_arg_format()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(new String[]{"--filenames", "./doesNotExist.txt"}); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "Usage error: use --filename <filepath>";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_invalid_input_comma()
	{
		// Setup input lines list
		// Missing comma on first line
		String inputLines = "Lions 3 Snakes 3" + System.lineSeparator() +
				"Tarantulas 1, FC Awesome 0" + System.lineSeparator() +
				"Lions 1, FC Awesome 1" + System.lineSeparator() +
				"Tarantulas 3, Snakes 1" + System.lineSeparator() +
				"Lions 4, Grouches 0" + System.lineSeparator();

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "Invalid input";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_invalid_input_empty_name()
	{
		// Setup input lines list
		// Missing comma on first line
		String inputLines = "Lions 3 Snakes 3" + System.lineSeparator() +
				"Tarantulas 1, FC Awesome 0" + System.lineSeparator() +
				"Lions 1, FC Awesome 1" + System.lineSeparator() +
				"Tarantulas 3, Snakes 1" + System.lineSeparator() +
				"Lions 4, 0" + System.lineSeparator();

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "Invalid input";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_invalid_input_empty()
	{
		// Setup input lines list
		// Missing comma on first line
		String inputLines = "";

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "No teams found in input";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test_invalid_input_new_line()
	{
		// Setup input lines list
		// Missing comma on first line
		String inputLines = System.lineSeparator();

		// Input each line
		InputStream in = new ByteArrayInputStream(inputLines.getBytes());
		System.setIn(in);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		LeagueRankingCLI.main(null); // call the main method

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = String.join(System.lineSeparator(), lines);

		// checkout output
		String expected = "No teams found in input";

		Assert.assertEquals(expected, actual);
	}
}
