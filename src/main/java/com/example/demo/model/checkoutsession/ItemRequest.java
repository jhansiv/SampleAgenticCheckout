package com.example.demo.model.checkoutsession;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface ItemRequest {

  String getLineModelNumber();

  UUID getLineId();

  Integer getSellingQuantity();
}
