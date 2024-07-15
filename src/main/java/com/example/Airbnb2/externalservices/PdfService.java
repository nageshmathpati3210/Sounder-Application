package com.example.Airbnb2.externalservices;


import com.example.Airbnb2.payload.BookingDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class PdfService
{
    public byte[] generateBookingPdf(BookingDto booking) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Add title
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Booking Confirmation");
                contentStream.endText();

                // Add booking details
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Booking ID: " + booking.getId());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Total Amount: $" + booking.getTotal());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Nightly Price: $" + booking.getPropertyDto().getNightlyPrice());
                contentStream.endText();

                // Add property user details
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, 640);
                contentStream.showText("Property User Details");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(50, 620);
                contentStream.showText("Name: " + booking.getPropertyUserDto().getFirstName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Email: " + booking.getPropertyUserDto().getEmail());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Phone: " + booking.getPropertyUserDto().getMobile());
                contentStream.endText();

                // Add property details
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, 560);
                contentStream.showText("Property Details");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(50, 540);
                contentStream.showText("Property Name: " + booking.getPropertyDto().getPropertyName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Address: " + booking.getPropertyDto().getLocation());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("City: " + booking.getPropertyDto().getCountry());
                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}