package com.example.demo.model.checkoutsession;

import org.immutables.value.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value.Immutable
public interface ItemInformation {
  String getLineModelNumber();

  UUID getLineId();

  Integer getSellingQuantity();

  BigDecimal getExtendedPrice();

  BigDecimal getLineTax();
}
