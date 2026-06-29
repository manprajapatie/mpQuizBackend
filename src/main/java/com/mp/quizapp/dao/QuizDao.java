package com.mp.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mp.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
