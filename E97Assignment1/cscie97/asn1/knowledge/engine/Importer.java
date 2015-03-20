package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The Importer class is responsible for reading triples from input files 
 * using N­Triple format. The Importer class creates a Triple instance 
 * for each line read from the input file and passes the resulting Triples 
 * to the KnowledgeGraph.importTriples() method. 
 *  
 * @author Siddharth Singh
 *
 */
public class Importer {
	
	/**
	 * Public method for importing triples from N_Triple formatted file into the
	 * 	KnowledgeGraph. 
	 *  Throws FileNotFoundException if N_Triple file does not exist. 
	 *  Throws IOException on error reading the N_Triple file.
	 *  Throws ImportException If a line in the input N_Triple File has more than three tokens
	 *  		or any token is equal to '?' keyword. 
	 *
	 *
	 * @param  fileName  File name of N-Triple formatted file.
	 */
	
	public void importTripleFile(String fileName) throws ImportException,
													FileNotFoundException,IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		List<Triple> tripleList = new ArrayList<Triple>();
		String line;
		int lineNum = 0;
		while ((line = br.readLine()) != null) {
			++lineNum;
			if (line.trim().equals("")){
				continue;
			}
			StringTokenizer st = new StringTokenizer(line);
			int numTokens = st.countTokens();
			if(numTokens != 3){
				br.close();
				throw new ImportException(lineNum,line,fileName);
			}
			int count = 0;
			String[] tokens = new String[3];
			while (st.hasMoreTokens()) {
				String token = st.nextToken().trim();
				if(token.equals("?")){
					br.close();
					throw new ImportException(lineNum,line,fileName);
				}
				tokens[count++] = token;
		     }
			tokens[2] = tokens[2].substring(0, tokens[2].length() - 1);
			KnowledgeGraph.getInstance().updateNodeMap(tokens[0]);
			KnowledgeGraph.getInstance().updatePredicateMap(tokens[1]);
			KnowledgeGraph.getInstance().updateNodeMap(tokens[2]);
			Node object = KnowledgeGraph.getInstance().getNode(tokens[0]);
			Predicate pred = KnowledgeGraph.getInstance().getPredicate(tokens[1]);
			Node subject = KnowledgeGraph.getInstance().getNode(tokens[2]);
			Triple triple = new Triple(object, pred, subject);
			tripleList.add(triple);
		}
		lineNum = 0;
		fileName = "";
		br.close();
		KnowledgeGraph.getInstance().importTriples(tripleList);
	}
}
