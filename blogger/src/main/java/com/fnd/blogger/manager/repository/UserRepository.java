package com.fnd.blogger.manager.repository;

import com.fnd.blogger.manager.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

   List<User> getAllById(Integer id);
   User findByNameAndPassword(String name,String password);
}
