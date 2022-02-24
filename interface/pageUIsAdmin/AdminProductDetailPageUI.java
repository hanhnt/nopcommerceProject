package pageUIsAdmin;

public class AdminProductDetailPageUI {
	public static final String PICTURE_INFOR_AFTER_EDIT ="xpath=//a[contains(@href,'%s')]/parent::td/following-sibling::td[@data-columnname='OverrideAltAttribute' and text()='%s']/following-sibling::td[@data-columnname='OverrideTitleAttribute' and text()='%s']";
	public static final String SAVE_BUTTON ="name=save";
	//public static final String DELETE_BUTTON_BY_ALT_VALUE ="xpath=//td[@data-columnname='OverrideAltAttribute' and text()='%s']/following-sibling::td/a[contains(string(),'%s')]";
	public static final String DELETE_BUTTON_LIST ="xpath=//td/a[contains(string(),'Delete')]";
	public static final String UPLOAD_FILE_BUTTON_BY_BLOCK_NAME="xpath=//div[@id='product-%s']//input[@type='file']";
	public static final String IMAGE_UPDATE_SUCCESSFULLY="xpath=//div[@class='picture-container']//img[contains(@src,'%s')]";
	public static final String ALT_TEXTBOX="id=AddPictureModel_OverrideAltAttribute";
	public static final String TITLE_TEXTBOX="id=AddPictureModel_OverrideTitleAttribute";
	public static final String DISPLAY_ORDER_TEXTBOX="xpath=//input[@id='AddPictureModel_DisplayOrder']/preceding-sibling::input";
	public static final String ADD_PRODUCT_PICTURE_BUTTON="id=addProductPicture";
	public static final String TOOL_BUTTON_BY_BLOCK_NAME="xpath=//div[@id='product-%s']//button[@class='btn btn-tool']/i";
	public static final String NO_DATA_TEXT_BY_BLOCK_NAME="xpath=//table[@id='%s-grid']//td[text()='%s']";
	public static final String BLOCK_NAME="xpath=//div[@id='product-%s']";
	public static final String INCREASE_BUTTON_AT_BLOCK_NAME="xpath=//div[@id='product-%s']//span[@class='k-link k-link-increase']";
	
}
