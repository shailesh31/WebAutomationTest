package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Created by Shailesh Sharma 06-12-2020.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"test/bdd/features/"},
        glue = {"steps"},
        plugin = {
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "pretty", "json:target/Cucumber.json",
                "html:target/cucumber-html-report"
        },
        tags = {"@Case1"}
)
public class TestsRunner {
}
