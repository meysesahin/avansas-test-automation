package implementation;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AvansasImplementation {

    @Step("Go to Avansas Home Page")
    public void gotoAvansasHomePage() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url);
        assertThat(Driver.webDriver.getTitle()).contains("Avansas");
    }

    @Step("Search for the word <word>")
    public void searchForWord(String word) {
        WebElement searchBar = Driver.webDriver.findElement(By.name("q"));
        searchBar.sendKeys(word);
        WebElement searchButton = Driver.webDriver.findElement(By.xpath("//button[text()=\"ARA\"]"));
        searchButton.click();
    }

    @Step("Click on the category <category>")
    public void selectCategory(String category) {
        WebElement categoryBox = Driver.webDriver.findElement(By.xpath("//div[@class='category-card']/*[contains(text(), '" + category + "')]"));
        categoryBox.click();
    }

    @Step("Select sort by name from sorting function")
    public void sortByName() {
        WebElement sortingContainer = Driver.webDriver.findElement(By.id("select-search-0"));
        sortingContainer.click();
        WebElement sortingSearchBar = Driver.webDriver.findElement(By.className("select2-search__field"));
        sortingSearchBar.sendKeys("isme gore [a-z]");
        sortingSearchBar.sendKeys(Keys.RETURN);
    }

    @Step("Number of products containing <text> in their names should be greater than 0")
    public void checkNumberOfProducts(String text) {
        List<WebElement> products = Driver.webDriver.findElements(By.className("product-list"));
        long count = products.stream().filter(p -> p.getAttribute("data-product-name").contains(text)).count();
        Gauge.writeMessage("The amount of items whose names contain " + text + " is " + count);
        assertThat(count).isGreaterThan(0);
    }

}
