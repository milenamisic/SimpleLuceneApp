package app;

public class Main {

	public static void main(String[] args) {

		try {
			
			LuceneWrapper lw = new LuceneWrapper();
			lw.search();
						
		//	String[] searchWords = {"document*", "documen?", "?ocumen*"};
			
	/*		System.out.println("QueryParser:");
			QueryParser parser = new QueryParser("contents", analyzer);
			query = parser.parse("doc* -text");
			ScoreDoc[] hits = isearcher.search(query, 3).scoreDocs;

			for (int i = 0; i < hits.length; i++)
				System.out.println(hits.length);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
