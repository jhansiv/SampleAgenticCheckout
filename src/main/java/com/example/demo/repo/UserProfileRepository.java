
package com.example.demo.repo;

import com.example.demo.model.UserProfile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory repository for demo purposes.
 */
public class UserProfileRepository {
    private final Map<String, UserProfile> store = new ConcurrentHashMap<>();

    public Optional<UserProfile> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<UserProfile> findAll() {
        return List.copyOf(store.values());
    }

    public UserProfile save(UserProfile profile) {
        store.put(profile.id(), profile);
        return profile;
    }

    public boolean deleteById(String id) {
        return store.remove(id) != null;
    }

    public boolean existsById(String id) {
        return store.containsKey(id);
    }
}
