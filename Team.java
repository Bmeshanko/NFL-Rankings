public class Team {
	public String name;
	public String nickname;
	public int alphabetizedNumber;
	public int wins;
	public int losses;
	public int ties;
	public double winPercent;
	public int pf;
	public int pa;
	public int net;
	public double rating;
	public Game[] games = new Game[17];
	public int g = 0;
	
	public Team(String name) {
		this.name = name;
		this.rating = 1500;
	}
	
	public void roundRating() {
		rating = ((int) ((double) rating * 100)) / (double) 100;
	}
	
	public String toString() {
		roundRating();
		return name + ": " + wins + "-" + losses + "-" + ties + ". Rating: " + rating;
	}
	
	public void addGame(Game game) {
		games[g++] = game;
	}
	
}
