import java.io.*;

public class League {
	public Team[] teams = new Team[32];
	
	public void initalizeWithStandings(String file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		int teamsAdded = 0;
		while (true) {
			String line = br.readLine();
			if (line == null) return;
			for (int i = 0; i < line.length(); i++) {
				if (Character.toLowerCase(line.charAt(i)) != line.charAt(i) && line.length() > 24) {
					// This line contains a capital letter and is therefore an NFL Team name.
					Team team = new Team(line.substring(i));
					
					// Nickname
					for (int j = 0; j < 3; j++) {
						br.readLine();
					}
					String nickname = br.readLine();
					nickname = nickname.substring(12);
					team.nickname = nickname;
					
					// Wins
					for (int j = 0; j < 5; j++) {
						br.readLine();
					}
					String wins = br.readLine();
					wins = wins.substring(14);
					team.wins = Integer.parseInt(wins);
				
					// Losses
					for (int j = 0; j < 2; j++) {
						br.readLine();
					}
					String losses = br.readLine();
					losses = losses.substring(14);
					team.losses = Integer.parseInt(losses);
					
					// Ties
					for (int j = 0; j < 2; j++) {
						br.readLine();
					}
					String ties = br.readLine();
					ties = ties.substring(14);
					team.ties = Integer.parseInt(ties);
					
					teams[teamsAdded++] = team;
					break;
				}
			}
		}
	}
	
	private void winPercentage() {
		for (int i = 0; i < teams.length; i++) {
			teams[i].winPercent = (double) teams[i].wins + 0.5 * ((double) teams[i].wins) / (double) (teams[i].wins + teams[i].losses + teams[i].ties);
		}
	}
	
	public void sort() {
		winPercentage();
		int i = 1;
		while (i < teams.length) {
			Team x = teams[i];
			int j = i - 1;
			while (j >= 0 && teams[j].rating < x.rating) {
				teams[j + 1] = teams[j];
				j--;
			}
			teams[j + 1] = x;
			i++;
		}
	}
	
	public void sortByName() {
		int i = 1;
		while (i < teams.length) {
			Team x = teams[i];
			int j = i - 1;
			while (j >= 0 && teams[j].name.compareTo(x.name) > 0) {
				teams[j + 1] = teams[j];
				j--;
			}
			teams[j + 1] = x;
			i++;
		}
	}
	
	public void fromString(String s, Team[] teams) {
		int home = Integer.parseInt(s.substring(0, s.indexOf('v')));
		int away = Integer.parseInt(s.substring(s.indexOf('v') + 1, s.indexOf(':')));
		int homeScore = Integer.parseInt(s.substring(s.indexOf(':') + 1, s.indexOf('-')));
		int awayScore = Integer.parseInt(s.substring(s.indexOf('-') + 1));
		
		Game game = new Game(teams[home], teams[away], homeScore, awayScore);
		double homeRating = teams[home].rating + 100;
		double awayRating = teams[home].rating - 100;
		
		double aDiff = homeRating - awayRating;
		double aExpected = 20 / (1 + Math.pow(10, aDiff / 400));
		double aScoreDiff  = 10 + (awayScore - homeScore);
		
		double hDiff = awayRating - homeRating;
		double hExpected = 20 / (1 + Math.pow(10, hDiff / 400));
		double hScoreDiff = 10 + (homeScore - awayScore);
		
		if (awayScore > homeScore) {
			aScoreDiff += 20;
			hScoreDiff -= 20;
		} else if (homeScore > awayScore) {
			hScoreDiff += 20;
			aScoreDiff -= 20;
		}
		teams[home].rating += 1.25 * (hScoreDiff - hExpected);
		teams[home].addGame(game);
		teams[away].rating += 1.25 * (aScoreDiff - aExpected);
		teams[away].addGame(game);
	}
	
	public void games(String file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("games.txt"));
		while(true) {
			String line = br.readLine();
			if (line == null) break;
			fromString(line, teams);
		}
	}
}
