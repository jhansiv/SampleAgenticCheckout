package com.example.demo.controller;

import com.example.demo.model.checkoutsession.CheckoutCompleteRequest;
import com.example.demo.model.checkoutsession.CheckoutCompleteResponse;
import com.example.demo.model.checkoutsession.CheckoutSessionRequest;
import com.example.demo.model.checkoutsession.CheckoutSessionResponse;
import com.example.demo.model.checkoutsession.CheckoutTotal;
import com.example.demo.model.checkoutsession.ImmutableCheckoutCompleteResponse;
import com.example.demo.model.checkoutsession.ImmutableCheckoutSessionResponse;
import com.example.demo.model.checkoutsession.ImmutableCheckoutTotal;
import com.example.demo.model.checkoutsession.ImmutableItemInformation;
import com.example.demo.model.checkoutsession.ImmutableShipToAddress;
import com.example.demo.model.checkoutsession.ImmutableShipToOptions;
import com.example.demo.model.checkoutsession.ItemInformation;
import com.example.demo.model.checkoutsession.ShipToAddress;
import com.example.demo.model.checkoutsession.ShipToOptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/checkout-session")
public class CheckoutSessionController{


    @PostMapping
    public ResponseEntity<CheckoutSessionResponse> createCheckoutSession(
            @RequestBody CheckoutSessionRequest checkoutRequestInformation) {

        return ResponseEntity.ok(
                ImmutableCheckoutSessionResponse.builder()
                        .checkoutSessionId(UUID.randomUUID())
                        .status("IN PROGRESS")
                        .itemInformation(getItemInformation())
                        .shipToAddress(createShipToAddress())
                        .currency("USD")
                        .shipToOptions(getShipToOptions())
                        .checkoutTotals(getTotals())
                        .totalAmount("129.90")
                        .build());
    }

    private ShipToAddress createShipToAddress() {
        return ImmutableShipToAddress.builder()
                .name("John Doe")
                .addressLine1("123 Main St")
                .addressLine2("Apt 4B")
                .city("Anytown")
                .stateProvinceCode("CA")
                .postalCode("12345")
                .postalCodeExtension("6789")
                .countryCode("US")
                .build();
    }

    private List<ShipToOptions> getShipToOptions() {
        return Arrays.asList(
                ImmutableShipToOptions.builder()
                        .viaCode("UP")
                        .viaDescription("Ground Shipping")
                        .viaId("SHIP_VIA_001")
                        .leadTime("3 Business Days")
                        .leadTimeDays("5-7")
                        .shippingCost("10.00")
                        .isFreight(false)
                        .isParcel(true)
                        .build(),
                ImmutableShipToOptions.builder()
                        .viaCode("JP")
                        .viaDescription("MOTOR FREIGHT - JP EXPRESS")
                        .viaId("SHIP_VIA_002")
                        .leadTime("2")
                        .leadTimeDays("2 days")
                        .shippingCost("25.00")
                        .isFreight(true)
                        .isParcel(false)
                        .build());
    }

    private List<CheckoutTotal> getTotals() {
        return Arrays.asList(
                ImmutableCheckoutTotal.builder()
                        .totalType("SUBTOTAL")
                        .displayText("Subtotal")
                        .amount(140.00)
                        .build(),
                ImmutableCheckoutTotal.builder()
                        .totalType("LINE ITEM TAXES")
                        .displayText("Line Taxes")
                        .amount(10.00)
                        .build(),
                ImmutableCheckoutTotal.builder()
                        .totalType("TAX")
                        .displayText("Shipping")
                        .amount(10.00)
                        .build());
    }

    private List<ItemInformation> getItemInformation() {
        return Arrays.asList(

                ImmutableItemInformation.builder()
                        .lineId(UUID.randomUUID())
                        .lineModelNumber("Pallet Truck")
                        .sellingQuantity(2)
                        .extendedPrice(new BigDecimal("399.99"))   // optional
                        .lineTax(new BigDecimal("45.00"))          // optional
                        .build(),


                ImmutableItemInformation.builder()
                        .lineId(UUID.randomUUID())
                        .lineModelNumber("Rolling Ladder")
                        .sellingQuantity(2)
                        .extendedPrice(new BigDecimal("599.99"))   // optional
                        .lineTax(new BigDecimal("45.00"))          // optional
                        .build());

    }


    @PostMapping(path = "/{id}/complete")
    public ResponseEntity<CheckoutCompleteResponse> completeCheckoutSession(
            @RequestBody CheckoutCompleteRequest checkoutRequestInformation) {

        return ResponseEntity.ok(
                ImmutableCheckoutCompleteResponse.builder().checkoutSessionId(UUID.randomUUID()).build());
    }
}
