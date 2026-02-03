package com.example.demo.model.checkoutsession;

import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
public interface ShipToOptions {
  String getViaCode();

  String getViaDescription();

  String getViaId();

  String getLeadTime();

  String getLeadTimeDays();

  BigDecimal getShippingCost();

  boolean getIsFreight();

  boolean getIsParcel();
}
