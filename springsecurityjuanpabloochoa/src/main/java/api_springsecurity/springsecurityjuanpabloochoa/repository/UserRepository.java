package api_springsecurity.springsecurityjuanpabloochoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api_springsecurity.springsecurityjuanpabloochoa.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String emailUsername);
}
