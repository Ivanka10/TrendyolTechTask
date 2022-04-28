# Introduction
This is a technical task for Trendyol project. A simple framework, which contains one base test.

# Technologies
- Java
- Maven
- Selenium
- TestNg
- SoftAssertions  
- Allure
- Log4j

# Getting Started
Installation process:
- clone project from github
- install: java, maven, allure

# Build and Test
Here are few options for running a test:
 - configure a runner
 - run test.xml file
 - execute from console

Also, you can find a 'test.properties' file with all parameters for test: 
   - driver
   - base url
   - email
   - password

# Allure report
Here is an allure-results folder.
- You can see the report after running a command : allure serve allure-results
- Report contains all steps, attachments and screenshots(if test failed)
