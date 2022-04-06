# clean and run spring boot
mvnw clean spring-boot:run

# test using curl get speeches
curl -v localhost:8080/speeches

# add new speech
curl -X POST localhost:8080/speeches -H "Content-type:application/json" -d "{'actualText': 'Speech of Pres Digong', 'subjectText': 'Digong Speech', 'createdDate': '2022-04-06'}"