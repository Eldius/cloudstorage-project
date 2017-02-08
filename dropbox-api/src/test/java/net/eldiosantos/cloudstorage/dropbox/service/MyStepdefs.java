package net.eldiosantos.cloudstorage.dropbox.service;

import cucumber.api.java8.En;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 08/02/2017.
 */
public class MyStepdefs implements En {
    public MyStepdefs() {
        Given("I have (\\d+) cukes in my belly", (Integer cukes) -> {
            System.out.format("Cukes: %n\n", cukes);
        });
        Given("the following animals: (.*)", (List<String> animals) -> {
            System.out.println(String.format(
                    "What animals: %s"
                    , animals.stream()
                        .collect(Collectors.joining(", "))
            ));
        });
    }
}
