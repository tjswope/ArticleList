// class: Article
// written by: Mr. Swope
// date: 11/1/2023
// description: Contains the implementation for an Article that is parsed from a JSONObject.
//              An Article will have a title, author, date and content along with all typical 
//              accessors and modifiers.
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import parser.*;

public class Article {
	// instance fields
	private String title;
	private String author;
	private Date date;
	private String content;

	// constructor - passed a JSONObject, from which Article information is parsed.
	public Article(JSONObject input) {
		setAuthor((String)input.get("author"));
		setTitle((String) input.get("title"));
		setDate((String) input.get("publishedAt"));
		setContent((String) input.get("content"));
	}

	// setters
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	// sets the date from a String. If a date can't be constructed from the supplied string
	// it will be set equal to today.
	public void setDate(String dateString) {

		// The newsapi includes a 'T' and 'Z', however or DateFormat will not, so remove
		// these extra characters.
		dateString = dateString.replace('T', '-')
				.substring(0, (dateString.indexOf('Z') != -1) ? dateString.indexOf('Z') : dateString.length());

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		try {
			this.date = df.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}
	}

	// getters
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	// get's a string representation of date.
	public String getStringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		return formatter.format(date);
	}

	public String toString() {
		return "title = " + getTitle() + "\n" +
				"author = " + getAuthor() + "\n" + 
				"date = " + getStringDate() + "\n" + 
				"content = " + getContent() + "\n";
	}
}
