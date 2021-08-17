//1. Pacotes
package petstore;

// 2. Bibliotecas

// 3. Classes

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


public class Pet {

    //3.1 Atributos

    String uri = "https://petstore.swagger.io/v2/pet"; // Endereço da entidade Pet


    //3.2 Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }
    //Incluir - create - Post
    @Test(priority = 1)// identifica o metodo ou função como um teste para o TestNG
    public void incluirPet() throws IOException {

        String jsonBody = lerJson("data_base/pet1.json" );

        //sintaxe Gherkin (giria pepino e conserva = problema de comunicação
        // Dado - Quando - Então
        // Given - When - Then

        given()//Dado

                .contentType("application/json")// comum em API REST - antigas era "text/xml
                .log().all()
                .body(jsonBody)

        .when()//Quando
                .post(uri)

        .then()// Então
                .log().all()
                .statusCode(200)
                .body("name", is("thor"))
                .body("status", is("available"))
                .body("category.name", is("AXER34JTLO"))
                .body("tags.name", contains("sta"))
        ;


    }

    @Test(priority = 2)
    public void consultarPet(){
        String petId = "1980082251";
        String token =

        given()
                .contentType("applicatio/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("thor"))
                .body("category.name", is("AXER34JTLO"))
                .body("status", is("available"))

        .extract()
                .path("category.name")

        ;

        System.out.println("O token é " + token);

    }

    @Test(priority = 3)
    public void alterarPet() throws IOException {

        String jsonBody = lerJson("data_base/pet2.json" );

        //sintaxe Gherkin (giria pepino e conserva = problema de comunicação
        // Dado - Quando - Então
        // Given - When - Then

        given()//Dado

                .contentType("application/json")// comum em API REST - antigas era "text/xml
                .log().all()
                .body(jsonBody)

                .when()//Quando
                .put(uri)

                .then()// Então
                .log().all()
                .statusCode(200)
                .body("name", is("thor"))
                .body("status", is("Sold"))

        ;

    }
    @Test(priority = 4)
    public void excluirPet(){

        String petId = "1980082251";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is ("unknown"))
                .body("message", is(petId))



        ;


    }

}





