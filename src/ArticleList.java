// class: ArticleList
// written by: Mr. Swope
// description: 

import parser.*;
import java.util.ArrayList;

public class ArticleList {

	private ArrayList<Article> articles;

	public ArticleList() {

		articles = new ArrayList<>();
		ArticleGetter a = new ArticleGetter("eagles");
		JSONArray arr = a.getArticles();

		// if the JSON request was successful, arr will not be null, and we can loop
		// through the "articles" in arr, parse them using the JSONObjects, and add them
		// to the ArrayList articles
		if(arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				JSONObject new_obj = (JSONObject) arr.get(i);
				articles.add(new Article(new_obj));
			}
		}
	}
	
	public String toString() {
		String s = "Topic: eagles                number of articles: 10 ";
		
		for(Article a: articles)
			s += a.toString() + "\n";
		
		return s;
	}
}
