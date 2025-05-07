package steps;

import dto.ProdutoDTO;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import utils.Config;
import utils.ProdutoUtils;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProdutoSteps {

    ProdutoDTO produto;
    Response resposta;

    @Dado("que eu crio um novo produto")
    public void que_eu_crio_um_novo_produto() {
        produto = ProdutoUtils.criarProduto();
    }

    @Quando("eu listar os produtos")
    public void eu_listar_os_produtos() {
        resposta = given()
                .baseUri(Config.BASE_URL)
                .when()
                .get("/produtos");
    }

    @Entao("o produto deve estar na lista")
    public void o_produto_deve_estar_na_lista() {
        assertTrue(resposta.getBody().asString().contains(produto.getNome()));
        ProdutoUtils.deletarProduto(produto.getId());
    }
}
