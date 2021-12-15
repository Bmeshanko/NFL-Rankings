public class Game {
	private Team home;
	private Team away;
	private int homeScore;
	private int awayScore;

	public Game(Team home, Team away, int homeScore, int awayScore) {
		this.home = home;
		this.away = away;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}
}
