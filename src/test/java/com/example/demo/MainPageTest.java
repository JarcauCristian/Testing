package com.example.demo;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;

import javax.net.ssl.SSLSession;

public class MainPageTest {
    MainPage mainPage = new MainPage("password", "dev", "qwer1234", "headless");
    @Test
    void ensureThatEndpointReturns200() throws Exception {
        FileHandler handler = new FileHandler("data.log", true);
        Logger logger = Logger.getLogger("com.example.demo");
        logger.addHandler(handler);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/x-www-form-urlencoded").POST(getParamsUrlEncoded(mainPage.convertToMap())).uri(URI.create("https://retention-csb-test.biomed.ntua.gr/auth/realms/retention/protocol/openid-connect/token")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.warning("Bad Response");
        }
        assertEquals(200, response.statusCode());
    }

    private HttpRequest.BodyPublisher getParamsUrlEncoded(HashMap<String, String> parameters) {
        String urlEncoded = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return HttpRequest.BodyPublishers.ofString(urlEncoded);
    }

}
