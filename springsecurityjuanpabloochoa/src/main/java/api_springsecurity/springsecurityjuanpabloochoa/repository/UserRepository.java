package api_springsecurity.springsecurityjuanpabloochoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api_springsecurity.springsecurityjuanpabloochoa.models.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer>{
	
// Optional<User> findByUsername(String username);
	
	@Query(value= "SELECT sr.id, sr.email, sr.password, sr.role FROM user sr WHERE sr.email = :userEmail", nativeQuery = true)
	Optional<User> findByUsername(@Param("userEmail")String email);
}
