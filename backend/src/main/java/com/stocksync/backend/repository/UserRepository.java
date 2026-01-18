package com.stocksync.backend.repository;
 import com.stocksync.backend.model.User;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
 import java.util.Optional;

 @Repository

public interface UserRepository extends JpaRepository<User, Long> {
     // 2. Used for Login (Find the user to check password)
     Optional<User> findByUsername(String username);

   /* findBy..is a tool ..spring writes sql query for anythng to find like email,numberor anythng ...
   like ....findByemail(yrs) spring makes it
    like ..select * where email = yrs ....*/


     // optional prevents null pointer exception ....and if user not found then returns empty box//
     boolean existsByUsername(String username);
 }


