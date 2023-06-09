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
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;

import javax.net.ssl.SSLSession;

public class MainPageTest {
    String URL = "http://147.102.230.182:30007";
    Gson gson = new Gson();
    @Test
    void ensureThatEndpointReturns200() throws Exception {
        boolean append = true;
        boolean checkTimedOut = false;
        FileHandler handler = new FileHandler("./data.log", append); // Making the log file
        Logger logger = Logger.getLogger("com.example.demo"); // Get the java logger API
        logger.addHandler(handler);

        HttpClient client = HttpClient.newBuilder().build(); // Create the HTTPClient
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().timeout(Duration.ofSeconds(10)).uri(URI.create("http://18.193.199.62/?integrated")).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                logger.warning("Bad Response");
            } else {
                logger.info("Request Successful!" + "\nRequest body: " + response.body());
            }
            assertEquals(200, response.statusCode());
            assertNotEquals("", response.body());
        } catch (java.net.http.HttpConnectTimeoutException e) {
            logger.severe("Request Timed Out!");
            assertTrue(checkTimedOut);
        }
    }

    @Test
    void ensureThatEndpointServiceJsonReturns200() throws Exception {
        boolean append = true;
        boolean checkTimedOut = false;
        FileHandler handler = new FileHandler("./data.log", append); // Making the log file
        Logger logger = Logger.getLogger("com.example.demo"); // Get the java logger API
        logger.addHandler(handler);

        HttpClient client = HttpClient.newBuilder().build(); // Create the HTTPClient
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().timeout(Duration.ofSeconds(10)).uri(URI.create(URL + "/service.json")).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                logger.warning("Bad Response");
            }
            else if(response.statusCode() == 200){
                logger.info("Request Successful!" + "\nRequest body: " + response.body());
            }
            assertEquals(200, response.statusCode());
            assertNotEquals("", response.body());
        } catch (java.net.http.HttpConnectTimeoutException e){
            logger.severe("Request Timed Out!");
            assertTrue(checkTimedOut);
        }
    }

    @Test
    void ensureThatEndpointDataProvidersReturns200() throws Exception {
        boolean append = true;
        boolean checkTimedOut = false;
        FileHandler handler = new FileHandler("./data.log", append); // Making the log file
        Logger logger = Logger.getLogger("com.example.demo"); // Get the java logger API
        logger.addHandler(handler);

        HttpClient client = HttpClient.newBuilder().build(); // Create the HTTPClient
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().timeout(Duration.ofSeconds(10)).uri(URI.create(URL + "/ihelp/dataproviders")).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 500) {
                logger.warning("Internal Server Error");
            }
            else if(response.statusCode() == 200){
                logger.info("Request Successful!" + "\nRequest body: " + response.body());
            }
            assertEquals(200, response.statusCode());
            assertNotEquals("", response.body());
        } catch (java.net.http.HttpConnectTimeoutException e){
            logger.severe("Request Timed Out!");
            assertTrue(checkTimedOut);
        }
    }
    @Test
    void ensureThatEndpointDataCaptureTestReturns200() throws Exception {
        boolean append = true;
        boolean checkTimedOut = false;
        FileHandler handler = new FileHandler("./data.log", append); // Making the log file
        Logger logger = Logger.getLogger("com.example.demo"); // Get the java logger API
        logger.addHandler(handler);

        HttpClient client = HttpClient.newBuilder().build(); // Create the HTTPClient
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().timeout(Duration.ofSeconds(10)).uri(URI.create(URL + "/ihelp/datacapture/test")).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 500) {
                logger.warning("Internal Server Error");
            }
            else if(response.statusCode() == 200){
                logger.info("Request Successful!" + "\nRequest body: " + response.body());
            }
            assertEquals(200, response.statusCode());
            assertEquals("<b>Hello from the test resource!</b>", response.body().strip());
        } catch (java.net.http.HttpConnectTimeoutException e){
            logger.severe("Request Timed Out!");
            assertTrue(checkTimedOut);
        }
    }

    @Test
    void ensureThatEndpointDataCaptureReturns200() throws Exception {
        MainPage mainPage = new MainPage("1", "1", "1", "17.03.2023", "17.03.2023", "Ready", "dd.mm.yyyy", "", 0);
        String json = gson.toJson(mainPage);
        boolean append = true;
        boolean checkTimedOut = false;
        FileHandler handler = new FileHandler("./data.log", append); // Making the log file
        Logger logger = Logger.getLogger("com.example.demo"); // Get the java logger API
        logger.addHandler(handler);

        HttpClient client = HttpClient.newBuilder().build(); // Create the HTTPClient
        try {
            HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).timeout(Duration.ofSeconds(10)).uri(URI.create(URL + "/ihelp/datacapture")).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 500) {
                logger.warning("Internal Server Error");
            }
            else if(response.statusCode() == 200){
                logger.info("Request Successful!" + "\nRequest body: " + response.body());
            }
            assertEquals(200, response.statusCode());
        } catch (java.net.http.HttpConnectTimeoutException e){
            logger.severe("Request Timed Out!");
            assertTrue(checkTimedOut);
        }
    }
}
