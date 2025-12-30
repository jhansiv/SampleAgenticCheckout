
package com.example.demo.model;

import org.jetbrains.annotations.Nullable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Immutable DTO using Java 21 record.
 * Collections are defensively copied in the canonical constructor.
 */
public record UserProfile(
        @NotBlank String id,
        @NotBlank @Size(max = 100) String name,
        @Nullable @Email String email,
        List<String> roles
) {
    public UserProfile {
        roles = roles == null ? List.of() : List.copyOf(roles);
    }
}
