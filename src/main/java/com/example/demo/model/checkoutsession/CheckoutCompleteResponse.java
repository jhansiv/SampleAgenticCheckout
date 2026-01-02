package com.example.demo.model.checkoutsession;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;
import java.util.UUID;


@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new")
@JsonDeserialize(as = ImmutableCheckoutCompleteResponse.class)
public interface CheckoutCompleteResponse {
  UUID getCheckoutSessionId();
  String getStatus();

  String getCurrency();

  List<ItemInformation> getItemInformation();

  String getTotalAmount();

  ShipToAddress getShipToAddress();

  List<ShipToOptions> getShipToOptions();

  List<CheckoutTotal> getCheckoutTotals();
  String getMessage();
}
