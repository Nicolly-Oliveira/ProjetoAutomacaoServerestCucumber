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
import utils.ProdutoUtils;
import utils.UsuarioUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericSteps {

    UsuarioDTO usuario;
    private ContextUtils context;

    public GenericSteps() {
    }

    @Dado("que eu tenha ao menos um usuario cadastrado")
    public void queEuTenhaAoMenosUmUsuarioCadastrado() {
        usuario = UsuarioUtils.criarUsuarioAdmin();
    }

    @Então("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        ContextUtils.getResponse().then().statusCode(statusCode);
    }

    @Então("devo receber no response os dados de acordo com o schema {string}")
    public void devoReceberNoResponseOsDadosDeAcordoComOSchema(String schemaName) {
        String schemaPath = String.format("src/test/resources/schemas/" + schemaName + ".json");

        try {
            String schema = new String(Files.readAllBytes(Paths.get(schemaPath)), StandardCharsets.UTF_8);
            ContextUtils.getResponse().then().body(JsonSchemaValidator.matchesJsonSchema(new JSONObject(schema).toString()));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o schema: " + schemaPath, e);
        }
    }
}
