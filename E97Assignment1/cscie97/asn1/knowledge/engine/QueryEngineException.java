package cscie97.asn1.knowledge.engine;

/**
 * The class QueryEngineException indicates the exceptional cases
 * while processing the query file.
 * @author Siddharth Singh
 *
 */
public class QueryEngineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lineNum;
	private String lineString;
	private String fileName;
	
	/**
	 *  QueryEngineException constructor to initialize QueryEngineException object.
	 *
	 * @param  lineNum  Line number at which exception occurred.
	 * @param  lineString  String in the line at which exception occurred.
	 * @param  fileName  File name of query file.
	 */
	public QueryEngineException(int lineNum, String lineString, String fileName) {
		super("Parsing Query Excetion: line " + lineNum + " of file " + fileName +
				" in line :[" + lineString + "]");
		this.lineNum = lineNum;
		this.lineString = lineString;
		this.fileName = fileName;
	}
	
	/**
	 *  Overloaded QueryEngineException constructor to initialize 
	 *  QueryEngineException object in case an independent query executed.
	 *
	 * @param  lineString  String in the line at which exception occurred.
	 */
	public QueryEngineException(String lineString)
	{
		super("Parsing Query Excetion: [" + lineString + "]");
		this.lineNum = 0;
		this.lineString = lineString;
		this.fileName = "";
	}
	/**
	 * 
	 * @return Line number at which exception occurred.
	 */
	public int getLineNum() {
		return lineNum;
	}
	
	/**
	 * 
	 * @return String in the line at which exception occurred.
	 */
	public String getLineString() {
		return lineString;
	}
	
	/**
	 * 
	 * @returnFile File name of query file.
	 */
	public String getFileName() {
		return fileName;
	}
}
