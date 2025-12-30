package com.example.demo.model.checkoutsession;

import org.immutables.value.Value;

import java.util.UUID;


@Value.Immutable
public interface CheckoutCompleteResponse {
  UUID getCheckoutSessionId();
  String getMessage();
}
