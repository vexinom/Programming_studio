package com.example.project;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


import com.example.project.service.StatisticsAnalyzer;
import com.example.project.util.ApiDataLoader;
import com.example.project.util.DataProvider;

import com.example.project.export.PdfExporter;
import com.example.project.export.XmlExporter;
import com.example.project.export.JsonExporter;

public class Main 
{
    public static void main( String[] args )
    {
        System.out.println("Fetching data...");

        DataProvider data_provider = new ApiDataLoader();
        StatisticsAnalyzer analyzer = new StatisticsAnalyzer(data_provider);

        Map<String, Double> stats = analyzer.calculateStats();

        System.out.println("-----STATS-----");
        stats.forEach((key, value) -> System.out.println(key + "  " + value));
        System.out.println("---------------");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Save as");
        System.out.println("P -> PDF");
        System.out.println("J -> JSON");
        System.out.println("X -> XML");

        String choice = scanner.nextLine().toLowerCase();

        switch(choice)
        {
            case "p":
                new PdfExporter().export(stats, "result.pdf");
                System.out.println("Data were saved to PDF");
                break;

            case "j":
                new JsonExporter().export(stats, "result.json");
                System.out.println("Data were saved to JSON");
                break;

            case "x":
                new XmlExporter().export(stats, "result.xml");
                System.out.println("Data were saved to XML");
                break;

            default:
                System.out.println("Invalid option: " + choice);
        }

        scanner.close();

    }
}
