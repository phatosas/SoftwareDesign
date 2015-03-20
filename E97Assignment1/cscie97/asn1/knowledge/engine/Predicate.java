package cscie97.asn1.knowledge.engine;

/**
 * The Predicate class represents predicate portion of the Triple class.
 *
 * @author Siddharth Singh
 *
 */
public class Predicate {
	//non-mutable Predicate identifier
	private final String identifier;
	
	/**
	 * Predicate constructor
	 * @param id Node identifier
	 */
	Predicate(String id)
	{
		identifier = id;
	}
	
	/**
	 * 
	 * @return Predicate identifier
	 */
	public String getIdentifier()
	{
		return identifier;
	}
}
