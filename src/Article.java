
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import parser.*;

public class Article {
	private String title;
	private String author;
	private Date date;
	private String content;

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
	
	public void setDate(String dateString) {

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
