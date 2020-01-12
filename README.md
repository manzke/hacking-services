# badges
![](https://github.com/manzke/hacking-services/workflows/Build+Project/badge.svg)

# hacking-services

mvn clean spring-boot:run

docker-compose -f ./docker-compose-dynamodb-local.yaml up -d

aws dynamodb --endpoint-url http://localhost:8042 create-table --table-name demo-customer-info --attribute-definitions AttributeName=customerId,AttributeType=S --key-schema AttributeName=customerId,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

aws dynamodb list-tables --endpoint-url http://localhost:8042


# readme
https://spring.io/blog/2016/11/28/going-reactive-with-spring-data
https://spring.io/projects/spring-data
https://github.com/derjust/spring-data-dynamodb
https://derjust.github.io/spring-data-dynamodb/
https://www.codeflow.site/de/article/spring-data-dynamodb (german)
https://github.com/aws/aws-sdk-java-v2
https://github.com/rieckpil/blog-tutorials/blob/master/.github/workflows/sampleJavaMavenProject.yml
