package cscie97.asn4.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cscie97.asn4.ecommerce.authentication.AccessDeniedException;
import cscie97.asn4.ecommerce.authentication.AuthenticationException;
import cscie97.asn4.ecommerce.authentication.AuthenticationServiceFactory;
import cscie97.asn4.ecommerce.authentication.IAuthenticationService;
import cscie97.asn4.ecommerce.authentication.InvalidAccessTokenException;
import cscie97.asn4.ecommerce.authentication.PrintInfoVisitor;
import cscie97.asn4.ecommerce.authentication.Visitor;
import cscie97.asn4.ecommerce.collection.CollectionServiceException;
import cscie97.asn4.ecommerce.collection.CollectionServiceFactory;
import cscie97.asn4.ecommerce.collection.ICollectionService;
import cscie97.asn4.ecommerce.product.ContentParameterException;
import cscie97.asn4.ecommerce.product.IProductService;
import cscie97.asn4.ecommerce.product.ProductServiceFactory;

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
	private static void importCountries(String fileName,IProductService productService,String GUID) 
			throws ContentParameterException, FileNotFoundException,IOException, 
			InvalidAccessTokenException, AccessDeniedException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] cntryArr=line.split("(?<!\\\\),\\s*");
			if(cntryArr.length != 3)
				continue;
			String countryCode = cntryArr[0].trim().replace("\\,", ",");
			String countryName = cntryArr[1].trim().replace("\\,", ",");
			String exportStatus = cntryArr[2].trim().replace("\\,", ",");
			productService.addCountry(countryCode, countryName, exportStatus, GUID);
		}
		br.close();
	}
	private static void importDevices(String fileName,IProductService productService,String GUID) 
			throws ContentParameterException, FileNotFoundException,IOException, 
			InvalidAccessTokenException, AccessDeniedException {
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
			productService.addDevice(deviceId, deviceName, mfgName, GUID);
		}
		br.close();
	}
	private static void importContents(String fileName,IProductService productService,String GUID) 
			throws ContentParameterException, FileNotFoundException,
			IOException, NumberFormatException, InvalidAccessTokenException, 
			AccessDeniedException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] prodArr=line.split("(?<!\\\\),\\s*");
			if(prodArr.length < 12 || prodArr.length > 13)
				continue;
			String contentType = prodArr[0].trim().replace("\\,", ",");
			String contentId = prodArr[1].trim().replace("\\,", ",");
			String contentName = prodArr[2].trim().replace("\\,", ",");
			String contentDesc = prodArr[3].trim().replace("\\,", ",");
			
			String author = prodArr[4].trim().replace("\\,", ",");
			String rating = prodArr[5].trim().replace("\\,", ",");
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
					contentSize, GUID);
		}
		br.close();
	}
	public static void importCollections(String fileName,String GUID)
			throws CollectionServiceException, FileNotFoundException,IOException, 
			InvalidAccessTokenException, AccessDeniedException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] criteriaArr = line.split(",");
			if(criteriaArr[0].trim().compareToIgnoreCase("define_collection") == 0){
				String type = criteriaArr[1].trim();
				String id = criteriaArr[2].trim();
				String name = criteriaArr[3].trim();
				String desc = criteriaArr[4].trim();
				CollectionServiceFactory.getCollectionService().create(type, id, name, desc, GUID);
			}
		}
		br.close();
	}
	public static void authenticationTest(String fileName,String countryFile,
			String deviceFile,String prodFile,String collFile)
			throws FileNotFoundException,IOException, InvalidAccessTokenException, 
			AccessDeniedException, AuthenticationException, 
			ContentParameterException, CollectionServiceException{
		IAuthenticationService authService = AuthenticationServiceFactory.getAuthenticationService();
		String accessToken = authService.login("admin","admin");
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty() || line.charAt(0) == '#')
				continue;
			String[] criteriaArr = line.split(",");
			if(criteriaArr[0].trim().compareToIgnoreCase("define_service") == 0){
				String id = criteriaArr[1].trim();
				String name = criteriaArr[2].trim();
				String desc = criteriaArr[3].trim();
				authService.createService(id, name, desc, accessToken);
			}
			else if(criteriaArr[0].trim().compareToIgnoreCase("define_permission") == 0){
				String serviceId = criteriaArr[1].trim();
				String permissionId = criteriaArr[2].trim();
				String name = criteriaArr[3].trim();
				String desc = criteriaArr[4].trim();
				authService.createPermission(serviceId, permissionId, name, desc, accessToken);
			}
			else if(criteriaArr[0].trim().compareToIgnoreCase("define_role") == 0){
				String id = criteriaArr[1].trim();
				String name = criteriaArr[2].trim();
				String desc = criteriaArr[3].trim();
				authService.createRole(id, name, desc, accessToken);
			}
			else if(criteriaArr[0].trim().compareToIgnoreCase("add_entitlement_to_role") == 0){
				String roleId = criteriaArr[1].trim();
				String entitlementId = criteriaArr[2].trim();
				authService.addEntitlementToRole(roleId, entitlementId, accessToken);
			}
			else if(criteriaArr[0].trim().compareToIgnoreCase("create_user") == 0){
				String id = criteriaArr[1].trim();
				String name = criteriaArr[2].trim();
				authService.createUser(id, name, accessToken);
			}
			else if(criteriaArr[0].trim().compareToIgnoreCase("add_credential") == 0){
				String id = criteriaArr[1].trim();
				String userName = criteriaArr[2].trim();
				String password = criteriaArr[3].trim();
				authService.addCredentialToUser(id, userName, password, accessToken);
			}
			else if(criteriaArr[0].trim().compareToIgnoreCase("add_entitlement_to_user") == 0){
				String userId = criteriaArr[1].trim();
				String entitlementId = criteriaArr[2].trim();
				authService.addEntitlementIdToUser(userId, entitlementId, accessToken);
			}
		}
		br.close();
		String productApiToken = authService.login("sam2","secret2");
		String productDevToken = authService.login("joe","1234");
		String collectionApiToken = authService.login("lucy","4567");
		String authenticationApiToken = authService.login("jill","1234567");
		Visitor visitor = new PrintInfoVisitor();
		authService.printAuthenticationInfo(visitor);
		ICollectionService collService = CollectionServiceFactory.getCollectionService();
		ProductServiceFactory psf = new ProductServiceFactory();
		IProductService prodService = psf.getProductService();
		importCountries(countryFile, psf.getProductService(), productApiToken);
		importDevices(deviceFile, psf.getProductService(), productApiToken);
		importContents(prodFile, psf.getProductService(), productDevToken);
		importCollections(collFile,collectionApiToken);
		
		try{
			authService.login("sam2","wrong_password");
		}
		catch (AuthenticationException e) {
			System.out.println(e);
		}
		try{
			authService.createUser("collection_search", "Collection Search", productApiToken);
		}
		catch (AccessDeniedException e) {
			System.out.println(e);
		}
		try{
			collService.create("dynamic", "cricket_collection", "Cricket", "All things Cricket", authenticationApiToken);
		}
		catch (AccessDeniedException e) {
			System.out.println(e);
		}
		try{
			prodService.addCountry("AT", "AUSTRIA", "open", collectionApiToken);
		}
		catch (AccessDeniedException e) {
			System.out.println(e);
		}
		authService.logout(productApiToken);
		try{
			prodService.addCountry("CN", "CHINA", "open", productApiToken);
		}
		catch (InvalidAccessTokenException e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		if (args.length < 5)
		{
			System.out.println("Please specify authentication,country,device,product and collection csv file");
			System.exit(1);
		}
		try {
			authenticationTest(args[0],args[1],args[2],args[3],args[4]);	
		} catch (ContentParameterException cpe) {
			System.out.println(cpe);
		}
		catch (CollectionServiceException cse) {
			System.out.println(cse);
		}
		catch (FileNotFoundException fne) {
			System.out.println(fne);
		}
		catch (InvalidAccessTokenException e) {
			System.out.println(e);
		} catch (AccessDeniedException e) {
			System.out.println(e);
		} catch (AuthenticationException e) {
			System.out.println(e);
		}
		catch (IOException ioe) {
			System.out.println(ioe);
		}  
	}
}
