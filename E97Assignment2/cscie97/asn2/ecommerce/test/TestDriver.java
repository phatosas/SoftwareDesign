package cscie97.asn2.ecommerce.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cscie97.asn2.ecommerce.product.Content;
import cscie97.asn2.ecommerce.product.ContentParameterException;
import cscie97.asn2.ecommerce.product.IProductService;
import cscie97.asn2.ecommerce.product.ProductServiceFactory;

public class TestDriver {
	private static List<String> getList(String properties,String delim){
		List<String> results = new ArrayList<String>();
		String[] propertyList = properties.split(delim);
		for(String property : propertyList){
			property = property.trim();
			if(!property.isEmpty())
				results.add(property);
		}
		return results;
	}
	private static void printContentList(String query,List<Content> results){
		System.out.println("Query = [ " + query + " ]");
		if(results.isEmpty()){
			System.out.println("No Results Found");
			System.out.println();
			return;
		}
		System.out.println("Results");
		System.out.println("------------------------------");
		for(Content content : results){
			System.out.println("Id = " + content.getContentId());
			System.out.println("Name = " + content.getContentName());
			System.out.println("Description = " + content.getContentDescription());
			System.out.println("Author = " + content.getContentAuthor());
			System.out.println("Price = " + content.getContentPrice());
			System.out.println("Rating = " + content.getContentRating());
			System.out.println("Countries = " + content.getContentCountryNames());
			System.out.println("Devices = " + content.getContentDeviceNames());
			System.out.println("Categories = " + content.getContentLanguages());
			System.out.println("Image URL = " + content.getContentImageURL());
			System.out.println();
		}
	}
	/**
	 * Imports country from country csv file and calling addCountry of product service API
	 * @param fileName country csv file name
	 * @param productService product service API
	 * @throws ContentParameterException invalid parameter
	 * @throws FileNotFoundException csv file not found
	 * @throws IOException error processing csv file
	 */
	private static void importCountries(String fileName,IProductService productService) 
			throws ContentParameterException, FileNotFoundException,IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] cntryArr=line.split(",");
			if(cntryArr.length != 3)
				continue;
			String countryCode = cntryArr[0].trim();
			String countryName = cntryArr[1].trim();
			String exportStatus = cntryArr[2].trim();
			productService.addCountry(countryCode, countryName, exportStatus, null);
		}
		br.close();
	}
	/**
	 * Imports devices from device csv file and calling addDevice of product service API
	 * @param fileName device csv file name
	 * @param productService product service API
	 * @throws ContentParameterException invalid parameter
	 * @throws FileNotFoundException csv file not found
	 * @throws IOException error processing csv file
	 */
	private static void importDevices(String fileName,IProductService productService) 
			throws ContentParameterException, FileNotFoundException,IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] deviceArr=line.split(",");
			if(deviceArr.length != 3)
				continue;
			String deviceId = deviceArr[0].trim();
			String deviceName = deviceArr[1].trim();
			String mfgName = deviceArr[2].trim();
			productService.addDevice(deviceId, deviceName, mfgName, "");
		}
		br.close();
	}
	/**
	 * Imports contents from content csv file and calling addContent of product service API
	 * @param fileName content csv file name
	 * @param productService product service API
	 * @throws ContentParameterException invalid parameter
	 * @throws FileNotFoundException csv file not found
	 * @throws IOException error processing csv file
	 */
	private static void importContents(String fileName,IProductService productService) 
			throws ContentParameterException, FileNotFoundException,IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] prodArr=line.split(",");
			if(prodArr.length < 12 || prodArr.length > 13)
				continue;
			String contentType = prodArr[0].trim();
			String contentId = prodArr[1].trim();
			String contentName = prodArr[2].trim();
			String contentDesc = prodArr[3].trim();
			
			String author = prodArr[4].trim();
			String rating = prodArr[5].trim();
			List<String> categories = getList(prodArr[6].trim(),"\\|");//list
			
			List<String> countries = getList(prodArr[7].trim(),"\\|");//list
			List<String> devices = getList(prodArr[8].trim(),"\\|");//list
			String price = prodArr[9].trim();
			
			List<String> languages = getList(prodArr[10].trim(),"\\|");//list
			String imageUrl = prodArr[11].trim();
			String appSize = null;
			int contentSize = 0;
			if(prodArr.length == 13){
				appSize = prodArr[12].trim();
				contentSize = Integer.parseInt(appSize);
			}
			
			productService.addContent(contentId, contentType, contentName, contentDesc, 
					author, Integer.parseInt(rating), categories, countries, 
					devices, Float.parseFloat(price), languages, imageUrl, 
					contentSize, null);
		}
		br.close();
	}
	/**
	 * Searches contents by calling searchContent of product service API and 
	 * reading query from query csv file
	 * @param fileName query csv file name
	 * @param productService product service API
	 * @throws ContentParameterException invalid parameter
	 * @throws FileNotFoundException csv file not found
	 * @throws IOException error processing csv file
	 */
	private static void searchContents(String fileName,IProductService productService) 
			throws ContentParameterException, FileNotFoundException,IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] criteriaArr=line.split(",");
			if(criteriaArr.length < 7 || criteriaArr.length > 8)
				continue;
			List<String> contentCategories = getList(criteriaArr[0].trim(),"\\|");//list
			String searchText = criteriaArr[1].trim();
			int minRating = -1;
			if(!criteriaArr[2].trim().isEmpty())
				minRating = Integer.parseInt(criteriaArr[2].trim());
			float maxPrice = -1;
			if(!criteriaArr[3].trim().isEmpty())
				maxPrice = Float.parseFloat(criteriaArr[3].trim());
			
			String countryCode = criteriaArr[4].trim();
			List<String> contentLanguages = getList(criteriaArr[5].trim(),"\\|");//list
			String deviceId = criteriaArr[6].trim();
			List<String> contentType = new ArrayList<>();
			if(criteriaArr.length == 8)
				contentType = getList(criteriaArr[7].trim(),"\\|");//list
			
			List<Content> results = productService.searchContent(contentCategories, searchText, minRating, maxPrice, 
					countryCode, contentLanguages, deviceId, contentType);
			printContentList(line,results);
		}
		br.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
if (args.length < 4)
		{
			System.out.println("Please specify country,device,content and query file");
			System.exit(1);
		}

		ProductServiceFactory psf = new ProductServiceFactory();
		try {
			importCountries(args[0],psf.getProductService());
			importDevices(args[1],psf.getProductService());
			importContents(args[2],psf.getProductService());
			searchContents(args[3],psf.getProductService());
		} catch (ContentParameterException cpe) {
			// TODO Auto-generated catch block
			System.out.println(cpe);
			cpe.printStackTrace();
		}
		catch (FileNotFoundException fne) {
			// TODO Auto-generated catch block
			System.out.println(fne);
			fne.printStackTrace();
		}
		catch (IOException ioe) {
			// TODO Auto-generated catch block
			System.out.println(ioe);
			ioe.printStackTrace();
		}
	}
}
