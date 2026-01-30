package org.online.kz.store.Repository;

import org.online.kz.store.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {


    Users findByUserEmail(String userEmail);


    Users findByUserEmailAndUserPassword(String email, String password);

    Users getUsersById(long id);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserNumber(String userNumber);

     Users findByFullUserName(String username);
}
