package com.example.demo.model.checkoutsession;


import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface CheckoutSessionRequest {
  List<ItemRequest> getItemInformation();

  ShipToAddress getAddress();
}
