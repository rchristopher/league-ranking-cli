# league-ranking-cli
A team league ranking CLI application in Java 11

## Building the JAR

Run the following gradle command: 

```
./gradlew jar
```

This will build the JAR into the /build/libs folder

## Running the application

Ensure that you have Java 11 installed on your machine.

```
java -jar ./build/libs/league-ranking-table.jar
```

Once its running, enter the team scores line by line in the following format:

```
<TeamName> <TeamScore>, <TeamName> <TeamScore>
```

e.g.

```
TeamOne 4, TeamTwo 5
TeamOne 3, TeamThree 1
```

Alternatively, run the jar with the --filename argument and input a filename containing team scores
in the same format as above.

e.g.

```
java -jar ./build/libs/league-ranking-table.jar --filename ./LeagueScores.txt
```