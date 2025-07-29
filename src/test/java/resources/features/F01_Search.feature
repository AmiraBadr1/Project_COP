Feature: COP Final Project

Scenario: Verify that DuckDuckGo page title is "google"
Given User opens "chrome" browser "https://duckduckgo.com/"
Then the page title should be "Google"

Scenario: Verify that DuckDuckGo logo is displayed
Given User opens "chrome" browser "https://duckduckgo.com/"
Then DuckDuckGo logo should be visible

Scenario: Verify that first search result link is the Selenium WebDriver documentation
Given User opens "chrome" browser "https://duckduckgo.com/"
When User searches for "Selenium WebDriver"
Then The first result link should be "https://www.selenium.dev/documentation/webdriver/"

Scenario: Verify that the fourth result of TestNG search is 'TestNG Tutorial'
Given User opens "firefox" browser "https://duckduckgo.com/"
When User searches for "TestNG"
Then The text of the fourth result should be "TestNG Tutorial"

Scenario: Verify that second result on second page contains LinkedIn link
Given User opens "chrome" browser "https://duckduckgo.com/"
When User searches for "Cucumber IO"
And User goes to the second page of results
Then the page link should be "https://www.linkedin.com"

Scenario: Verify that both checkboxes are checked
Given User opens "chrome" browser "http://the-internet.herokuapp.com/checkboxes"
When User checks the first checkbox if not already checked
Then Both checkboxes should be checked

Scenario: Verify Country of Ernst Handel
Given User opens "chrome" browser "https://www.w3schools.com/html/html_tables.asp"
Then The country for the company "Ernst Handel" should be "Austria"


Scenario: File Upload Test
Given User opens "chrome" browser "http://the-internet.herokuapp.com/upload"
When User uploads the file "C:\\Users\\Amira\\Desktop\\baby.jpg"
Then The file should be uploaded successfully


Scenario: Verify drag and drop changes the text to "Dropped!"
Given User opens "chrome" browser "https://jqueryui.com/resources/demos/droppable/default.html"
When User drags the draggable element and drops it into the target
Then The drop area text should be "Dropped!"