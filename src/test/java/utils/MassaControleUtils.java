package utils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MassaControleUtils {

    private static final List<String> idsUsuariosCriados = new ArrayList<>();
    private static final List<String> idsProdutosCriados = new ArrayList<>();
    private static final List<String> idsCarrinhosCriados = new ArrayList<>();

    // Adiciona o ID do usuário à lista
    public static void adicionarIdUsuario(String id) {
        idsUsuariosCriados.add(id);
    }

    public static void adicionarIdProduto(String id) {
        idsProdutosCriados.add(id);
    }

    public static void adicionarIdCarrinho(String id) {
        idsCarrinhosCriados.add(id);
    }

    // Recupera todos os IDs de usuários criados
    public static List<String> getIdsUsuariosCriados() {
        return new ArrayList<>(idsUsuariosCriados);
    }

    public static List<String> getIdsProdutosCriados() {
        return new ArrayList<>(idsProdutosCriados);
    }

    public static List<String> getIdsCarrinhosCriados() {
        return new ArrayList<>(idsCarrinhosCriados);
    }

    // Limpa a lista de IDs após os testes
    public static void limparIdsUsuarios() {
        idsUsuariosCriados.clear();
    }

    public static void limparIdsProdutos() {
        idsProdutosCriados.clear();
    }

    public static void limparIdsCarrinhos() {
        idsCarrinhosCriados.clear();
    }

    public static void deletarUsuario(String id) {
        given()
                .baseUri(Config.BASE_URL)
                .when()
                .delete("/usuarios/" + id);
    }

    public static void deletarProduto(String id) {
        given()
                .baseUri(Config.BASE_URL)
                .when()
                .delete("/produtos/" + id);
    }

    public static void deletarCarrinhos(String id) {
        given()
                .baseUri(Config.BASE_URL)
                .when()
                .delete("/carrinhos/" + id);
    }
}
