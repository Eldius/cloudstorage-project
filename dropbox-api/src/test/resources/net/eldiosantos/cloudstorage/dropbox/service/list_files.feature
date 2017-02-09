
Feature: List files from Dropbox
  It'll test the dropbox file listing feature

  Scenario: Execute my test steps
    When I look to the folder "/Photos/Sample Album/"
    Then I have "3" files

