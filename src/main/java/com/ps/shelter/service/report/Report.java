package com.ps.shelter.service.report;

import com.ps.shelter.dto.DogDTO;

import java.util.List;

public interface Report {

    void generateReport(List<DogDTO> dogDTOS);
}
