package cscie97.asn4.ecommerce.collection;

/**
 * @author ssingh
 *Collectible class represents elements that can be contained by a collection. 
 *Collectible is inherited by product and collection. It declares an abstract 
 *method to print the properties of collectible objects to standard output. 
 *This auxiliary method is useful for test driver
 */
public abstract class Collectible {
	/**
	 * print the properties of collectible objects to standard output.
	 */
	public abstract void printInfo(); 
}
