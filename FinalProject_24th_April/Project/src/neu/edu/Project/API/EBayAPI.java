package neu.edu.Project.API;

import neu.edu.Project.Entity.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EBayAPI {
	private String FINDBYKEYWORD = "http://open.api.ebay.com/shopping?appid=studentf1-98a2-4f06-8377-530d81ed27b&version=517&siteid=0&callname=FindItems&QueryKeywords={{KEYWORD}}&responseencoding=JSON&MaxEntries=10";
	public List<Product> getProductsforKeyword(String keyword) {
		keyword = keyword.replace(" ", "+");
		String urlStr = FINDBYKEYWORD.replace("{{KEYWORD}}", keyword);
		List<Product> products = new ArrayList<Product>();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			String json = "";
			while((line = buffer.readLine()) != null) {
				json += line;
			}
			
			JSONParser parser = new JSONParser();
		try {
			
				JSONObject root = (JSONObject) parser.parse(json);
				JSONArray ItemArray = (JSONArray) root.get("Item");
				
				if(ItemArray != null) {
				Integer length = ItemArray.size();
				
					for (int i = 0; i < length; i++ ) {
						JSONObject FirstItem = (JSONObject) ItemArray.get(i);
						String ItemID = FirstItem.get("ItemID").toString();
						String Title = FirstItem.get("Title").toString();
						String GalleryURL = FirstItem.get("GalleryURL").toString();
						String PrimaryCategoryName = FirstItem.get("PrimaryCategoryName").toString();
						JSONObject ConvertedCurrentPriceList = (JSONObject) FirstItem.get("ConvertedCurrentPrice");
						Double ConvertedCurrentPrice = (Double) ConvertedCurrentPriceList.get("Value");		
						Product product = new Product(ItemID, Title, GalleryURL, PrimaryCategoryName, ConvertedCurrentPrice);
						products.add(product);					
					}
				}
				return products;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}	

}
