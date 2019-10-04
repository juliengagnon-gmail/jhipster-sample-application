package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.User;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

import static com.mycompany.myapp.config.Constants.ID_DELIMITER;

/**
 * Spring Data Couchbase repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends N1qlCouchbaseRepository<User, String> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    default Optional<User> findOneByLogin(String login) {
        return findById(User.PREFIX + ID_DELIMITER + login);
    }

    Page<User> findAllByLoginNot(Pageable pageable, String login);
}
