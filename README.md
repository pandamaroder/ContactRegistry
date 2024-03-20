## Core Java JVM Cookbooks and Examples

Demo concole app for Contacts saving (Spring)

[![Java CI](https://github.com/pandamaroder/ContactRegistry/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/pandamaroder/ContactRegistry/actions/workflows/github-actions-demo.yml)

### Requirements:
- Java 17+
- Spring Boot 3.0+

## Profiles and initial configuration 
-profile name: "initFromFile" (specialize the initial contacts by configuring initialization of contacts from the 
file)  / -Dspring-profiles=initFromFile
- property file path key: "contacts.fileLocation"
## Saved Contacts configuration
- saved contacts file path key:  "contacts.save-path"
## Run locally

- 1- specify profile to load contacts from the initial file: -profile "initFromFile"
- 2- run the app
- 3 - choose the number of action through the predefined number of the action

