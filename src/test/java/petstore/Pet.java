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
    @Test// identifica o metodo ou função como um teste para o TestNG
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
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))
        ;


    }

}





