package com.example.project.service;

import com.example.project.util.DataProvider;
import com.example.project.model.Root;
import com.example.project.model.SensorDataValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsAnalyzer 
{
    private final DataProvider data_provider;

    public StatisticsAnalyzer(DataProvider data_provider)
    {
        this.data_provider = data_provider;
    }

    public Map<String, Double> calculateStats()
    {
        List <Root> data = data_provider.fetchData();
        Map<String, Double> stats = new HashMap<>();

        if (data == null || data.isEmpty() == true)
        {
            return stats;
        }

        stats.put("Number_of_active_sensors", (double) data.size());

        double avreage_temperature = data.stream().flatMap(root -> root.getSensordatavalues().stream())
                                                  .filter(value -> value.getValue_type().equals("temperature"))
                                                  .mapToDouble(value -> Double.parseDouble(value.getValue()))
                                                  .average()
                                                  .orElse(0.0);

        double avreage_humidity = data.stream().flatMap(root -> root.getSensordatavalues().stream())
                                                  .filter(value -> value.getValue_type().equals("humidity"))
                                                  .mapToDouble(value -> Double.parseDouble(value.getValue()))
                                                  .average()
                                                  .orElse(0.0);


        double max_P10 = data.stream().flatMap(root -> root.getSensordatavalues().stream())
                                                  .filter(value -> value.getValue_type().equals("P1"))
                                                  .mapToDouble(value -> Double.parseDouble(value.getValue()))
                                                  .max()
                                                  .orElse(0.0);

        stats.put("Avreage_temperature", Math.round(avreage_temperature * 100.0) / 100.0);
        stats.put("Avreage_humidity", Math.round(avreage_humidity * 100.0) / 100.0);
        stats.put("Avreage_max_P10", max_P10);

        return stats;
    }
}
