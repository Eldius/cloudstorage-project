
Feature: List files from Dropbox
  It'll test the dropbox file listing feature

  Scenario: Listing files on folder '/Photos/Sample Album/'
    When I look to the folder "/Photos/Sample Album/"
    Then I have "3" files

  Scenario: Listing files on an unexistant folder ('/invalid/dropbox/folder/')
    When I look to the folder "/invalid/dropbox/folder/"
    Then I have an "java.lang.IllegalStateException" exception

