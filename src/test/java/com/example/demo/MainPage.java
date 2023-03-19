package com.example.demo;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

// page_url = https://www.jetbrains.com/
public class MainPage {
    public String id;

    public String datasourceID;

    public String datasetID;

    public String dateAdded;

    public String dateFinished;

    public String status;

    public HashMap<String, String> connectorArguments;

    public HashMap<String, String> convertorArguments;

    public int batchSize;


    MainPage(String id, String datasourceID, String datasetID, String dateAdded, String dateFinished, String status, String datePattern, String nullString, int batchSize) {
        this.id = id;
        this.datasourceID = datasourceID;
        this.datasetID = datasetID;
        this.dateAdded = dateAdded;
        this.dateFinished = dateFinished;
        this.status = status;
        HashMap<String, String> connectorArguments = new HashMap<>();
        connectorArguments.put("datePattern", datePattern);
        connectorArguments.put("nullString", nullString);
        this.connectorArguments = connectorArguments;
        HashMap<String, String> convertorArguments = new HashMap<>();
        convertorArguments.put("datePattern", datePattern);
        this.convertorArguments = convertorArguments;
        this.batchSize = batchSize;
    }
}
