package com.example.demo.model.checkoutsession;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.micrometer.common.lang.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new")
@JsonDeserialize(as = ImmutableShipToAddress.class)
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
