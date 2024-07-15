package com.example.Airbnb2.controller;


import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.externalservices.PdfService;
import com.example.Airbnb2.payload.BookingDto;
import com.example.Airbnb2.repository.BookingRepository;
import com.example.Airbnb2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController 
{
    @Autowired
    private BookingService bookingService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/{propertyId}/{days}")
    public ResponseEntity<byte[]> createBooking(@PathVariable String propertyId, @PathVariable int days, @AuthenticationPrincipal PropertyUser user) {
        try {
            BookingDto booking = bookingService.createBooking(propertyId, user.getId(), days);
            byte[] bytes = pdfService.generateBookingPdf(booking);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "booking.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
