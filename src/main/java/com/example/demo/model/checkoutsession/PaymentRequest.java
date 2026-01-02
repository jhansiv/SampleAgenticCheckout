package com.example.demo.model.checkoutsession;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new")
@JsonDeserialize(as = ImmutablePaymentRequest.class)
public interface PaymentRequest {
    String getToken();
    ShipToAddress getBillToAddress();

}
