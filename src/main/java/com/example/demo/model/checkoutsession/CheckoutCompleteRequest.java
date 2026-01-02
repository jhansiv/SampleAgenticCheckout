package com.example.demo.model.checkoutsession;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new")
@JsonDeserialize(as = ImmutableCheckoutCompleteRequest.class)
public interface CheckoutCompleteRequest {

  CustomerInformation getCustomerInformation();
  PaymentRequest getPaymentData();
}
