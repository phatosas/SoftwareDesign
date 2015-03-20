package cscie97.asn1.knowledge.engine;

/**
 * The Node class represents subject/object portion of the Triple class.
 *
 * @author Siddharth Singh
 *
 */
public class Node {
	//non-mutable Node identifier
	private final String identifier;
	
	/**
	 * Node constructor
	 * @param id Node identifier
	 */
	Node(String id)
	{
		identifier = id;
	}
	
	/**
	 * 
	 * @return Node identifier
	 */
	public String getIdentifier()
	{
		return identifier;
	}
}
