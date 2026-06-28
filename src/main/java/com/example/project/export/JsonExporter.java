package com.example.project.export;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;

public class JsonExporter implements ReportExporter
{
    public void export(Map<String, Double> stats, String filename)    
    {
        try
        {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(filename), stats);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
