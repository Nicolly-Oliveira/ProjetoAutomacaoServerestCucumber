package utils;

import dto.UsuarioDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsuarioUtils {

    public static UsuarioDTO criarUsuarioAdmin() {
        long timestamp = System.currentTimeMillis();

        UsuarioDTO usuario = new UsuarioDTO.Builder()
                .nome("Usuario Dein" + timestamp)
                .email("usuario" + timestamp + "@teste.com")
                .password("testdein")
                .administrador("true")
                .build();

        Response response = given()
                .baseUri(Config.BASE_URL)
                .contentType("application/json")
                .body(usuario)
                .when()
                .post("/usuarios");

        response.then().statusCode(201);

        String id = response.jsonPath().getString("_id");
        MassaControleUtils.adicionarIdUsuario(id);

        return usuario;
    }

    public static void deletarUsuario(String id) {
        given()
                .baseUri(Config.BASE_URL)
                .when()
                .delete("/usuarios/" + id);
    }
}
