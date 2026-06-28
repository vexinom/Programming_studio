package com.example.project.export;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.util.Map;

public class XmlExporter implements ReportExporter
{
    public void export(Map<String, Double> stats, String filename)
    {
        try
        {
            new XmlMapper().writerWithDefaultPrettyPrinter().writeValue(new File(filename), stats);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
