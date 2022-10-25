package br.com.adrianomenezes.cloudparking.controller;

import br.com.adrianomenezes.cloudparking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    public static ParkingCreateDTO createDTO;

    @BeforeAll
    public static  void setUpTestInitial(){
        createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("XYZ-1234");
        createDTO.setModel("FIAT UNO");
        createDTO.setState("BA");

    }

    @BeforeEach
    public void setUpTest(){

        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThen() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(200)
                .body("license[0]", Matchers.contains("DMS-"))
                .extract().response().body().prettyPrint()
        ;
    }

    @Test
    void deletedById() {
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(201)
                .body("license", Matchers.equalTo("XYZ-1234"))
                .body("color", Matchers.equalTo("AMARELO"))
                .extract().response().body().prettyPrint()
        ;

    }

    @Test
    void whenExitParkingThen() {
//        ResponseBody body = RestAssured.given()
//                .when()
//                .get("/parking")
//                .then()
//                .extract().response().getBody("license");
//        System.out.println(body);
//        RestAssured.given()
//                .when()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .
//                .body(createDTO)
//                .post("/parking")
//                .then()
//                .statusCode(201)
//                .body("license", Matchers.equalTo("XYZ-1234"))
//                .body("color", Matchers.equalTo("AMARELO"))
//                .extract().response().body().prettyPrint()
//        ;
    }
}