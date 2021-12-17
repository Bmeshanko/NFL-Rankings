// Class to Simply PrintWriter calls.
public class WebHelper {
	public String init = "<!DOCTYPE html>\n<html>\n<head>\n"
			+ "<link rel=\"stylesheet\" href=\"style.css\">\n</head>\n<body>";
	public String close = "</body>\n</html>";
	
	public String elo = "These ratings are calculated using the Elo rating system."
			+ " Elo is commonly used in chess<br> where players can either win, lose or draw."
			+ " However, sports can have blowout wins or<br> close losses. These ratings account" 
			+ " for that by calculating an expected score, and taking<br> its difference from the"
			+ " actual score as a variable.";
	public WebHelper() {
		
	}
	
	public String parse(String input) {
		String parsed = "";
		for (int i = 0; i < input.length(); i++) {
	    	if (i < input.length() - 9 && input.substring(i, i + 9).equals("Standings")) i += 800;
	    	if (i < input.length() - 11 && input.substring(i, i + 11).equals("Advertising")) i += 1900;
	    	parsed += input.charAt(i);		
 	    }
		return parsed;
	}
	
	public String removeTags(String text) {
		String info = "";
	    for (int i = 0; i < text.length(); i++) {
	    	if (text.charAt(i) == '<') {
	    		while (i < text.length() && text.charAt(i) != '>') i++;
	    	} else {
	    		info += text.charAt(i);
	    	}
	    }
	    return info;
	}
}
