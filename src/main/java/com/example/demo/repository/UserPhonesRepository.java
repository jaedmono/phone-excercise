package com.example.demo.repository;

import com.example.demo.model.UserPhones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhonesRepository extends CrudRepository<UserPhones, Long> {

}
