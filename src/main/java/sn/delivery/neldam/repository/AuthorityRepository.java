package sn.delivery.neldam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.delivery.neldam.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
