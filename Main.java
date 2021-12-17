import com.jaunt.*;
import java.io.*;
import java.util.Date;
public class Main {
	public static void main(String[] args) throws Exception {
		UserAgent userAgent = new UserAgent();                       
	    userAgent.visit("https://www.nfl.com/standings/");
	    String text = userAgent.doc.innerHTML().substring(66600, 149000);
	    PrintWriter pw = new PrintWriter("parsed.txt");
	    WebHelper wh = new WebHelper();
	    
	    String info = wh.removeTags(text);
	    
	    pw.println(wh.parse(info));
	    pw.close();
	    
	    League nfl = new League();
	    nfl.initalizeWithStandings("parsed.txt");
	    nfl.sortByName();
	    nfl.games("games.txt");
	    nfl.sort();
	    
	    pw = new PrintWriter("index.html");
	    
	    pw.println(wh.init);
	    
	    Date date = new Date();
	    
	    pw.println("<h1>NFL Rankings 2021. Last updated " + date.toString() + "</h1>");
	    pw.println("<p>" + wh.elo + "</p>");
	    pw.println("<p>");
	    for (int i = 0; i < nfl.teams.length; i++) {
	    	pw.println((i + 1) + ". " + nfl.teams[i].toString() + "<br>");
	    }
	    pw.println("</p>");
	    pw.println(wh.close);
	    pw.close();
	}
}
