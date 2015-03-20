package cscie97.asn1.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import cscie97.asn1.knowledge.engine.ImportException;
import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn1.knowledge.engine.QueryEngineException;


/**
 * The TestDriver class implements main() function of the application. Calls 
 * Importer.importFile() to import from N_Triple file and then invokes executeQuery()
 * to run queries from a query file.
 * 
 * @author Siddharth Singh
 *
 */
public class TestDriver {

	/**
	 * Main function of the application
	 *  Calls the Importer.importFile() method, passing in the name of the provided 
	 *  triple file from command line.
	 *  After loading the input triples, the main() invokes the executeQuery() method 
	 *  passing in the provided query file name.
	 *  Catches ImportException,QueryEngineException,FileNotFoundException and
	 *  IOException in the process prints the exception message on standard output
	 *  and exits the process with exit status 1.
	 *  
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2)
		{
			System.out.println("Please specify N_Triple file and query file");
			System.exit(1);
		}
		try
		{
			Importer importer = new Importer();
			importer.importTripleFile(args[0]);
			QueryEngine qe = new QueryEngine();
			qe.executeQueryFile(args[1]);
		}
		catch(ImportException ie)
		{
			ie.printStackTrace();
			System.out.println(ie);
			System.exit(1);
		}
		catch(QueryEngineException qe)
		{
			qe.printStackTrace();
			System.out.println(qe);
			System.exit(1);
		}
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
			System.out.println(fne);
			System.exit(1);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.out.println(ioe);
			System.exit(1);
		}
		
	}

}
