package com.example.demo.model.checkoutsession;

import org.immutables.value.Value;

@Value.Immutable
public interface ShipToOptions {
  String getViaCode();

  String getViaDescription();

  String getViaId();

  String getLeadTime();

  String getLeadTimeDays();

  String getShippingCost();

  boolean getIsFreight();

  boolean getIsParcel();
}
