## Core Java JVM Cookbooks and Examples

Demo concole app for Contacts saving (Spring)


### Requirements:
-Java 17+
- Spring Boot 3.0+

## Profiles and initial configuration 
-profile name: "initFromFile" (specialize the initial contacts by configuring initialization of contacts from the 
file) 
- property file path key: "contacts.fileLocation"
## Saved Contacts configuration
- saved contacts file path key:  "contacts.save-path"
- 
## Run locally

- 1- specify profile to load contacts from the initial file: -profile "initFromFile"
- 2- run the app
- 3 - choose the number of action through the number of the action:
("1. Просмотреть контакты");
("2. Добавить новый контакт");
("3. Удалить контакт по email");
("4. Сохранить контакты в файл");
("5. Новый функционал");
("6. Выйти");

