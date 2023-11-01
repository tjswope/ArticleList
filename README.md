Article List
 

APIs are mechanisms that enable two software components to communicate with each other using a set of definitions and protocols. For example, the weather bureau’s software system contains daily weather data. The weather app on your phone “talks” to this system via APIs and shows you daily weather updates on your phone.

JSON is an open standard file format and data interchange format that uses human-readable text to store and transmit data objects consisting of attributes – value pairs and arrays. It is a common data format with diverse uses in electronic data interchange, including that of web applications with servers.

For this project we’ll use JSON to interpret data from newsapi.org. News API is a simple, easy-to-use API that returns JSON search results for current and historic news articles published by over 80,000 worldwide sources. Before you can start this project, you’ll need to create an account and get an API key from newsapi.org.

Go to newsapi.org and create an account. Once you’ve created your account, they’ll give you an API key. Do not share your API key with anyone. 

Extensive starter code is provided for this project. You should get this starter code from my github account @ https://github.com/tjswope/ArticlesProject.git. 

1. In eclipse, navigate to Window->Show View->Other.
   
<div style="text-align: center;">
<img width="300" alt="image" src="https://github.com/tjswope/ArticleList/assets/35576679/41e39bdf-4cbb-4267-9fc8-1938c75c8dcb" >
</div>

2. Now expand Git and select Git Repositories

<img width="400" alt="image" src="https://github.com/tjswope/ArticleList/assets/35576679/ca9ab1e3-62c1-4541-becb-b3e270599c5f">

3. The Git Repositories View should open in the bottom left hand corner of eclipse. Click on “Clone a Git repository. 

<img width="400" alt="image" src="https://github.com/tjswope/ArticleList/assets/35576679/59aa38ee-9af0-46a7-92f3-b3d69205f192">


4. Enter https://github.com/tjswope/ArticlesProject.git in the URI field. You can leave everything else as is.


<img width="400" alt="image" src="https://github.com/tjswope/ArticleList/assets/35576679/1bf6afc1-953c-4da5-ac20-0a54ec9fd4c9">


5. Leave everything as it is on the next window.

<img width="400" alt="image" src="https://github.com/tjswope/ArticleList/assets/35576679/83d6109a-4d68-48ed-955a-c68ab19aaed8">


6.  When you’ve reached the window that allows you to choose the local destination where your project will be saved, click on the browse button, and navigate to your workspace folder. Next, check the box next to Import all existing Eclipse projects after clone finishes and then click finished.

<img width="400" alt="image" src="https://github.com/tjswope/ArticleList/assets/35576679/1ba90094-9fff-4d54-b75a-8503296b0d59">

















Once you’ve cloned the project, you’ll see that there are several packages and classes. We’ll be working in two of these classes: ArticleList and Main.  

The implementation for class Article is shown below.  Some of the code may look a bit intimidating, but the good news is that you won’t have to change anything in this class. The class represents an Article that will be decoded from your API call. 

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
		dateString = dateString.replace('T', '-').substring(0, (dateString.indexOf('Z') != -1)   
                            ? dateString.indexOf('Z') : dateString.length());

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
		return "title = " + getTitle() + "\n" + "author = " + getAuthor() + "\n" + 
			"date = " + getStringDate() + "\n" + "content = " + getContent() + "\n";
	}
}

The majority of your work will be done in the ArticleList class. This class will contain an ArrayList of Articles. The beginning of this class is shown below:


public class ArticleList {

	private ArrayList<Article> articles;

	public ArticleList() {

		ArticleGetter a = new ArticleGetter("eagles", "your API key here");
		JSONArray arr = a.getArticles();

		// if the JSON request was successful, arr will not be null, and we can loop
		// through the "articles" in arr, parse them using a JSONObject, and add them
		// to the articles ArrayList. 
		if(arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				JSONObject new_obj = (JSONObject) arr.get(i);
				articles.add(new Article(new_obj));
			}
		}
	}
}


Complete class ArticleList:

Instance Fields: articles is currently the only instance field. Add a second instance field named topic. This instance field will be used to store the topic that you are getting articles on.


Constructor:
This class contains one constructor, which is almost finished. You’re going to make three changes.

