package com.example.Notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.Notes.Notes;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LoginRepository extends CrudRepository<Login, Integer> {
    @Query("select n from Login n")
    List<Login> showAllUsers();

    @Query("select n from Login n where username like ?1 and password like ?2")
    List<Login> isCorrect(String username, String password);
}