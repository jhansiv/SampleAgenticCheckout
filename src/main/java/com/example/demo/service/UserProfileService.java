
package com.example.demo.service;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.UserProfile;
import com.example.demo.repo.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileRepository repo = new UserProfileRepository();

    public List<UserProfile> listAll() {
        return repo.findAll();
    }

    public Optional<UserProfile> get(String id) {
        return repo.findById(id);
    }

    public UserProfile create(CreateUserRequest req) {
        String id = UUID.randomUUID().toString();
        List<String> roles = req.roles() == null ? List.of() : List.copyOf(req.roles());
        UserProfile profile = new UserProfile(id, req.name(), req.email(), roles);
        return repo.save(profile);
    }

    public Optional<UserProfile> update(String id, CreateUserRequest req) {
        return repo.findById(id).map(existing -> {
            List<String> roles = req.roles() == null ? existing.roles() : List.copyOf(req.roles());
            String name = req.name() != null ? req.name() : existing.name();
            String email = req.email() != null ? req.email() : existing.email();
            UserProfile updated = new UserProfile(id, name, email, roles);
            return repo.save(updated);
        });
    }

    public boolean delete(String id) {
        return repo.deleteById(id);
    }
}
