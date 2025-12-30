
package com.example.demo.model;

import org.jetbrains.annotations.Nullable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Request body used to create/update a user.
 * Uses @Nullable for optional fields.
 */
public record CreateUserRequest(
        @NotBlank @Size(max = 100) String name,
        @Nullable @Email String email,
        @Nullable List<String> roles
) {}
