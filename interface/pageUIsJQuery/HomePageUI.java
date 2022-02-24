package pageUIsJQuery;

public class HomePageUI {
	public static final String PAGINATION = "xpath=//a[@class='qgrd-pagination-page-link' and text()='%s']";
	public static final String PAGE_NUMBER_DISPLAY = "xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String TEXTBOX_UNDER_HEADER = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div[@class='qgrd-header-text-wrap']/following-sibling::input[@class='qgrd-header-filter']";
	public static final String DELETED_ICON = "xpath=//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-remove-row-btn']";
	public static final String VALUE_UNDER_TEXTBOX_AFTER_SEARCH = "xpath=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
	public static final String FIELDS_UNDER_HEADER_DATA_TABLE = "id=tblAppendGrid_%s_%s";
	public static final String ADD_BUTTON = "xpath=//button[@title='Append Row']";
}
