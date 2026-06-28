package com.example.project.export;

import java.util.Map;

public interface ReportExporter 
{
    void export(Map<String, Double> stats, String filename);
}
