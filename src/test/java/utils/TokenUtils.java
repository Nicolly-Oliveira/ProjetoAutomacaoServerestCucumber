package utils;
import static io.restassured.RestAssured.given;

public class TokenUtils {

    private static String tokenAdmin;
    private static String tokenComum;

    public static String getTokenAdmin() {
        if (tokenAdmin == null) {
            tokenAdmin = login("admin", "senhaAdmin");
        }
        return tokenAdmin;
    }

    public static String getTokenComum() {
        if (tokenComum == null) {
            tokenComum = login("comum", "senhaComum");
        }
        return tokenComum;
    }

    private static String login(String usuario, String senha) {
        String endpointLogin = "/login";
        String jsonBody = String.format("{\"usuario\":\"%s\", \"senha\":\"%s\"}", usuario, senha);

        return given()
                .baseUri(Config.BASE_URL)
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(endpointLogin)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("token");
    }
}
