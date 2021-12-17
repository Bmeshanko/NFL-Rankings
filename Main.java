import com.jaunt.*;
import java.io.*;
import java.util.Date;
public class Main {
	public static void main(String[] args) throws Exception {
		UserAgent userAgent = new UserAgent();                       
	    userAgent.visit("https://www.nfl.com/standings/");
	    String text = userAgent.doc.innerHTML().substring(66600, 149000);
	    PrintWriter pw = new PrintWriter("parsed.txt");
	    String info = "";
	    for (int i = 0; i < text.length(); i++) {
	    	if (text.charAt(i) == '<') {
	    		while (i < text.length() && text.charAt(i) != '>') i++;
	    	} else {
	    		info += text.charAt(i);
	    	}
	    	
	    }
	    
	    String parsed = "";
	    for (int i = 0; i < info.length(); i++) {
	    	if (i < info.length() - 9 && info.substring(i, i + 9).equals("Standings")) i += 800;
	    	
	    	if (i < info.length() - 11 && info.substring(i, i + 11).equals("Advertising")) i += 1900;
	    	
	    	parsed += info.charAt(i);		
 	    }
	    pw.println(parsed);
	    pw.close();
	    
	    League nfl = new League();
	    nfl.initalizeWithStandings("parsed.txt");
	    nfl.sortByName();
	    nfl.games("games.txt");
	    nfl.sort();
	    
	    pw = new PrintWriter("ratings.html");
	    
	    
	    WebHelper wh = new WebHelper();
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
