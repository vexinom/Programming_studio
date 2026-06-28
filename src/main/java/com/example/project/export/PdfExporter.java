package com.example.project.export;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import java.util.Map;

public class PdfExporter implements ReportExporter
{
    public void export(Map<String, Double> stats, String filename)
    {
        try(PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage();
            document.addPage(page);

            try(PDPageContentStream contentStream = new PDPageContentStream(document, page))
            {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Meteo stats");

                contentStream.newLineAtOffset(0, -30);
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                for (Map.Entry<String, Double> entry : stats.entrySet()) {
                    contentStream.showText(entry.getKey() + ": " + entry.getValue());
                    contentStream.newLineAtOffset(0, -15);
                }
                contentStream.endText();
            }

            document.save(filename);
            System.out.println("PDF was saved in: " + filename);
        }
        catch(IOException e)
        {
            System.err.println("An error occured during PDF creation: " + e.getMessage());
        }
    }
}
