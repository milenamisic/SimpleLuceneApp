package app;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;

public class Searcher {

	DirectoryReader reader;
	IndexSearcher indexSearcher;
	Query query;

	public Searcher(){}
	
	public void startSearcher(Directory directory) throws IOException{
		reader = DirectoryReader.open(directory);
		indexSearcher = new IndexSearcher(reader);
	}
	
	public void stopSearcher() throws IOException{
		reader.close();
	}
	
	public void wildcardSearch(String querystring) throws IOException{
		
		System.out.println("Starting Wildcard Search...");
		
		String[] searchWords = querystring.split(" ");

		for(int i = 0; i < searchWords.length; i++){
			query = new WildcardQuery(new Term("contents", searchWords[i]));
			ScoreDoc[] results = indexSearcher.search(query, 3).scoreDocs;
			System.out.println("Word containing '" + searchWords[i] + "' was found in:");
			for(ScoreDoc sd : results){
				System.out.println(sd.toString());
			}
		}

		System.out.println("Exiting Wildcard Search...");
	}
	
	public void fuzzySearch(String querystring) throws IOException{
		
		System.out.println("Starting Fuzzy Search...");
		
		String[] searchWords = querystring.split(" ");
		
		for(int i = 0; i < searchWords.length; i++){
			query = new FuzzyQuery(new Term("contents", searchWords[i]));
			ScoreDoc[] results = indexSearcher.search(query, 3).scoreDocs;
			System.out.println("Words similar to '" + searchWords[i] + "' were found in:");
			for(ScoreDoc sd : results){
				System.out.println(sd.toString());
			}
		}

		System.out.println("Exiting Fuzzy Search...");
	}
	
	public void termSearch(String querystring) throws IOException{
		
		System.out.println("Starting Term Search...");
		
		String[] searchWords = querystring.split(" ");
		
		for(int i = 0; i < searchWords.length; i++){
			query = new TermQuery(new Term("contents", searchWords[i]));
			ScoreDoc[] results = indexSearcher.search(query, 3).scoreDocs;
			System.out.println("Word '" + searchWords[i] + "' was found in:");
			for(ScoreDoc sd : results){
				System.out.println(sd.toString());
			}
		}

		System.out.println("Exiting Term Search...");
		
	}
	
	public void search(String querystring) throws IOException{
		
		if(querystring.contains("~"))
			fuzzySearch(querystring);
		else if(querystring.contains("*") || querystring.contains("?"))
			wildcardSearch(querystring);
		else
			termSearch(querystring);
	}
	
}
