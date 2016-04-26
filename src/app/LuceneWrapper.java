package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class LuceneWrapper {

	Directory directory;
	Indexer indexer;
	Searcher searcher;
	
	public LuceneWrapper() throws IOException{
		directory = new RAMDirectory();

		Indexer indexer = new Indexer(directory);
		indexer.indexDocuments(Paths.get("./files"));

		searcher = new Searcher();
		searcher.startSearcher(directory);
	}
	
	public void search() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter query:");
        String querystring = br.readLine();
        
		do{
	        querystring = querystring.toLowerCase();
			searcher.search(querystring);
        	System.out.println("Enter query:");
	        querystring = br.readLine();
			
        }while(!querystring.equals("q"));
		
		System.out.println("Exiting...");
		
		searcher.stopSearcher();
		directory.close();
	}
	
}
