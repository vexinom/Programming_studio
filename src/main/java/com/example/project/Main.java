package com.example.project;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.example.project.service.StatisticsAnalyzer;
import com.example.project.util.ApiDataLoader;
import com.example.project.util.DataProvider;

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

        String choice = scanner.nextLine();

        scanner.close();

        
    }
}
