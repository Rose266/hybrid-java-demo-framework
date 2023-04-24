package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	/* WebBrowser */

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresent(WebDriver driver) {
		return new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresent(driver).accept();
	}

	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresent(driver).dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String keysToSend) {
		waitForAlertPresent(driver).sendKeys(keysToSend);
	}

	public String getTextInAlert(WebDriver driver) {
		return waitForAlertPresent(driver).getText();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowPageIDs = driver.getWindowHandles();
		for (String id : allWindowPageIDs) {
			String currentPageTitle = getPageTitle(driver);
			driver.switchTo().window(id);
			if (currentPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeOtherWindowsWithoutParent(WebDriver driver, String parentId) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) {
			if (!id.equals(parentId)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().defaultContent();
		}
	}

	/* WebElement */
	public By byXpathlocatorType(String locatorType) {
		return By.xpath(locatorType);
	}

	protected By getByLocator(String locator) {
		By by = null;
		if (locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
			locator = locator.substring(6);
			by = By.xpath(locator);
		} else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
			locator = locator.substring(4);
			by = By.cssSelector(locator);
		} else if (locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
			locator = locator.substring(5);
			by = By.name(locator);
		} else if (locator.startsWith("id=") || locator.startsWith("Id=") || locator.startsWith("ID=")) {
			locator = locator.substring(3);
			by = By.id(locator);
		} else if (locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLASS=")) {
			locator = locator.substring(6);
			by = By.className(locator);
		} else if (locator.startsWith("tag=") || locator.startsWith("Tag=") || locator.startsWith("TAG=")) {
			locator = locator.substring(4);
			by = By.tagName(locator);
		} else if (locator.startsWith("link=") || locator.startsWith("Link=") || locator.startsWith("LINK=")) {
			locator = locator.substring(5);
			by = By.linkText(locator);
		} else if (locator.startsWith("parLink=") || locator.startsWith("Parlink=") || locator.startsWith("PARLINK=")) {
			locator = locator.substring(8);
			by = By.partialLinkText(locator);
		}
		return by;
	}

	protected String getRestParameter(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	protected WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	protected WebElement getWebElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByLocator(getRestParameter(locator, params)));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locator, String... params) {
		return driver.findElements(getByLocator(getRestParameter(locator, params)));
	}

	protected int getListElementSize(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}

	protected String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	protected String getElementText(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, locator, params).getText();
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeValue) {
		return getWebElement(driver, locator).getAttribute(attributeValue);
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeValue, String... params) {
		return getWebElement(driver, locator, params).getAttribute(attributeValue);
	}

	protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	protected String getHexColorValueFromRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected String getSlectedOptionInDropdown(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
	}

	protected String getSlectedOptionInDropdown(WebDriver driver, String locator, String... params) {
		return new Select(getWebElement(driver, locator, params)).getFirstSelectedOption().getText();
	}

	public String getElementValueByJs(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver)
				.executeScript("return $(document.eavaluate(\"" + byXpathlocatorType(locator)
						+ "\",document,null,XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).value");
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentlocatorType, String childlocatorType,
			String expectedItem) {
		clickToElement(driver, parentlocatorType);
		List<WebElement> allItems = getListWebElement(driver, childlocatorType);
		waitForElementPresent(driver, childlocatorType);
		for (WebElement option : allItems) {
			if (option.getText().equals(expectedItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", option);
				sleepInSeconds(2);
				option.click();
				sleepInSeconds(1);
				break;
			}
		}
	}

	public void selectItemInDropDown(WebDriver driver, String locator, String expectedOption) {
		new Select(getWebElement(driver, locator)).selectByVisibleText(expectedOption);
	}

	public void selectItemInDropDown(WebDriver driver, String locator, String expectedOption, String... params) {
		new Select(getWebElement(driver, locator, params)).selectByVisibleText(expectedOption);
	}

	protected void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	protected void clickToElement(WebDriver driver, String locator, String... params) {
		getWebElement(driver, locator, params).click();
	}

	protected void sendKeysToElement(WebDriver driver, String locator, String valueToSendKeys) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(valueToSendKeys);
	}

	protected void sendKeysToElement(WebDriver driver, String locator, String valueToSendKeys, String... params) {
		getWebElement(driver, locator, params).clear();
		getWebElement(driver, locator, params).sendKeys(valueToSendKeys);
	}

	protected void checkToCheckoxRadio(WebDriver driver, String locator) {
		if (!getWebElement(driver, locator).isSelected()) {
			clickToElement(driver, locator);
		}
	}

	protected void checkToCheckboxRadio(WebDriver driver, String locator, String... params) {
		if (!getWebElement(driver, locator, params).isSelected()) {
			clickToElement(driver, locator, params);
		}
	}

	protected void uncheckToCheckbox(WebDriver driver, String locator) {
		if (getWebElement(driver, locator).isSelected()) {
			clickToElement(driver, locator);
		}
	}

	protected void uncheckToCheckbox(WebDriver driver, String locator, String... params) {
		if (getWebElement(driver, locator, params).isSelected()) {
			clickToElement(driver, locator, params);
		}
	}

	protected Object executeJSForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	protected Object executeJSForBrowser(WebDriver driver, String javaScript, String loctor) {
		return ((JavascriptExecutor) driver).executeScript(javaScript, getWebElement(driver, loctor));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected boolean isImageLoadedSuccess(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeOf argumnets[0].naturalWith != \"undefined\"&& arguments[0].naturalWidth>0",
				getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoadedSuccess(WebDriver driver, String locator, String... params) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeOf argumnets[0].naturalWith != \"undefined\"&& arguments[0].naturalWidth>0",
				getWebElement(driver, locator, params));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void highlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String orignalStyle = getElementAttribute(driver, locator, "style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,
				"style", "border:2px solid red;border-style:dashed;");
		sleepInSeconds(GlobalConstants.SHORT_TIMEOUT);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,
				"style", orignalStyle);
	}

	protected String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentELement.innerText");
	}

	protected boolean areExpectedInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')");
		return textActual.equals(textExpected);
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location= '" + url + "'");
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void scrollToPixel(WebDriver driver, String pixelValue) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,'" + pixelValue + "')");
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "')",
				getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String value, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + value + "')",
				getWebElement(driver, locatorType));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",
				getWebElement(driver, locator));
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage",
				getWebElement(driver, locatorType));
	}

	public void clearToElementByDeleteKey(WebDriver driver, String locator) {
		getWebElement(driver, locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void moveToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}

	protected void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}

	protected void dragAndDropToElement(WebDriver driver, String sourcelocatorType, String targetlocatorType) {
		new Actions(driver)
				.dragAndDrop(getWebElement(driver, sourcelocatorType), getWebElement(driver, targetlocatorType))
				.perform();
	}

	public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}

	// wait methods
	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, locator, params).isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time: " + new Date().toString());
		overrideGlobalTimeout(driver, 3);
		List<WebElement> elementList = getListWebElement(driver, locator);
		overrideGlobalTimeout(driver, 30);
		if (elementList.size() == 0) {
			System.out.println("-----Element not in DOM---");
			System.out.println("End time: " + new Date().toString());
			return true;
		} else if (elementList.size() > 0 && !elementList.get(0).isDisplayed()) {
			System.out.println("-----Element in DOM but not displayed visible---");
			System.out.println("End time: " + new Date().toString());
			return true;
		} else {
			System.out.println("-----Element displayed---");
			System.out.println("End time: " + new Date().toString());
			return false;
		}

	}

	protected boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	protected boolean isElementEnabled(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, locator, params).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, locator, params).isSelected();
	}

	protected void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT)
				.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementVisible(WebDriver driver, String locator, String... params) {
		new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT)
				.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getRestParameter(locator, params))));
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT)
				.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... params) {
		new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT)
				.until(ExpectedConditions.elementToBeClickable(getByLocator(getRestParameter(locator, params))));
	}

	protected void waitForElementSelected(WebDriver driver, String locator) {
		new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT)
				.until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
	}

	protected void waitForElementSelected(WebDriver driver, String locator, String... params) {
		new WebDriverWait(driver, GlobalConstants.WAIT_TIMEOUT)
				.until(ExpectedConditions.elementToBeSelected(getByLocator(getRestParameter(locator, params))));
	}

	protected void waitForElementPresent(WebDriver driver, String locator) {
		new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
	}

	protected void sleepInSeconds(long timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Sort methods
	protected boolean isElementNameSortedAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementNameList = getListWebElement(driver, locator);
		for (WebElement name : elementNameList) {
			arrayList.add(name.getText());
		}
		ArrayList<String> sortedArrayList = new ArrayList<String>();
		for (String child : arrayList) {
			sortedArrayList.add(child);
		}
		Collections.sort(sortedArrayList);
		return arrayList.equals(sortedArrayList);
	}

	protected boolean isElementNameSortedDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementNameList = getListWebElement(driver, locator);
		for (WebElement name : elementNameList) {
			arrayList.add(name.getText());
		}
		ArrayList<String> sortedArrayList = new ArrayList<String>();
		for (String child : arrayList) {
			sortedArrayList.add(child);
		}
		Collections.sort(sortedArrayList);
		Collections.reverse(sortedArrayList);
		return arrayList.equals(sortedArrayList);
	}

	protected boolean isElementFloatSortedAscending(WebDriver driver, String loctor) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = getListWebElement(driver, loctor);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		ArrayList<Float> sortedArrayList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedArrayList.add(child);
		}
		Collections.sort(sortedArrayList);
		return arrayList.equals(sortedArrayList);
	}

	protected boolean isElementFloatSortedDescending(WebDriver driver, String locatorType) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementFloatList = getListWebElement(driver, locatorType);
		for (WebElement elementFloat : elementFloatList) {
			arrayList.add(Float.parseFloat(elementFloat.getText().replace("$", "").trim()));
		}

		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);
		Collections.reverse(sortedList);

		return sortedList.equals(arrayList);
	}

	protected Date convertStringToDate(String dateString) {
		dateString = dateString.replace(",", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyy");
		Date date = null;
		try {
			date = formatter.parse(dateString.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public boolean isDataDateSortedAscending(WebDriver driver, String locatorType) {
		ArrayList<Date> arrayList = new ArrayList<Date>();
		List<WebElement> dateList = getListWebElement(driver, locatorType);
		for (WebElement date : dateList) {
			arrayList.add(convertStringToDate(date.getText()));
		}
		ArrayList<Date> sortedArrayList = new ArrayList<Date>();
		for (Date child : arrayList) {
			sortedArrayList.add(child);
		}
		Collections.sort(sortedArrayList);
		return arrayList.equals(sortedArrayList);
	}

	public boolean isDataDateSortedDescending(WebDriver driver, String locatorType) {
		ArrayList<Date> arrayList = new ArrayList<Date>();
		List<WebElement> elementDateList = getListWebElement(driver, locatorType);
		for (WebElement dateElement : elementDateList) {
			arrayList.add(convertStringToDate(dateElement.getText()));
		}

		ArrayList<Date> sortedList = new ArrayList<Date>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return sortedList.equals(arrayList);
	}

	public void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String fullFileName = "";
		for (String fileName : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UPLOAD_FILE_PATH + fileName + "\n";

		}

		fullFileName = fullFileName.trim();
		sendKeysToElement(driver, locator, fullFileName);
		sleepInSeconds(2);
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}

		sleepInSeconds(2);
	}
}
