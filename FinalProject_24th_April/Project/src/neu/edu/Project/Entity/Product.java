package neu.edu.Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Product {
	private String ItemID;
	private String Title;
	private String GalleryURL;
	private String PrimaryCategoryName;
	private Double ConvertedCurrentPrice;
	
	public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getGalleryURL() {
		return GalleryURL;
	}
	public void setGalleryURL(String galleryURL) {
		GalleryURL = galleryURL;
	}
	public String getPrimaryCategoryName() {
		return PrimaryCategoryName;
	}
	public void setPrimaryCategoryName(String primaryCategoryName) {
		PrimaryCategoryName = primaryCategoryName;
	}
	public Double getConvertedCurrentPrice() {
		return ConvertedCurrentPrice;
	}
	public void setConvertedCurrentPrice(Double convertedCurrentPrice) {
		ConvertedCurrentPrice = convertedCurrentPrice;
	}

	public Product(String itemID, String title, String galleryURL, String primaryCategoryName, Double convertedCurrentPrice)
	{
		super();
		ItemID = itemID;
		GalleryURL = galleryURL;
		PrimaryCategoryName = primaryCategoryName;
		ConvertedCurrentPrice = convertedCurrentPrice;
		Title = title;
	}
	
	public Product() {
		super();
	}
	
	
}
