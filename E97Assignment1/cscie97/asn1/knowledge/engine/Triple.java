package cscie97.asn1.knowledge.engine;

/**
 * The Triple class represents a unique Triple (Subject, Predicate, Object) within the
 * KnowledgeGraph. A Triple contains references to Nodes ( subject and object ) and Predicate.
 *
 * @author Siddharth Singh
 *
 */
public class Triple {
	private final Node subject;
	private final Node object;
	private final Predicate predicate;
	
	/**
	 * Triple constructor. Keeps reference of nodes and predicates. 
	 * Nodes and predicates are created only for new identifiers so that 
	 * number of node and predicate instances are minimized ( flyweight pattern ).
	 * 
	 * @param subject node corresponding to subject
	 * @param predicate node corresponding to predicate
	 * @param object node corresponding to object
	 */
	public Triple(Node subject,Predicate predicate,Node object)
	{
		this.subject = subject;
		this.object = object;
		this.predicate = predicate;
	}
	
	/**
	 * 
	 * @return Triple identifier 
	 * ( subject + <space> + predicate + <space> + object)
	 */
	public String getIdentifier()
	{
		String identifier = subject.getIdentifier() + " " + predicate.getIdentifier() + 
							" " + object.getIdentifier();
		return identifier;
	}
	
	/**
	 * 
	 * @return subject identifier 
	 */
	public String getSubject()
	{
		return this.subject.getIdentifier();
	}
	
	/**
	 * 
	 * @return object identifier 
	 */
	public String getObject()
	{
		return this.object.getIdentifier();
	}
	
	/**
	 * 
	 * @return predicate identifier 
	 */
	public String getPredicate()
	{
		return this.predicate.getIdentifier();
	}
}
