package steps;

import dto.UsuarioDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.Config;
import utils.ContextUtils;
import utils.UsuarioUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static utils.ContextUtils.response;

public class ListarUsuarioSteps {

    private ContextUtils context;

    Response resposta;

    public ListarUsuarioSteps() {
    }

    @Quando("eu envio uma requisição para listar os usuários")
    public void euEnvioUmaRequisicaoParaListarOsUsuarios() {
        resposta = given()
                        .baseUri(Config.BASE_URL)
                        .contentType("application/json")
                        .when().log().all()
                        .get("/usuarios")
                        .then().log().all()
                        .extract().response();

        ContextUtils.setResponse(resposta);
    }

}
