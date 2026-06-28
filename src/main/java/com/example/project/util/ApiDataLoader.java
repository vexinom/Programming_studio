package com.example.project.util;

import com.example.project.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ApiDataLoader implements DataProvider
{
    private static final String API_URL = "https://data.sensor.community/airrohr/v1/filter/box=52.36734243199027,20.819494415027485,52.09692843752652,21.319390572461643";
    
    @Override
    public List<Root> fetchData()
    {
        try
        {
            HttpClient http_client = HttpClient.newHttpClient();
            HttpRequest http_request = HttpRequest.newBuilder().uri(URI.create(API_URL)).GET().build();
            
            HttpResponse<String> http_response = http_client.send(http_request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            Root[] records = mapper.readValue(http_response.body(), Root[].class);
            return Arrays.asList(records);
        }
        catch (Exception e)
        {
            System.err.println("An error occured during data fetching " + e.getMessage());
            return List.of();
        }
    }
}
