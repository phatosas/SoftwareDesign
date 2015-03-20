package cscie97.asn1.knowledge.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The KnowledgeGraph class manages nodes, predicates, triples and query map.
 * It manages node and predicate objects such that their instances
 * are created only if corresponding identifiers are unique. Triple contains the 
 * references to node and predicate instances. Generates all of the eight permutations 
 * of a triple object. Stores each permutation in query map and maps it to the 
 * corresponding triple objects. This data structure forms the basis of a query
 * in N_Triple format. 
 *
 * @author Siddharth Singh
 *
 */
public class KnowledgeGraph {
	
	private Map<String,Node> nodeMap;
	private Map<String,Predicate> predicateMap;
	private Map<String,Triple> tripleMap;
	private Map<String,Set<Triple>> queryMapSet;
	
	private static KnowledgeGraph instance = null;
	
	/**
	 * KnowledgeGraph constructor kept private in order to create Singleton.
	 * Creates instances of nodeMap, predicateMap, tripleMap and queryMapset.
	 */
	private KnowledgeGraph() 
	{
		nodeMap = new HashMap<String,Node>();
		predicateMap = new HashMap<String,Predicate>();
		tripleMap = new HashMap<String,Triple>();
		queryMapSet = new HashMap<String,Set<Triple>>();
	}
	
	/**
	 * The public static instance() method of the Singleton class.
	 * 
	 * @return static singleton object of KnowledgeGraph
	 */
	public static KnowledgeGraph getInstance()
	{
		if(instance == null) 
		{
			instance = new KnowledgeGraph();
		}
      	return instance;
	}
	
	/**
	 * Creates a new node for a subject/object identifier if it does not exist in
	 * nodeMap and inserts it in the nodeMap. Does nothing if identifier 
	 * already exists in the map. Key is stored in upper case so that search is
	 * case insensitive. 
	 * 
	 * @param id String identifier for a subject or object.
	 */
	public void updateNodeMap(String id)
	{
		String key = id.toUpperCase();
		if(!nodeMap.containsKey(key))
		{
			Node node = new Node(id);
			nodeMap.put(key,node);
		}
	}
	
	/**
	 * Creates a new predicate if predicate's identifier does not exist in
	 * predicateMap and inserts it in the predicateMap. Does nothing if identifier 
	 * already exists in the map. Key is stored in upper case so that search is
	 * case insensitive. 
	 * 
	 * @param id String identifier for a predicate.
	 */
	public void updatePredicateMap(String id)
	{
		String key = id.toUpperCase();
		if(!nodeMap.containsKey(key))
		{
			Predicate predicate = new Predicate(id);
			predicateMap.put(key,predicate);
		}
	}
	
	/**
	 * Generates all of the eight permutations of a triple object. 
	 * Stores each permutation in query map and maps it to the corresponding
	 * triple object. each permutation of the query can correspond to multiple
	 * triple objects so each query permutation is mapped to set of triple objects.
	 * If permutation already exist in the map then the triple object is added to the
	 * corresponding set otherwise creates a new set and adds the triple object to
	 * the set.
	 * 
	 * @param triple String identifier for a predicate.
	 */
	private void updateQueryMap(Triple triple)
	{
		int bitmask = 1;
		for(int i = 0; i < 8; ++i)
		{
			String sub = ((i & bitmask) == 0) ? "?" : triple.getSubject();
			String pred = ((i & bitmask << 1) == 0) ? "?" : triple.getPredicate();
			String obj = ((i & bitmask << 2) == 0) ? "?" : triple.getObject();
			String query = sub + " " + pred + " " + obj;
			String key = query.toUpperCase();
			if(queryMapSet.containsKey(key))
			{
				Set<Triple> tripleSet = queryMapSet.get(key);
				tripleSet.add(triple);
			}
			else
			{
				Set<Triple> tripleSet = new HashSet<Triple>();
				tripleSet.add(triple);
				queryMapSet.put(key, tripleSet);
			}
		}
	}
	
	/**
	 * Searches node identifier in the nodeMap, Converts id to upper case 
	 * before searching in order to make search operation case insensitive.
	 * 
	 * @param id String identifier for the node.
	 * @return Corresponding node from nodeMap, NULL if id does not exist.
	 */
	public Node getNode(String id)
	{
		id = id.toUpperCase();
		return nodeMap.get(id);
	}
	
	/**
	 * Searches predicate identifier in the predicateMap, Converts id to upper case 
	 * before searching in order to make search operation case insensitive.
	 * 
	 * @param id String identifier for the predicate.
	 * @return Corresponding node from predicateMap, NULL if id does not exist.
	 */
	public Predicate getPredicate(String id)
	{
		id = id.toUpperCase();
		return predicateMap.get(id);
	}
	
	/**
	 * Imports list of triple objects to knowledge graph. Add triple object to
	 * knowledge graph only if does not exist already in the graph. Also updates
	 * query map for each new triple object
	 * 
	 * @param tripleList List of triple objects to be imported
	 */
	public void importTriples(List<Triple> tripleList)
	{
		for (Triple triple : tripleList) {
			if(!tripleMap.containsKey(triple.getIdentifier())){
				tripleMap.put(triple.getIdentifier(), triple);
				updateQueryMap(triple);
			}
		}
		
	}
	
	/**
	 * Searches a query triple in the knowledge graph and returns the 
	 * set of search results. Converts query string to upper case 
	 * before searching in order to make search operation case insensitive.
	 * 
	 * @param query the query triple object.
	 * @return Corresponding set of triple objects in the knowledge graph.
	 */
	public Set<Triple> executeQuery(Triple query)
	{
		Set<Triple> resultSet = null;
		String id = query.getIdentifier();
		String key = id.toUpperCase();
		if(queryMapSet.containsKey(key))
		{
			resultSet = queryMapSet.get(key);
		}
		return resultSet;
	}
}
