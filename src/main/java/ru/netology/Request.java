package ru.netology;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class Request {
    private final String method;// GET, POST, PUT, DELETE и т. д.
    private final String path;
    private final String queryString;

    private final Map<String, String> queryParams;
    private final Map<String, String> headers;  // Заголовки (ключ-значение)
    private final String body;        // Тело запроса (может быть null)

    public Request(String method, String fullPath, Map<String, String> headers, String body) {
        this.method = method;
        this.headers = headers != null ? new HashMap<>(headers) : new HashMap<>();
        this.body = body;

        // Разделяем путь и query-строку
        String[] pathParts = fullPath.split("\\?", 2);
        this.path = pathParts[0];
        this.queryString = pathParts.length > 1 ? pathParts[1] : null;

        // Парсим query-параметры
        this.queryParams = parseQueryParams();
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

    private Map<String, String> parseQueryParams() {
        if (queryString == null || queryString.isEmpty()) {
            return Collections.emptyMap();
        }

        List<NameValuePair> params = URLEncodedUtils.parse(queryString, StandardCharsets.UTF_8);
        return params.stream()
                .collect(Collectors.toMap(
                        NameValuePair::getName,
                        NameValuePair::getValue,
                        (first, second) -> second)); // при дублировании берем последнее значение
    }

    // Методы для работы с query-параметрами
    public String getQueryParam(String name) {
        return queryParams.get(name);
    }

    public Map<String, String> getQueryParams() {
        return new HashMap<>(queryParams);
    }
}
