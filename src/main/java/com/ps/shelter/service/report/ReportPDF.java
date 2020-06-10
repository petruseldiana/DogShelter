package com.ps.shelter.service.report;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ps.shelter.dto.DogDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ReportPDF implements Report {

    private String home = System.getProperty("user.home");
    File file = new File(home + "/Downloads/" + "Report" + ".pdf");

    @Override
    public void generateReport(List<DogDTO> dogDTOS) {
        try{

            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream(file));

            document.open();
            document.add(new Paragraph("~ ADOPTED DOGS:"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);

            PdfPCell cell = new PdfPCell();

            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(Rectangle.BOX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell.setPhrase(Phrase.getInstance("NAME"));
            table.addCell(cell);
            cell.setPhrase(Phrase.getInstance("BREED"));
            table.addCell(cell);
            cell.setPhrase(Phrase.getInstance("GENDER"));
            table.addCell(cell);
            cell.setPhrase(Phrase.getInstance("YEARS"));
            table.addCell(cell);
            cell.setPhrase(Phrase.getInstance("MONTHS"));
            table.addCell(cell);

            dogDTOS.forEach(dog -> {

                table.addCell(dog.getName());
                table.addCell(dog.getBreed());
                table.addCell(dog.getGender());
                table.addCell(String.valueOf(dog.getAgeInYears()));
                table.addCell(String.valueOf(dog.getAgeInMonths()));
            });

            // add the table to the document
            try {
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            document.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
