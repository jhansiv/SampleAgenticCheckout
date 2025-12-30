package com.example.demo.model.checkoutsession;


import io.micrometer.common.lang.Nullable;
import org.immutables.value.Value;

@Value.Immutable
public interface ShipToAddress {

  String getName();

  String getAddressLine1();

  String getAddressLine2();

  @Nullable
  String getColoniaNeighborhood();

  String getCity();

  String getStateProvinceCode();

  String getPostalCode();

  String getPostalCodeExtension();

  String getCountryCode();
}
