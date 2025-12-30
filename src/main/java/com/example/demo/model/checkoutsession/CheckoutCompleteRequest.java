package com.example.demo.model.checkoutsession;


import org.immutables.value.Value;

@Value.Immutable
public interface CheckoutCompleteRequest {
  PaymentRequest getpaymentData();
}
