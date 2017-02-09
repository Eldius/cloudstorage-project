package net.eldiosantos.cloudstorage.dropbox.service;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Created by esjunior on 08/02/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:build/cucumber"}
)
public class RunCukesTest {
}
