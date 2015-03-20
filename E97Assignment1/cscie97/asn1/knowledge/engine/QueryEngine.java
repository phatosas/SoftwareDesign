package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * The QueryEngine class supports the execution of Knowledge Graph queries.
 * Queries are provided in N_Triple format which it searches in the knowledges graph and 
 * prints results on standard output.
 * 
 * @author Siddharth Singh
 *
 */
public class QueryEngine {
	private int lineNum;
	private String fileName;
	
	/**
	 * QueryEngine constructor. Initializes line number to zero. 
	 * line number keeps track of number of lined read from query file. 
	 */
	public QueryEngine()
	{
		lineNum = 0;
	}
	
	/**
	 *  Public method for executing a query from the knowledge graph
	 * 
	 *  Throws QueryEngineException on error processing the query. 
	 *
	 * @param  query  The query string.
	 */
	public void executeQuery(String query) throws QueryEngineException
	{
		StringTokenizer st = new StringTokenizer(query);
		int count = 0;
		String[] tokens = new String[3];
		int numTokens = st.countTokens();
		if(numTokens != 3){
			if(lineNum == 0)
				throw new QueryEngineException(query);
			throw new QueryEngineException(lineNum,query,fileName);
		}
		while (st.hasMoreTokens()) {
			tokens[count++] = st.nextToken().trim();
	     }
		tokens[2] = tokens[2].substring(0, tokens[2].length() - 1);
		Triple triple = new Triple(new Node(tokens[0]), new Predicate(tokens[1]), new Node(tokens[2]));
		Set<Triple> resultSet = KnowledgeGraph.getInstance().executeQuery(triple);
		System.out.println();
		System.out.println();
		System.out.println(query);
		if(resultSet == null){
			System.out.println("<null>");
		}
		else{
			for(Triple triple2 : resultSet) {
				System.out.println(triple2.getIdentifier() + ".");
			}
		}
	}
	
	/**
	 *  Public method for executing queries in a query file from the knowledge graph
	 * 
	 *  Throws FileNotFoundException if query file does not exist. 
	 *  Throws IOException on error reading the query file.
	 *  Throws QueryEngineException on error processing the input query file. 
	 *
	 * @param  fileName  File name of N-Triple formatted file.
	 */
	public void executeQueryFile(String fileName) throws QueryEngineException,
												FileNotFoundException,IOException
	{
		this.fileName = fileName;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			++lineNum;
			if (line.trim().equals("")){
				continue;
			}
			executeQuery(line);
		}
		this.fileName = "";
		lineNum = 0;
		br.close();
	}
}
