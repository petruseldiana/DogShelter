package com.ps.shelter.service.report;

import com.ps.shelter.dto.DogDTO;
import com.ps.shelter.dto.FoodDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportTXT implements Report {

    private String home = System.getProperty("user.home");
    File file = new File(home + "/Downloads/" + "Report" + ".txt");

    static BufferedWriter writer = null;

    @Override
    public void generateReport(List<DogDTO> dogDTOS) {
        try {
            writer= new BufferedWriter(new FileWriter(file));
            writer.write("-> ADOPTED DOGS:");
            writer.newLine();
            writer.newLine();

            dogDTOS.forEach(dog -> {
                try {
                    writer.write("Name: " );
                    writer.write(dog.getName());
                    writer.newLine();
                    writer.write("Breed: ");
                    writer.write(dog.getBreed());
                    writer.newLine();
                    writer.write("Age: " + dog.getAgeInYears() + " years, " + dog.getAgeInMonths() + " months");
                    writer.newLine();
                    writer.newLine();
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
