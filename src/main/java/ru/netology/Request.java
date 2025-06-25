package ru.netology;

import java.util.HashMap;
import java.util.Map;
public class Request {
    private final String method;      // GET, POST, PUT, DELETE и т. д.
    private final Map<String, String> headers;  // Заголовки (ключ-значение)
    private final String body;        // Тело запроса (может быть null)

    public Request(String method, Map<String, String> headers, String body) {
        this.method = method;
        this.headers = headers != null ? new HashMap<>(headers) : new HashMap<>();
        this.body = body;
    }

    // Геттеры
    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return new HashMap<>(headers);  // Возвращаем копию для иммутабельности
    }

    public String getBody() {
        return body;
    }

}
