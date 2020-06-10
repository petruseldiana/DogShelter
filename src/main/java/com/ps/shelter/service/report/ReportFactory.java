package com.ps.shelter.service.report;

public class ReportFactory {

    public Report getReport(String reportType) {

        switch(reportType) {
            case "TXT":
                return new ReportTXT();
            case "PDF":
                return new ReportPDF();
        }
        return null;
    }
}
