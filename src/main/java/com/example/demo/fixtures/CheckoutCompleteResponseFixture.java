package com.example.demo.fixtures;

import com.example.demo.model.checkoutsession.CheckoutCompleteResponse;
import com.example.demo.model.checkoutsession.CheckoutTotal;
import com.example.demo.model.checkoutsession.ImmutableCheckoutCompleteResponse;
import com.example.demo.model.checkoutsession.ImmutableCheckoutTotal;
import com.example.demo.model.checkoutsession.ImmutableItemInformation;
import com.example.demo.model.checkoutsession.ImmutableShipToAddress;
import com.example.demo.model.checkoutsession.ImmutableShipToOptions;
import com.example.demo.model.checkoutsession.ItemInformation;
import com.example.demo.model.checkoutsession.ShipToAddress;
import com.example.demo.model.checkoutsession.ShipToOptions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CheckoutCompleteResponseFixture {

    public static CheckoutCompleteResponse getFullResponse()
    {

        return ImmutableCheckoutCompleteResponse.builder()
                .checkoutSessionId(UUID.randomUUID())
                .status("COMPLETED")
                .itemInformation(getItemInformation())
                .shipToAddress(createShipToAddress())
                .currency("USD")
                .shipToOptions(getShipToOptions())
                .checkoutTotals(getTotals())
                .totalAmount("129.90")
                .build();

    }

    private static ShipToAddress createShipToAddress() {
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

    private static List<ShipToOptions> getShipToOptions() {
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

    private static  List<CheckoutTotal> getTotals() {
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

    private static List<ItemInformation> getItemInformation() {
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

}
