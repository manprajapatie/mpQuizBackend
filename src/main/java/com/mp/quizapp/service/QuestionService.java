package com.mp.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.quizapp.dao.QuestionDao;
import com.mp.quizapp.entities.Question;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }
    
    public String addQuestion(Question question) {
        //it will save the question from the client
        questionDao.save(question);
        return "success";
    }
}
