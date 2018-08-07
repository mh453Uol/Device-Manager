package com.mh453Uol.services;

import com.mh453Uol.database.InMemoryDatabase;
import com.mh453Uol.domain.User;

public class UserService {
	
	public void addUser(User user) {
		InMemoryDatabase.users.put(user.getId(), user);
	}
	
	public boolean userExists(String email) {
		//linear O(n)
		return InMemoryDatabase.users.values().stream().anyMatch(u -> u.getEmail().equals(email));
	}
	
	public User validateCredientials(String email, String password) {
		return InMemoryDatabase.users.values()
				.stream()
				.filter(u -> (u.getEmail().equals(email)) && (u.getPassword().equals(password)))
				.findFirst()
				.orElse(null);
	}
}
