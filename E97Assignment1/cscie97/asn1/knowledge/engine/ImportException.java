package cscie97.asn1.knowledge.engine;

/**
 * The class ImportException indicates the exceptional cases
 * while processing the N_Triple format file.
 * @author Siddharth Singh
 *
 */
public class ImportException extends Exception {
	private static final long serialVersionUID = 1L;
	private int lineNum;
	private String lineString;
	private String fileName;
	
	/**
	 *  ImportException constructor to initialize ImportException object.
	 *
	 * @param  lineNum  Line number at which exception occurred.
	 * @param  lineString  String in the line at which exception occurred.
	 * @param  fileName  File name of N-Triple formatted file.
	 */
	public ImportException(int lineNum, String lineString, String fileName) {
		super("Import Excetion: line " + lineNum + " of file " + fileName +
				" in line :[" + lineString + "]");
		this.lineNum = lineNum;
		this.lineString = lineString;
		this.fileName = fileName;
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
	 * @return File name of N-Triple formatted file.
	 */
	public String getFileName() {
		return fileName;
	}
	
}
