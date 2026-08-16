package service;

import java.util.Date;

import util.ExcelGenerator;

public class ExcelReportJob {

    public void execute() {
        System.out.println("EXCEL REPORT JOB STARTED AT: " + new Date());

        try {
            byte[] excelBytes = ExcelGenerator.generateExcel();// TAWAG SA SHARED CLASS

            simulateSendEmail(
                "Daily Report - " + new Date(),
                "elife@yourcompany.com.ph",
                "recipient@yourcompany.com.ph",
                "ccperson@yourcompany.com.ph",
                excelBytes
            );

        } catch (Exception e) {
            System.out.println("ERROR generating/sending report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void simulateSendEmail(String subject, String sender, String recipient, 
                                     String cc, byte[] attachmentBytes) {
        System.out.println("========== SIMULATED EMAIL ==========");
        System.out.println("Subject   : " + subject);
        System.out.println("Sender    : " + sender);
        System.out.println("Recipient : " + recipient);
        System.out.println("CC        : " + cc);
        System.out.println("Attachment: report.xlsx (" + attachmentBytes.length + " bytes)");
        System.out.println("=======================================");
    }
}