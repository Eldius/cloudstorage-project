
Feature: Download files from Dropbox
  It'll test the dropbox file listing feature

  Scenario: Download a picture
    When I look for the file "/Photos/Sample Album/Boston City Flow.jpg"
    Then I have this file in my temporary folder

  Scenario: Download a picture to a predefined folder
    When I look for the file "/Photos/Sample Album/Boston City Flow.jpg" to folder "build/download_picture_folder"
    Then I have this file on the local filesystem "build/download_picture_folder/Photos/Sample Album/Boston City Flow.jpg"

  Scenario: Downloading an unexistant file ('/invalid/dropbox/folder/invalid_file.png')
    When I look for the file "/invalid/dropbox/folder/invalid_file.png"
    Then I have an "java.lang.IllegalArgumentException" exception on my download attempt

