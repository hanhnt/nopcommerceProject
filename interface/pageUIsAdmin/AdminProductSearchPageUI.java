package pageUIsAdmin;

public class AdminProductSearchPageUI {
	public static final String SUCCESS_MESSAGE="xpath=//div[contains(@class,'alert-success') and contains(string(),'%s')]";
	public static final String SEARCH_TEXTBOX="id=SearchProductName";
	public static final String SEARCH_BUTTON="id=search-products";
	public static final String SEARCH_RESULT="xpath=//td[text()='%s']/preceding-sibling::td/img[contains(@src,'%s')]";
	public static final String EDIT_BUTTON_BY_PRODUCT_NAME="xpath=//td[text()='%s']/following-sibling::td/a";
	public static final String SEARCH_AREA="xpath=//div[@class='icon-collapse']/i";
}
