package utils;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ContextUtils {
    public static Response response;
    private Map<String, Object> data = new HashMap<>();

    public static void setResponse(Response res) {
        response = res;
    }

    public static Response getResponse() {
        return response;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }
}
