package com.example.demo.model.checkoutsession;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new")
@JsonDeserialize(as = ImmutableCheckoutSessionRequest.class)
public interface CheckoutSessionRequest {
  List<ItemRequest> getItemInformation();

  ShipToAddress getAddress();
}
