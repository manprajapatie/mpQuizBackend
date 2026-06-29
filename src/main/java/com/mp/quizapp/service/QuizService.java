package com.mp.quizapp.service;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mp.quizapp.dao.QuestionDao;
import com.mp.quizapp.dao.QuizDao;
import com.mp.quizapp.model.Question;
import com.mp.quizapp.model.Quiz;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){

        //Getting Random Question from database
    List<Question> questions = questionDao.findByRandomQuestionsByCategory(category, numQ);


        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
