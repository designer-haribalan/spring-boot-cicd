package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "StepDefinitions", // Package containing step definitions
        plugin = {
                "pretty", // For readable console output
                "html:target/cucumber-reports/cucumber.html", // Generates HTML report
                "json:target/cucumber-reports/cucumber.json", // Generates JSON report
                "junit:target/cucumber-reports/cucumber.xml",
                "json:target/cucumber.json"// Generates JUnit report
        },
        monochrome = true // Makes console output readable
        //tags = "@Regression" // Run scenarios with this tag, modify as needed
)
public class CucumberTestRunner {
}
