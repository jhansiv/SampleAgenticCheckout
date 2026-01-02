package com.example.demo.controller;

import com.example.demo.fixtures.CheckoutSessionResponseFixture;
import com.example.demo.model.checkoutsession.CheckoutCompleteRequest;
import com.example.demo.model.checkoutsession.CheckoutCompleteResponse;
import com.example.demo.model.checkoutsession.CheckoutSessionRequest;
import com.example.demo.model.checkoutsession.CheckoutSessionResponse;

import com.example.demo.model.checkoutsession.CheckoutSessionUpdateRequest;
import com.example.demo.model.checkoutsession.ImmutableCheckoutCompleteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/checkout-session")
public class CheckoutSessionController{

    @PostMapping
    public ResponseEntity<CheckoutSessionResponse> createCheckoutSession(
            @RequestBody CheckoutSessionRequest checkoutRequestInformation) {

        //Validate Request
        //Business Logic to create Checkout Session
        CheckoutSessionResponse response = CheckoutSessionResponseFixture.getFullResponse();


        // Return 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<CheckoutSessionResponse> updateCheckoutSession(
            @RequestBody CheckoutSessionUpdateRequest checkoutSessionUpdateRequest) {

        //Validate Request
        //Business Logic to update Checkout Session
        CheckoutSessionResponse response = CheckoutSessionResponseFixture.getFullResponse();


        // Return 201 Created
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/{id}/complete")
    public ResponseEntity<CheckoutCompleteResponse> completeCheckoutSession(
            @RequestBody CheckoutCompleteRequest checkoutRequestInformation) {

        return ResponseEntity.ok(
                ImmutableCheckoutCompleteResponse.builder().checkoutSessionId(UUID.randomUUID()).build());
    }
}
