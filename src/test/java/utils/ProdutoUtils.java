package utils;

import dto.ProdutoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProdutoUtils {

    public static ProdutoDTO criarProduto() {
        ProdutoDTO produto = new ProdutoDTO("Produto Teste " + System.currentTimeMillis(), 150, "descricao", 10);

        Response response = given()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON)
                .body(produto)
                .when()
                .post("/produtos");

        produto.setId(response.jsonPath().getString("_id"));
        return produto;
    }

    public static void deletarProduto(String id) {
        given()
                .baseUri(Config.BASE_URL)
                .when()
                .delete("/produtos/" + id);
    }
}
