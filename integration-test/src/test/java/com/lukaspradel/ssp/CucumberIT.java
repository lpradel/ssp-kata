package com.lukaspradel.ssp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:specification"},
        glue = {"com.lukaspradel.ssp"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public final class CucumberIT {
}
