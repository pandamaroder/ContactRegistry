## Core Java JVM Cookbooks and Examples

Demo console app for Contacts saving (Spring)

[![Java CI](https://github.com/pandamaroder/ContactRegistry/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/pandamaroder/ContactRegistry/actions/workflows/github-actions-demo.yml)
[![codecov](https://codecov.io/gh/pandamaroder/ContactRegistry/graph/badge.svg?token=9KNR2SQ3QI)](https://codecov.io/gh/pandamaroder/ContactRegistry)

### Requirements:

- Java 17+
- Spring Framework 5

## Profiles and initial configuration

- profile name: "initFromFile" (specialize the initial contacts by configuring initialization of contacts from the file)
/ `-Dspring-profiles=initFromFile`

- property file path key: `contacts.fileLocation`

## Saved Contacts configuration

- saved contacts file path key:  `contacts.save-path`

## Run locally

1. specify profile to load contacts from the initial file if there are the need for it 
2. run the app
3. choose the number of action through the predefined number of the action

