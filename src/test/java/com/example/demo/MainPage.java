package com.example.demo;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

// page_url = https://www.jetbrains.com/
public class MainPage {
    public String grant_type;

    public String username;

    public String password;

    public String client_id;

    MainPage(String grant_type, String username, String password, String client_id) {
        this.grant_type = grant_type;
        this.username = username;
        this.password = password;
        this.client_id = client_id;
    }

    public HashMap<String, String> convertToMap() {
        HashMap<String, String> content = new HashMap<>();

        content.put("grant_type", this.grant_type);
        content.put("username", this.username);
        content.put("password", this.password);
        content.put("client_id", this.client_id);

        return content;
    }
}