1.	When you currently run your project, you’ll get a null pointer exception. Update your project so that you’ll no longer get this error.
2.	The topic that you’ll research is currently “eagles”. Add a parameter to the constructor that will allow the user to pass a string for the topic that will be requested. Use this parameter to initialize your new instance field, and also pass it to the ArticleGetter constructor in place of “eagles”.
3.	Replace “your API key here” with your API key from newsapi.org.


Mutator Methods
public void swap(int article1, int ariticle2) : Switches positions of article1 and article2 in articles. Remember that Articles are objects; You shouldn’t copy individual properties, instead you should move objects.

public void removeArticles(String date) : Removes all Articles that were written before date. This method will be a bit tricky because you’ll need to make use of the Date class API. 

You can create a date object from a specific date as follows:



Calendar cal = Calendar.getInstance();
cal.set(year, month, date);   // year, month and date are all integers. 
Date date = cal.getTime();    // Creates a new Date object @ date/month/year


A partial Date class API is shown below. You won’t need to use all of the methods that are listed.

boolean	after(Date when)
Tests if this date is after the specified date.
int	compareTo(Date anotherDate)
Compares two Dates for ordering.
boolean	equals(Object obj)
Compares two dates for equality.
String
toString()



public void removeAuthor(String author) : Removes all Articles that were written by author.

 
Accessor Methods

public ArrayList<Article> findKeyWord(String word) : Creates and returns an ArrayList of all Articles that contain word in their title.

public ArrayList< Article > getArticles(String author) : Returns an ArrayList of all Articles that were written by author.

public String getContent(String title) : Returns the content of the Article with the given title.

public int numberOfArticles() : Returns the number of Articles in the ArrayList.

public String toString() : Returns a String representation of all Articles in the ArrayList. This string should contain the topic for your Articles and how many there are, along with each Article separated by a newline, which can be inserted into a string as “\n”. When printed, this string would look like the following: 


Topic: eagles                                                         number of articles: 10 

title = NFL trade deadline 2023: Identifying teams that should be buyers and sellers
author = Tyler Sullivan
date = 2023-10-31
content = We are a few hours away from the NFL trade deadline. Between now and 4 p.m., various general managers across the league will be on the phone trying to wheel and deal to improve their club either for … [+6320 chars]

title = 2023 NFL trade deadline: One deal each NFL contender should make
author = Tyler Sullivan
date = 2023-10-31
content = The NFL trade deadline is here and teams are already wheeling and dealing. Leading into the Oct. 31 deadline, we've already seen a few deals go down, including the Eagles landing star safety Kevin By… [+7835 chars]

title = 2023 NFL trade deadline primer: Things to know, players who could be dealt, likely buyers and sellers, more
author = Cody Benjamin
date = 2023-10-31
content = The 2023 NFL season is fast approaching the midway point, when all 32 clubs take stock of their direction. Some will buckle in for a legitimate push at the playoffs. Others may swallow the tough pill… [+3547 chars]

title = NFL Week 9 Power Rankings: 49ers, Chiefs struggles turn top of league upside down; major test awaits new No. 1
author = Pete Prisco
date = 2023-10-31
content = Three weeks ago, it looked so simple. The San Francisco 49ers looked like the top team in the NFC with the Kansas City Chiefs the top team in the AFC.
Why play the rest of the season? It was so obvi… [+9925 chars]



 
Update class Main:  

This class should instantiate a default ArticleList and display a menu that allows the user to perform the actions implemented in your ArticleList class.  This menu should be inside of a loop so that the user can continue to use the class’s methods until they decide to quit the program.

Add comments:  

Much of the code for this project was written by Mr. Swope and Fang Yidong. You should take credit for the changes that you’ve made while still giving credit to the original authors. Update your ArticleList and Main classes by adding the following three lines of comments below the existing class level comments. Make sure that your description is in fact descriptive.


// updated by:
// date:
// description:


Next, add method level comments to each of the methods that you wrote in the ArticleList class. Each method should have our regular four lines of comments. Again, make sure your comments are descriptive.


// method:
// description:
// parameters:
// return:


Finally, add in line comments to any sections of code that are complex. This sort of documentation is up to you as the author, but you should try to write comments for any section of code that might not be immediate clear when viewed.
![image](https://github.com/tjswope/ArticleList/assets/35576679/1423cf71-67f8-44c7-91c8-1ecc32ccedb0)