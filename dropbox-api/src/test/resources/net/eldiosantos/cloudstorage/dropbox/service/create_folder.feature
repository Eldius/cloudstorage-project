
Feature: Create folder feature
  It'll test the dropbox folder create feature

  Scenario: Create a folder
    When I create the folder "/development/test-folder"
    And I look to the folder "/development/test-folder"
    Then I have "0" files

  Scenario: Create another folder
    When I create the folder "/development/another-test-folder"
    Then I have this resource "/development/another-test-folder"


