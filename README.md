# gra-winners
API RESTful para consulta de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.
API dentro da maturidade 2 de Richardson.

## Requisitos
* Java JDK 17
* Maven 3.9.1

## **Running local**

1. Building: execute o comando Maven abaixo.
   (Obs.: este comando executa o teste integrado e gera o build): 
```sh
$ mvn clean package
```
2. Running: execute o service java com o comando abaixo:

```sh
$ java -jar target/gra-winners-0.0.1-SNAPSHOT.jar
```

3. EndPoints
Acesse o serviço: http://localhost:8080/swagger-ui/index.html ou execute o curl abaixo:
```sh
$ curl -X GET "http://localhost:8080/api/v1/producers/intervals" -H "accept: */*" 
```
4. Consultar bases H2. http://localhost:8080/h2. 
   Clique no botao connect.

