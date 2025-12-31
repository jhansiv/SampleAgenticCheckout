package com.example.demo.model.checkoutsession;

import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface CheckoutSessionResponse {

  UUID getCheckoutSessionId();

  String getStatus();

  String getCurrency();

  List<ItemInformation> getItemInformation();

  String getTotalAmount();

  ShipToAddress getShipToAddress();

  List<ShipToOptions> getShipToOptions();

  List<CheckoutTotal> getCheckoutTotals();
}
