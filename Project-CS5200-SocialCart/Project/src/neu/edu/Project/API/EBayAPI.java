package neu.edu.Project.API;

import neu.edu.Project.Entity.*;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
			
				JSONObject object = (JSONObject) parser.parse(json);
				JSONArray Items = (JSONArray) object.get("Item");
				
				if(Items != null) {
				Integer len = Items.size();
				
					for (int i = 0; i < len; i++ ) {
						JSONObject it = (JSONObject) Items.get(i);
						Product product = new Product();
						product.ItemID = it.get("ItemID").toString();
						product.Title = it.get("Title").toString();
						product.GalleryURL = it.get("GalleryURL").toString();
						product.PrimaryCategoryName = it.get("PrimaryCategoryName").toString();
						JSONObject ConvertedCurrentPriceList = (JSONObject) it.get("ConvertedCurrentPrice");
						product.ConvertedCurrentPrice = (Double) ConvertedCurrentPriceList.get("Value");		
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
