package com.example.demo.model.checkoutsession;
import org.immutables.value.Value;

@Value.Immutable
public interface CheckoutTotal {
  String getTotalType();

  String getDisplayText();

  Double getAmount();
}
