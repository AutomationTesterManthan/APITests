package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/java/feature/Delete_operations.feature", 
                 glue= {"stepdefination"},
                 plugin= {"pretty",
                		 "json:target/cucumber.json"})

public class Runner_Class{

}





//385546