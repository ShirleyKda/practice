package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/funcionalTests"},
        glue = {"stepDefinitions"},
        monochrome = true,
        strict = true,
        tags = {"@pruebita"}
)
public class TestRunner {
}
