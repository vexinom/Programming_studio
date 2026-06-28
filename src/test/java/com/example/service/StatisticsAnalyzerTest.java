package com.example.project.service;

import com.example.project.model.Root;
import com.example.project.model.SensorDataValue;
import com.example.project.service.StatisticsAnalyzer;
import com.example.project.util.DataProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticsAnalyzerTest
{
    @Test
    public void testTemperatureCalc()
    {
        DataProvider mockProvider = mock(DataProvider.class);

        Root fake_root_1 = new Root();
        ArrayList<SensorDataValue> values_1 = new ArrayList<>();
        SensorDataValue temperature_1 = new SensorDataValue();
        temperature_1.setValue_type("temperature");
        temperature_1.setValue("69.63");
        values_1.add(temperature_1);
        fake_root_1.setSensordatavalues(values_1);

        Root fake_root_2 = new Root();
        ArrayList<kafka.model.SensorDataValue> values_2 = new ArrayList<>();
        SensorDataValue temperature_2 = new SensorDataValue();
        temperature_2.setValue_type("temperature");
        temperature_2.setValue("21.37");
        values_2.add(temperature_2);
        fake_root_2.setSensordatavalues(values_2);

        when(mockProvider.fetchData()).thenReturn(Arrays.asList(fake_root_1, fake_root_2));
        StatisticsAnalyzer analyzer = new StatisticsAnalyzer(mockProvider);
        Map<String, Double> results = analyzer.calculateStats();

        assertEquals(45.5, results.get("Avreage_temperature"));
        assertEquals(2.0, results.get("Number_of_active_sensors"));
    }
}