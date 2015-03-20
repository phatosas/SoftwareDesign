package cscie97.asn3.ecommerce.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ssingh
 *Iterator class for iterating collections.
 */
public class CollectionIterator {
	int currIndex;
	List<Collectible> collectibles;
	Map<Collection,Boolean> visited;
	Collection parentColl;
	
	/**
	 * @param parentColl
	 * Constructor to create iterator for collection tree rooted at parentColl 
	 */
	public CollectionIterator(Collection parentColl) {
		super();
		currIndex = 0;
		this.parentColl = parentColl;
		collectibles= new ArrayList<Collectible>();
		visited = new HashMap<Collection, Boolean>();
		collectionIterator_DFS(parentColl);
	}
	/**
	 * @param parent
	 * Depth first search traversal of collection tree
	 */
	private void collectionIterator_DFS(Collection parent){
		collectibles.add(parent);
		visited.put(parent, true);
		List<ProductProxy> prods =  parent.getContainedProducts();
		for(Collectible prod:prods){
			collectibles.add(prod);
		}
		List<String> childIds = parent.getChildIds();
		for(String collId:childIds){
			Collection coll = CollectionService.getInstance().getCollection(collId);
			if(!visited.containsKey(coll)){
				collectionIterator_DFS(coll);
			}
		}
	}
	/**
	 * Goes to next item in the iterator.
	 */
	public void next(){	
		++currIndex;
	}
	/**
	 * @return - Returns true if iterator has more items to iterate otherwise false.
	 */
	public Boolean hasNext(){
		return (currIndex < collectibles.size());
	}
	/**
	 * @return - current item in the iterator
	 */
	public Collectible currentItem(){
		return collectibles.get(currIndex);
	}
}
