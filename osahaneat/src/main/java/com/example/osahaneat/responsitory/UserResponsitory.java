package com.example.osahaneat.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.Users;

@Repository
public interface UserResponsitory  extends JpaRepository<Users, Long>{

	List<Users> findByUsernameAndPassword(String username, String password);
	
	Users findByUsername(String username);
}
