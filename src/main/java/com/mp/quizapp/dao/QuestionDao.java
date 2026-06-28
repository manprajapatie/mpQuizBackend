package com.mp.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.quizapp.entities.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

}
