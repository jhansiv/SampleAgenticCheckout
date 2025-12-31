package com.example.demo.model.checkoutsession;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new")
@JsonDeserialize(as = ImmutableItemRequest.class)
public interface ItemRequest {

  String getLineModelNumber();

  UUID getLineId();

  Integer getSellingQuantity();
}
