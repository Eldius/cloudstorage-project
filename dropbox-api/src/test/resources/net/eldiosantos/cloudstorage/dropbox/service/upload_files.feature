
Feature: Upload files for Dropbox
  It'll test the dropbox file listing feature

  Scenario: Sending a file
    And I create the folder "/development/folder-name"
    And I send "src/main/resources/requests/upload-request.json" to "/development/folder-name/upload-request.json"
    Then I look to the folder "/development/folder-name/"
    And I have "1" files


