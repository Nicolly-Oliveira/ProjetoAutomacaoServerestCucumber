package steps;

import dto.UsuarioDTO;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import utils.Config;
import utils.ContextUtils;
import utils.UsuarioUtils;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CadastrarUsuarioSteps {

    UsuarioDTO usuario;
    Response response;
    private ContextUtils context;
    public CadastrarUsuarioSteps() {
    }

    @Dado("que eu tenho os dados de um novo usuário {string} válido")
    public void queEuTenhoOsDadosDeUmNovoUsuarioValido(String tipoUsuario) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nome = "Usuario Dein" + timestamp;
        String email = "usuariodein" + timestamp + "@teste.com";
        String administrador;

        if ("admin".equalsIgnoreCase(tipoUsuario)) {
            administrador = "true";
        } else {
            administrador = "false";
        }

        usuario = new UsuarioDTO.Builder()
            .nome(nome)
            .email(email)
            .password("testedein")
            .administrador(administrador)
            .build();
    }

    @Dado("que eu tenho um usuário sem os campos preenchidos")
    public void queEuTenhoUmUsuarioSemOsCamposObrigatoriosPreenchidos() {
        usuario = new UsuarioDTO.Builder().build();
    }

    @Quando("eu envio uma requisição para cadastrar o usuário")
    public void euEnvioUmaRequisicaoParaCadastrarOUsuario() {
        response = given()
                    .baseUri(Config.BASE_URL)
                    .contentType("application/json")
                    .body(usuario)
                .when().log().all()
                    .post("/usuarios")
                .then().log().all()
                    .extract().response();

        ContextUtils.setResponse(response);
    }

    @Entao("a mensagem retornada deve ser {string}")
    public void aMensagemRetornadaDeveSer(String message) {
        String mensagem = ContextUtils.response.jsonPath().getString("message");
        assertTrue(mensagem.contains(message));

        UsuarioUtils.deletarUsuario(ContextUtils.response.jsonPath().getString("_id"));
    }

    @Então("a mensagem de erro deve indicar campo obrigatório")
    public void aMensagemDeErroDeveIndicarCampoObrigatorio() {
        assertTrue(ContextUtils.response.getBody().asString().contains("Campo obrigatorio"));
    }

    @Então("o campo {string} deve ser {string}")
    public void oCampoDeveSer(String campo, String valorEsperado) {
        String valorObtido = ContextUtils.response.jsonPath().getString(campo);
        assertEquals(valorEsperado, valorObtido);
    }

    @Entao("não deve conter erros na resposta")
    public void naoDeveConterErrosNaResposta() {
        assertFalse(ContextUtils.response.getBody().asString().contains("erro"));
    }

    @Dado("que eu tenho os dados do usuário com perfil {string}")
    public void queEuTenhoOsDadosDoUsuarioComPerfil(String perfil) {
        usuario = UsuarioUtils.criarUsuarioAdmin();
    }
}
